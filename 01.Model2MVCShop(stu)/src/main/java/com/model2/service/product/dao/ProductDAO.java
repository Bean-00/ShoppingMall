package com.model2.service.product.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Objects;

import com.model2.mvc.common.SearchVO;
import com.model2.mvc.common.util.DBUtil;
import com.model2.mvc.service.product.vo.ProductVO;

public class ProductDAO {
	
	public ProductDAO() {
		
	}
	
	public void addProduct(ProductVO productVO) {
		try(Connection con = DBUtil.getConnection()) {
			String sql = "insert into product("
					+ "PROD_NO, PROD_NAME, PROD_DETAIL, MANUFACTURE_DAY,"
					+ "PRICE, IMAGE_FILE, REG_DATE)"
					+ "VALUES (\r\n"
					+ "   seq_product_prod_no.nextval,\r\n"
					+ "  ?, ?, ?, ?, ?, ? \r\n"
					+ ")";
			
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
	
	public HashMap<String, Object> getProductList(SearchVO searchVO) {
		try(Connection con = DBUtil.getConnection()) {
			String sql = "select * from PRODUCTS ";
			if (Objects.nonNull(searchVO.getSearchCondition())) {
				
			}
			sql += " order by PROD_ID";
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}
}
