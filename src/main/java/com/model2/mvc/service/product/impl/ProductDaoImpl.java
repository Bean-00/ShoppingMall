package com.model2.mvc.service.product.impl;


import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.domain.ProductStatus;
import com.model2.mvc.service.product.dao.ProductDao;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("productDaoImpl")
public class ProductDaoImpl implements ProductDao {

    @Autowired
    @Qualifier("sqlSessionTemplate")
    private SqlSessionTemplate sqlSession;


    @Override
    public int getTotalProductCount(Search search) {
        return sqlSession.selectOne("ProductMapper.getTotalProductCount", search);
    }

    @Override
    public void insertProduct(Product product) {
        sqlSession.insert("ProductMapper.insertProduct", product);
    }

    @Override
    public Product getProduct(int prodNo) {
        return sqlSession.selectOne("ProductMapper.getProduct", prodNo);
    }

    @Override
    public List<ProductStatus> getProductWithStatusList(Search search) {
        return sqlSession.selectList("ProductMapper.getProductListWithStatus", search);
    }

    @Override
    public Product getProductByProdNo(int prodNo) {
        return sqlSession.selectOne("ProductMapper.getProductByProdNo", prodNo);
    }

    @Override
    public void updateProduct(Product product) {
        sqlSession.update("ProductMapper.updateProduct", product);
    }

    @Override
    public void deleteProduct(int prodNo) {
        sqlSession.delete("ProductMapper.deleteProduct", prodNo);
    }
}
