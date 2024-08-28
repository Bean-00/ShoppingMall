package com.model2.mvc.service.product.impl;

import com.model2.mvc.common.SearchVO;
import com.model2.mvc.service.product.vo.ProductStatusVO;
import com.model2.mvc.service.product.vo.ProductVO;
import com.model2.mvc.service.product.ProductService;
import com.model2.mvc.service.product.dao.ProductDAO;

import java.sql.SQLException;
import java.util.List;

public class ProductServiceImpl implements ProductService {
	
	private ProductDAO productDAO;
	
	public ProductServiceImpl() {
		productDAO = new ProductDAO();
	}

	@Override
	public void addProduct(ProductVO productVO) {
		productDAO.addProduct(productVO);
	}

	@Override
	public void updateProduct(ProductVO productVO) {
		productDAO.updateProduct(productVO);
	}

	@Override
	public void deleteProduct(ProductVO productVO) {

	}

	@Override
	public ProductVO getProduct(String productNo) {
        try {
            return productDAO.getProductByProdNo(productNo);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


	@Override
	public List<ProductStatusVO> getProductWithStatusList(SearchVO searchVO) {
		return productDAO.getProductWithStatusList(searchVO);
	}

	@Override
	public int getAllProductCount() {
		return productDAO.getProductTotalCount();
	}

}
