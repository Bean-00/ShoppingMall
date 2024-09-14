package com.model2.mvc.service.purchase.impl;

import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Purchase;
import com.model2.mvc.service.domain.PurchaseBuyer;
import com.model2.mvc.service.purchase.dao.PurchaseDao;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("purchaseDaoImpl")
public class PurchaseDaoImpl implements PurchaseDao {

    @Autowired
    @Qualifier("sqlSessionTemplate")
    private SqlSessionTemplate sqlSession;

    @Override
    public void insertPurchase(Purchase purchase) {
        sqlSession.insert("PurchaseMapper.addPurchase", purchase);
    }

    @Override
    public List<PurchaseBuyer> getPurchaseList(Search search) {
        return sqlSession.selectList("PurchaseMapper.getPurchaseList", search);
    }

    @Override
    public int checkPurchaseLog(String prodNo) {
        return sqlSession.selectOne("PurchaseMapper.checkPurchaseLog", prodNo);
    }

    @Override
    public int getPurchaseTotalCount(String buyerId) {
        return sqlSession.selectOne("PurchaseMapper.getPurchaseTotalCount", buyerId);
    }

    @Override
    public void updateTransCode(int prodNo) {
        sqlSession.update("PurchaseMapper.updateTransCode", prodNo);
    }

    @Override
    public void deletePurchase(int prodNo) {
        sqlSession.delete("PurchaseMapper.deletePurchase", prodNo);
    }
}
