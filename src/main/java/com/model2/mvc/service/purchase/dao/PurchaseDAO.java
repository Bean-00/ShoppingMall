package com.model2.mvc.service.purchase.dao;

import com.model2.mvc.service.purchase.vo.PurchaseVO;

import java.util.Map;

import static com.model2.mvc.common.util.DBUtil.executeUpdate;


public class PurchaseDAO {
    public void insertPurchase(PurchaseVO purchaseVO) {
        String sql = "INSERT INTO TRANSACTION (TRAN_NO, PROD_NO, BUYER_ID,PAYMENT_OPTION, RECEIVER_NAME, RECEIVER_PHONE, DLVY_ADDR,\n" +
                "                         DLVY_REQUEST, TRAN_STATUS_CODE, ORDER_DATA, DLVY_DATE)\n" +
                " VALUES (seq_transaction_tran_no.nextval, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        executeUpdate(sql,
                purchaseVO.getPurchaseProd().getProdNo(),
                purchaseVO.getBuyer().getUserId(),
                purchaseVO.getPaymentOption(),
                purchaseVO.getReceiverName(),
                purchaseVO.getReceiverPhone(),
                purchaseVO.getDivyAddr(),
                purchaseVO.getDivyRequest(),
                purchaseVO.getStatus(),
                purchaseVO.getOrderDate(),
                purchaseVO.getDivyDate());
    }

    public Map<String, Object> getPurchaseList(String userId) {
        String sql = "SELECT * FROM TRANSACTION WHERE BUYER_ID = ?";
        executeUpdate(sql, userId);
        return null;

    }

}
