package com.model2.mvc.service.purchase.vo;

import java.sql.Date;

import com.model2.mvc.service.product.vo.ProductVO;
import com.model2.mvc.service.purchase.constant.PurchaseStatus;
import com.model2.mvc.service.user.vo.UserVO;


public class PurchaseVO {
    private UserVO buyer;
    private String divyAddr;
    private Date divyDate;
    private String divyRequest;
    private Date orderDate;
    private String paymentOption;
    private ProductVO purchaseProd;
    private String receiverName;
    private String receiverPhone;
    private PurchaseStatus status;
    private int tranNo;

    public PurchaseVO() {
    }

    public UserVO getBuyer() {
        return buyer;
    }

    public void setBuyer(UserVO buyer) {
        this.buyer = buyer;
    }

    public String getDivyAddr() {
        return divyAddr;
    }

    public void setDivyAddr(String divyAddr) {
        this.divyAddr = divyAddr;
    }

    public Date getDivyDate() {
        return divyDate;
    }

    public void setDivyDate(Date divyDate) {
        this.divyDate = divyDate;
    }

    public String getDivyRequest() {
        return divyRequest;
    }

    public void setDivyRequest(String divyRequest) {
        this.divyRequest = divyRequest;
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

    public ProductVO getPurchaseProd() {
        return purchaseProd;
    }

    public void setPurchaseProd(ProductVO purchaseProd) {
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

    public String getTranText() {
        return status.getText();
    }

    public void setStatus(PurchaseStatus status) {
        this.status = status;
    }

    public int getTranNo() {
        return tranNo;
    }

    public void setTranNo(int tranNo) {
        this.tranNo = tranNo;
    }

    @Override
    public String toString() {
        return "PurchaseVO [buyer=" + buyer + ", divyAddr=" + divyAddr
                + ", divyDate=" + divyDate + ", divyRequest=" + divyRequest
                + ", orderDate=" + orderDate + ", paymentOption="
                + paymentOption + ", purchaseProd=" + purchaseProd
                + ", receiverName=" + receiverName + ", receiverPhone="
                + receiverPhone + ", tranCode=" + status + ", tranNo="
                + tranNo + "]";
    }
}