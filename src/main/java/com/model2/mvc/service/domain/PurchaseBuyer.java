package com.model2.mvc.service.domain;
import com.model2.mvc.service.purchase.constant.PurchaseStatus;
public class PurchaseBuyer {
    private int rowNum;
    private String buyerId;
    private String buyerName;
    private String buyerPhone;
    private String tranCode;
    private String prodNo;

    public int getRowNum() {
        return rowNum;
    }

    public void setRowNum(int rowNum) {
        this.rowNum = rowNum;
    }

    public String getBuyerId() {
        return buyerId;
    }

    public void setBuyerId(String buyerId) {
        this.buyerId = buyerId;
    }

    public String getBuyerName() {
        return buyerName;
    }

    public void setBuyerName(String buyerName) {
        this.buyerName = buyerName;
    }

    public String getBuyerPhone() {
        return buyerPhone;
    }

    public void setBuyerPhone(String buyerPhone) {
        this.buyerPhone = buyerPhone;
    }

    public String getTranCode() {
        return tranCode;
    }

    public String getTranText() {
        return PurchaseStatus.getTextByCode(this.tranCode);
    }

    public void setTranCode(String tranCode) {
        this.tranCode = tranCode;
    }

    public String getProdNo() {
        return prodNo;
    }

    public void setProdNo(String prodNo) {
        this.prodNo = prodNo;
    }

}
