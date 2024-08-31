package com.model2.mvc.service.product.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import java.util.function.Function;

import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.ProductStatus;
import com.model2.mvc.service.domain.Product;

import static com.model2.mvc.common.util.DBUtil.*;
import static com.model2.mvc.common.util.SQLUtil.makeSearchClause;

public class ProductDAO {
    public ProductDAO() {

    }

    public void addProduct(Product productVO) {
        String sql = "insert into product(" + "PROD_NO, PROD_NAME, PROD_DETAIL, MANUFACTURE_DAY," + "PRICE, IMAGE_FILE, REG_DATE)" + "VALUES (\r\n" + "   seq_product_prod_no.nextval,\r\n" + "  ?, ?, ?, ?, ?, ? \r\n" + ")";
        executeUpdate(sql, productVO.getProdName(), productVO.getProdDetail(), productVO.getManuDate(), productVO.getPrice(), productVO.getFileName(), productVO.getRegDate());
    }

    public List<ProductStatus> getProductWithStatusList(Search search) {

        final Function<ResultSet, ProductStatus> mapperFn = (rs) -> {
            try {
                ProductStatus productStatusVO = new ProductStatus();
                productStatusVO.setRowNum(rs.getInt("rowNum"));
                productStatusVO.setProdNo(rs.getInt("prodNo"));
                productStatusVO.setProductName(rs.getString("prodName"));
                productStatusVO.setPrice(rs.getInt("price"));
                productStatusVO.setRegDate(rs.getDate("regDate"));
                productStatusVO.setStatus(rs.getString("statusCode"));

                return productStatusVO;
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        };

        StringBuilder sql = new StringBuilder("SELECT PT.ROW_NUM      AS \"rowNum\",\n" +
                "       PT.\"prodNo\"     AS \"prodNo\",\n" +
                "       PT.\"prodName\"   AS \"prodName\",\n" +
                "       PT.\"price\"      AS \"price\",\n" +
                "       PT.\"regDate\"    AS \"regDate\",\n" +
                "       PT.\"statusCode\" AS \"statusCode\"\n" +
                "\n" +
                "FROM (select ROW_NUMBER() over (ORDER BY reg_date) AS \"ROW_NUM\",\n" +
                "             p.prod_no                             AS \"prodNo\",\n" +
                "             p.prod_name                           AS \"prodName\",\n" +
                "             p.price                               AS \"price\",\n" +
                "             p.reg_date                            AS \"regDate\",\n" +
                "             NVL(t.tran_status_code, 0)            AS \"statusCode\"\n" +
                "      from product p\n" +
                "               left outer join transaction t on p.PROD_NO = t.prod_no\n");
        if (Objects.nonNull(search.getSearchCondition())) {
            sql.append(makeSearchClause(search, "p.prod_no", "p.prod_name", "p.price"));
        }
        sql.append(") PT\n WHERE \"ROW_NUM\" BETWEEN ? AND ?\n");
        sql.append(" order by PT.\"regDate\"");

        List<ProductStatus> result = executeQuery(sql.toString(), mapperFn, search.getStartIndex(), search.getEndIndex());

        return result;
    }

    public Product getProductByProdNo(String prodNo) throws SQLException {
        final Function<ResultSet, Product> mapperFn = (rs) -> {
            try {
                Product productVO = new Product();

                productVO.setProdNo(rs.getInt("PROD_NO"));
                productVO.setProdName(rs.getString("PROD_NAME"));
                productVO.setProdDetail(rs.getString("PROD_DETAIL"));
                productVO.setManuDate(rs.getString("MANUFACTURE_DAY"));
                productVO.setPrice(rs.getInt("PRICE"));
                productVO.setFileName(rs.getString("IMAGE_FILE"));
                productVO.setRegDate(rs.getDate("REG_DATE"));

                return productVO;
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        };
        String sql = "select * from PRODUCT where PROD_NO=?";
        List<Product> productList = executeQuery(sql, mapperFn, prodNo);

        return productList.size() > 0 ? productList.get(0) : null;
    }

    public void updateProduct(Product productVO) {
        String sql = "UPDATE product SET PROD_NAME = ?, PROD_DETAIL = ?, MANUFACTURE_DAY = ?, PRICE = ?, IMAGE_FILE = ?, REG_DATE = ? where PROD_NO = ? ";
        executeUpdate(sql, productVO.getProdName(), productVO.getProdDetail(),
                productVO.getManuDate(), productVO.getPrice(),
                productVO.getFileName(), productVO.getRegDate(),
                productVO.getProdNo());
    }

    public int getProductTotalCount(Search search) {
        Function<ResultSet, Integer> mapperFn = (rs) -> {
            try {
                int totalCount = rs.getInt("totalCount");
                return totalCount;
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        };
        StringBuilder sql = new StringBuilder("SELECT\n" +
                "    count(prod_no) AS \"totalCount\"\n" +
                "FROM product\n");
        if (Objects.nonNull(search.getSearchCondition())) {
            sql.append(makeSearchClause(search, "PROD_NO", "PROD_NAME", "PRICE"));
        }

        return executeQuery(sql.toString(), mapperFn).get(0);
    }
}