package com.model2.service.product;

import java.util.HashMap;

import com.model2.mvc.common.SearchVO;
import com.model2.mvc.service.product.vo.ProductVO;


public interface ProductService {
	public void addProduct (ProductVO productVO);
	
	public HashMap<String, Object> getProductList (SearchVO searchVO);

}