package com.model2.mvc.service.domain;

import java.sql.Date;

import com.model2.mvc.service.purchase.constant.PurchaseStatus;


public class Purchase {
    private User buyer;
    private String dlvyAddr;
    private Date dlvyDate;
    private String dlvyRequest;
    private Date orderDate;
    private String paymentOption;
    private Product purchaseProd;
    private String receiverName;
    private String receiverPhone;
    private PurchaseStatus status;
    private int tranNo;
    private int rowNum;
    private int prodNo;

    public Purchase() {
    }

    public User getBuyer() {
        return buyer;
    }

    public void setBuyer(User buyer) {
        this.buyer = buyer;
    }

    public String getDlvyAddr() {
        return dlvyAddr;
    }

    public void setDlvyAddr(String dlvyAddr) {
        this.dlvyAddr = this.dlvyAddr;
    }

    public Date getDlvyDate() {
        return dlvyDate;
    }

    public void setDlvyDate(Date dlvyDate) {
        this.dlvyDate = dlvyDate;
    }

    public String getDlvyRequest() {
        return dlvyRequest;
    }

    public void setDlvyRequest(String divyRequest) {
        this.dlvyRequest = divyRequest;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public String getPaymentOption() {
        return paymentOption;
    }

    public void setPaymentOption(String paymentOption) {
        this.paymentOption = paymentOption;
    }

    public Product getPurchaseProd() {
        return purchaseProd;
    }

    public void setPurchaseProd(Product purchaseProd) {
        this.purchaseProd = purchaseProd;
    }

    public String getReceiverName() {
        return receiverName;
    }

    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
    }

    public String getReceiverPhone() {
        return receiverPhone;
    }

    public void setReceiverPhone(String receiverPhone) {
        this.receiverPhone = receiverPhone;
    }

    public String getStatus() {
        return status.getCode();
    }

    public void setStatus(String code) {
        this.status = PurchaseStatus.getByCode(code);
    }

    public String getTranText() {
        return status.getText();
    }

    public int getTranNo() {
        return tranNo;
    }

    public void setTranNo(int tranNo) {
        this.tranNo = tranNo;
    }

    public int getProdNo() {
        return this.purchaseProd.getProdNo();
    }

    public String getBuyerId() {
        return this.buyer.getUserId();
    }

    @Override
    public String toString() {
        return "PurchaseVO [buyer=" + buyer + ", dlvyAddr=" + dlvyAddr
                + ", divyDate=" + dlvyDate + ", dlvyRequest=" + dlvyRequest
                + ", orderDate=" + orderDate + ", paymentOption="
                + paymentOption + ", purchaseProd=" + purchaseProd
                + ", receiverName=" + receiverName + ", receiverPhone="
                + receiverPhone + ", tranCode=" + status + ", tranNo="
                + tranNo + "]";
    }

    public int getRowNum() {
        return rowNum;
    }

    public void setRowNum(int rowNum) {
        this.rowNum = rowNum;
    }

    public void setProdNo(int prodNo) {
        this.prodNo = prodNo;
    }
}