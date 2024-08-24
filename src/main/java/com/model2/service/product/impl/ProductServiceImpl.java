package com.model2.service.product.impl;

import java.util.HashMap;

import com.model2.mvc.common.SearchVO;
import com.model2.mvc.service.product.vo.ProductVO;
import com.model2.service.product.ProductService;
import com.model2.service.product.dao.ProductDAO;

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
	public HashMap<String, Object> getProductList(SearchVO searchVO) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
