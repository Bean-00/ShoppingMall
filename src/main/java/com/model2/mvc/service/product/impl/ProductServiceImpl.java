package com.model2.mvc.service.product.impl;

import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.ProductStatus;
import com.model2.mvc.service.domain.Product;
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
	public void addProduct(Product productVO) {
		productDAO.addProduct(productVO);
	}

	@Override
	public void updateProduct(Product productVO) {
		productDAO.updateProduct(productVO);
	}

	@Override
	public void deleteProduct(Product productVO) {

	}

	@Override
	public Product getProduct(String productNo) {
        try {
            return productDAO.getProductByProdNo(productNo);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


	@Override
	public List<ProductStatus> getProductWithStatusList(Search search) {
		return productDAO.getProductWithStatusList(search);
	}

	@Override
	public int getAllProductCount() {
		return productDAO.getProductTotalCount();
	}

}
