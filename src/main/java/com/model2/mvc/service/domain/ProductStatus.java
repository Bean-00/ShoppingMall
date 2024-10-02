package com.model2.mvc.service.domain;

import com.model2.mvc.service.purchase.constant.PurchaseStatus;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

public class ProductStatus {

    private int prodNo;
    private String productName;
    private int price;
    private Date regDate;
    private PurchaseStatus status;
    private int rowNum;
    private String fileName;

    public int getRowNum() {
        return rowNum;
    }

    public void setRowNum(int rowNum) {
        this.rowNum = rowNum;
    }

    public int getProdNo() {
        return prodNo;
    }

    public void setProdNo(int prodNo) {
        this.prodNo = prodNo;
    }


    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Date getRegDate() {
        return regDate;
    }

    public void setRegDate(Date regDate) {
        this.regDate = regDate;
    }

    public String getRegDateString() {
        if (Objects.isNull(this.regDate)) return null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy년 MM월 dd일");
        return sdf.format(this.regDate);
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public PurchaseStatus getStatus() {
        return status;
    }

//    public void setStatus(PurchaseStatus status) {
//        this.status = status;
//    }

    public void setStatus(String code) {
        this.status = PurchaseStatus.getByCode(code);
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
}
