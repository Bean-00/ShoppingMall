package com.model2.mvc.service.product.impl;

import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.ProductStatus;
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.product.ProductService;
import com.model2.mvc.service.product.dao.ProductDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service("productServiceImpl")
public class ProductServiceImpl implements ProductService {

	@Autowired
	@Qualifier("productDaoImpl")
	private ProductDAO productDAO;

	@Override
	public void addProduct(Product productVO) {
		productDAO.insertProduct(productVO);
	}

	@Override
	public void updateProduct(Product productVO) {
		productDAO.updateProduct(productVO);
	}

	@Override
	public void deleteProduct(Product productVO) {

	}

	@Override
	public Product getProductByProdNo(int productNo) {
        return productDAO.getProductByProdNo(productNo);
    }


	@Override
	public List<ProductStatus> getProductWithStatusList(Search search) {
		return productDAO.getProductWithStatusList(search);
	}

	@Override
	public int getAllProductCount(Search search) {
		return productDAO.getTotalProductCount(search);
	}

}
