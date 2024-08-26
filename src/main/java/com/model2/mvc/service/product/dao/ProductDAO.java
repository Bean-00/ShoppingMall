package com.model2.mvc.service.product.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import com.model2.mvc.common.SearchVO;
import com.model2.mvc.common.util.DBUtil;
import com.model2.mvc.common.util.SQLUtil;
import com.model2.mvc.service.product.vo.ProductVO;

public class ProductDAO {

    public ProductDAO() {

    }

    public void addProduct(ProductVO productVO) {
        try (Connection con = DBUtil.getConnection()) {
            String sql = "insert into product(" + "PROD_NO, PROD_NAME, PROD_DETAIL, MANUFACTURE_DAY," + "PRICE, IMAGE_FILE, REG_DATE)" + "VALUES (\r\n" + "   seq_product_prod_no.nextval,\r\n" + "  ?, ?, ?, ?, ?, ? \r\n" + ")";

            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, productVO.getProdName());
            stmt.setString(2, productVO.getProdDetail());
            stmt.setString(3, productVO.getManuDate());
            stmt.setInt(4, productVO.getPrice());
            stmt.setString(5, productVO.getFileName());
            stmt.setDate(6, productVO.getRegDate());

            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Map<String, Object> getProductList(SearchVO searchVO) {
        try (Connection con = DBUtil.getConnection()) {
            StringBuilder sql = new StringBuilder("select * from PRODUCT ");
            if (Objects.nonNull(searchVO.getSearchCondition())) {
                if (searchVO.getSearchCondition().equals("0")) {
                    sql.append(SQLUtil.makeLikeClause("PROD_NO", searchVO.getSearchKeyword()));
                } else if (searchVO.getSearchCondition().equals("1")) {
                    sql.append(SQLUtil.makeLikeClause("PROD_NAME", searchVO.getSearchKeyword()));
                } else {
                    sql.append(SQLUtil.makeLikeClause("PRICE", searchVO.getSearchKeyword()));
                }

            }
            sql.append(" order by PROD_NO");

            PreparedStatement stmt = con.prepareStatement(sql.toString(), ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = stmt.executeQuery();

            rs.last();
            int total = rs.getRow();

            Map<String, Object> productMap = new HashMap<>();

            productMap.put("count", new Integer(total));

            rs.absolute(searchVO.getPage() * searchVO.getPageUnit() - searchVO.getPageUnit() + 1);
            System.out.println("searchVO.getPage():" + searchVO.getPage());
            System.out.println("searchVO.getPageUnit():" + searchVO.getPageUnit());

            List<ProductVO> list = new ArrayList<>();

            if (total > 0) {
                for (int i = 0; i < searchVO.getPageUnit(); i++) {
                    ProductVO productVO = new ProductVO();
                    productVO.setProdNo(rs.getInt("PROD_NO"));
                    productVO.setProdName(rs.getString("PROD_NAME"));
                    productVO.setProdDetail(rs.getString("PROD_DETAIL"));
                    productVO.setManuDate(rs.getString("MANUFACTURE_DAY"));
                    productVO.setPrice(rs.getInt("PRICE"));
                    productVO.setFileName(rs.getString("IMAGE_FILE"));
                    productVO.setRegDate(rs.getDate("REG_DATE"));

                    list.add(productVO);
                    if (!rs.next()) break;
                }
            }
            System.out.println("list.size() : " + list.size());
            productMap.put("list", list);
            System.out.println("map().size() : " + productMap.size());

            con.close();

            return productMap;

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public ProductVO getProductByProdNo(String prodNo) throws SQLException {
        Connection con = DBUtil.getConnection();

        String sql = "select * from PRODUCT where PROD_NO=?";

        PreparedStatement stmt = con.prepareStatement(sql);
        stmt.setString(1, prodNo);

        ResultSet rs = stmt.executeQuery();

        ProductVO productVO = null;
        while (rs.next()) {
            productVO = new ProductVO();
            productVO.setProdNo(rs.getInt("PROD_NO"));
            productVO.setProdName(rs.getString("PROD_NAME"));
            productVO.setProdDetail(rs.getString("PROD_DETAIL"));
            productVO.setManuDate(rs.getString("MANUFACTURE_DAY"));
            productVO.setPrice(rs.getInt("PRICE"));
            productVO.setFileName(rs.getString("IMAGE_FILE"));
            productVO.setRegDate(rs.getDate("REG_DATE"));

        }

        con.close();

        return productVO;
    }

    public void updateProduct(ProductVO productVO) {

        try (Connection con = DBUtil.getConnection()) {
            StringBuilder sql = new StringBuilder("UPDATE product " +
                    "SET PROD_NAME = ?," +
                    "PROD_DETAIL = ?," +
                    "MANUFACTURE_DAY = ?," +
                    "PRICE = ?," +
                    "IMAGE_FILE = ?," +
                    "REG_DATE = ?" +
            "where PROD_NO = ?");

            PreparedStatement stmt = con.prepareStatement(sql.toString());
            stmt.setString(1, productVO.getProdName());
            stmt.setString(2, productVO.getProdDetail());
            stmt.setString(3, productVO.getManuDate());
            stmt.setInt(4, productVO.getPrice());
            stmt.setString(5, productVO.getFileName());
            stmt.setDate(6, productVO.getRegDate());
            stmt.setInt(7, productVO.getProdNo());

            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
