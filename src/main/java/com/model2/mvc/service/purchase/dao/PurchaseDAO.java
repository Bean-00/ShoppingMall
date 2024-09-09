package com.model2.mvc.service.purchase.dao;

import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.PurchaseBuyer;
import com.model2.mvc.service.domain.Purchase;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.function.Function;

import static com.model2.mvc.common.util.DBUtil.executeQuery;
import static com.model2.mvc.common.util.DBUtil.executeUpdate;


public interface PurchaseDAO {
    void insertPurchase(Purchase purchaseVO);

    List<PurchaseBuyer> getPurchaseList(Search search, String buyerId);

    int getPurchaseTotalCount(String buyerId);

    void updateTransCode(String prodNo);

//    public void insertPurchase(Purchase purchaseVO) {
//        String sql = "INSERT INTO TRANSACTION (TRAN_NO, PROD_NO, BUYER_ID,PAYMENT_OPTION, RECEIVER_NAME, RECEIVER_PHONE, DLVY_ADDR,\n" +
//                "                         DLVY_REQUEST, TRAN_STATUS_CODE, ORDER_DATA, DLVY_DATE)\n" +
//                " VALUES (seq_transaction_tran_no.nextval, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
//        executeUpdate(sql,
//                purchaseVO.getPurchaseProd().getProdNo(),
//                purchaseVO.getBuyer().getUserId(),
//                purchaseVO.getPaymentOption(),
//                purchaseVO.getReceiverName(),
//                purchaseVO.getReceiverPhone(),
//                purchaseVO.getDivyAddr(),
//                purchaseVO.getDivyRequest(),
//                purchaseVO.getStatus(),
//                purchaseVO.getOrderDate(),
//                purchaseVO.getDivyDate());
//    }
//
//    public List<PurchaseBuyer> getPurchaseList(Search search, String buyerId) {
//        final Function<ResultSet, PurchaseBuyer> mapperFn = (rs) -> {
//            try {
//                PurchaseBuyer purchaseBuyerVO = new PurchaseBuyer();
//                purchaseBuyerVO.setRowNum(rs.getInt("rowNum"));
//                purchaseBuyerVO.setBuyerId(rs.getString("buyerId"));
//                purchaseBuyerVO.setBuyerName(rs.getString("buyerName"));
//                purchaseBuyerVO.setBuyerPhone(rs.getString("buyerPhone"));
//                purchaseBuyerVO.setTranCode(rs.getString("tranCode"));
//                purchaseBuyerVO.setProdNo(rs.getString("prodNo"));
//
//                return purchaseBuyerVO;
//            } catch (SQLException e) {
//                throw new RuntimeException(e);
//            }
//        };
//        StringBuilder sql = new StringBuilder("SELECT \"rowNum\",\n" +
//                "       buyer_id         AS \"buyerId\",\n" +
//                "       receiver_name    AS \"buyerName\",\n" +
//                "       receiver_phone   AS \"buyerPhone\",\n" +
//                "       tran_status_code AS \"tranCode\",\n" +
//                "       prod_no          AS \"prodNo\"\n" +
//                "FROM (SELECT ROW_NUMBER() OVER (ORDER BY order_data) AS \"rowNum\",\n" +
//                "             buyer_id,\n" +
//                "             receiver_name,\n" +
//                "             receiver_phone,\n" +
//                "             tran_status_code,\n" +
//                "             prod_no,\n" +
//                "             order_data\n" +
//                "      FROM TRANSACTION\n");
//
//        sql.append(") \n");
//        sql.append("WHERE buyer_id ='");
//        sql.append(buyerId);
//        sql.append("'\n");
//        sql.append("AND \"rowNum\" BETWEEN ? AND ?\n");
//        sql.append("ORDER BY order_data");
//
//        List<PurchaseBuyer> purchaseBuyerVOList = executeQuery(sql.toString(), mapperFn, search.getStartIndex(), search.getEndIndex());
//
//        return purchaseBuyerVOList;
//    }
//
//    public static int getPurchaseTotalCount(String buyerId) {
//        Function<ResultSet, Integer> mapperFn = (rs) -> {
//            try {
//                int totalCount = rs.getInt("totalCount");
//                return totalCount;
//            } catch (SQLException e) {
//                throw new RuntimeException(e);
//            }
//        };
//        String sql = "SELECT\n" +
//                "    count(tran_no) AS \"totalCount\"\n" +
//                "FROM transaction\n" +
//                "WHERE buyer_id = ?";
//
//        return executeQuery(sql, mapperFn, buyerId).get(0);
//    }
//
//    public static void updateTransCode(String prodNo) {
//        StringBuilder sql = new StringBuilder("UPDATE TRANSACTION\n" +
//                "SET TRAN_STATUS_CODE = TRAN_STATUS_CODE + 1\n" +
//                "WHERE prod_no = ?");
//
//        executeUpdate(sql.toString(), prodNo);
//    }
}
