package com.model2.mvc.service.purchase.dao;

import com.model2.mvc.common.SearchVO;
import com.model2.mvc.service.purchase.vo.PurchaseBuyerVO;
import com.model2.mvc.service.purchase.vo.PurchaseVO;
import com.model2.mvc.service.user.dao.UserDAO;
import com.model2.mvc.service.user.vo.UserVO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;

import static com.model2.mvc.common.util.DBUtil.executeQuery;
import static com.model2.mvc.common.util.DBUtil.executeUpdate;
import static com.model2.mvc.common.util.SQLUtil.makeSearchClause;


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

    public List<PurchaseBuyerVO> getPurchaseList(SearchVO searchVO) {
        final Function<ResultSet, PurchaseBuyerVO> mapperFn = (rs) -> {
            try {
                PurchaseBuyerVO purchaseBuyerVO = new PurchaseBuyerVO();
                purchaseBuyerVO.setRowNum(rs.getInt("rowNum"));
                purchaseBuyerVO.setBuyerId(rs.getString("buyerId"));
                purchaseBuyerVO.setBuyerName(rs.getString("buyerName"));
                purchaseBuyerVO.setBuyerPhone(rs.getString("buyerPhone"));
                purchaseBuyerVO.setTranCode(rs.getString("tranCode"));

                return purchaseBuyerVO;
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        };
        StringBuilder sql = new StringBuilder("SELECT ROW_NUMBER() OVER (ORDER BY order_data) AS \"rowNum\",\n" +
                "           buyer_id AS \"buyerId\",\n" +
                "       receiver_name AS \"buyerName\",\n" +
                "       receiver_phone AS \"buyerPhone\",\n" +
                "       tran_status_code AS \"tranCode\"\n" +
                "FROM transaction\n" +
                "WHERE buyer_id = ?\n" +
                "ORDER BY order_data");

        List<PurchaseBuyerVO> purchaseBuyerVOList = executeQuery(sql.toString(), mapperFn, searchVO.getStartIndex(), searchVO.getEndIndex());

        return purchaseBuyerVOList;
    }

    public static int getPurchaseTotalCount() {
        Function<ResultSet, Integer> mapperFn = (rs) -> {
            try {
                int totalCount = rs.getInt("totalCount");
                return totalCount;
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        };
        String sql = "SELECT\n" +
                "    count(tran_no) AS \"totalCount\"\n" +
                "FROM transaction\n" +
                "WHERE buyer_id = ?";

        return executeQuery(sql, mapperFn).get(0);
    }
}
