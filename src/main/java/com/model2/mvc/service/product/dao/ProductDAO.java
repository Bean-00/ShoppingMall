package com.model2.mvc.service.product.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import java.util.function.Function;

import com.model2.mvc.common.SearchVO;
import com.model2.mvc.common.util.DBUtil;
import com.model2.mvc.service.product.vo.ProductStatusVO;
import com.model2.mvc.service.product.vo.ProductVO;

import static com.model2.mvc.common.util.DBUtil.*;
import static com.model2.mvc.common.util.SQLUtil.makeSearchClause;

public class ProductDAO {
    public ProductDAO() {

    }

    public void addProduct(ProductVO productVO) {
        String sql = "insert into product(" + "PROD_NO, PROD_NAME, PROD_DETAIL, MANUFACTURE_DAY," + "PRICE, IMAGE_FILE, REG_DATE)" + "VALUES (\r\n" + "   seq_product_prod_no.nextval,\r\n" + "  ?, ?, ?, ?, ?, ? \r\n" + ")";
        executeUpdate(sql, productVO.getProdName(), productVO.getProdDetail(), productVO.getManuDate(), productVO.getPrice(), productVO.getFileName(), productVO.getRegDate());
    }

    public Map<String, Object> getProductWithStatusList(SearchVO searchVO) {

        final Function<ResultSet, ProductStatusVO> mapperFn = (rs) -> {
            try {
                ProductStatusVO productStatusVO = new ProductStatusVO();
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

        try (Connection con = DBUtil.getConnection()) {
            StringBuilder sql = new StringBuilder("select\n" +
                    "    p.prod_no AS \"prodNo\",\n" +
                    "    p.prod_name AS \"prodName\",\n" +
                    "    p.price AS \"price\",\n" +
                    "    p.reg_date AS \"regDate\",\n" +
                    "    NVL(t.tran_status_code, 0) AS \"statusCode\"\n" +
                    "\n" +
                    "from product p left outer join transaction t on p.PROD_NO = t.prod_no");
            if (Objects.nonNull(searchVO.getSearchCondition())) {
                sql.append(makeSearchClause(searchVO, "p.prod_no", "p.prod_name", "p.price"));
            }
            sql.append(" order by p.prod_no");

            Map<String, Object> result = executePagingQuery(sql.toString(), mapperFn, searchVO);

            return result;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public ProductVO getProductByProdNo(String prodNo) throws SQLException {
        final Function<ResultSet, ProductVO> mapperFn = (rs) -> {
            try {
                ProductVO productVO = new ProductVO();

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
        List<ProductVO> productList = executeQuery(sql, mapperFn, prodNo);

        return productList.size() > 0 ? productList.get(0) : null;
    }

    public void updateProduct(ProductVO productVO) {
        String sql = "UPDATE product SET PROD_NAME = ?, PROD_DETAIL = ?, MANUFACTURE_DAY = ?, PRICE = ?, IMAGE_FILE = ?, REG_DATE = ? where PROD_NO = ? ";
        executeUpdate(sql, productVO.getProdName(), productVO.getProdDetail(),
                productVO.getManuDate(), productVO.getPrice(),
                productVO.getFileName(), productVO.getRegDate(),
                productVO.getProdNo());
    }
}