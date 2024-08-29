package com.model2.mvc.service.purchase.constant;

import java.util.Arrays;
import java.util.Optional;

public enum PurchaseStatus {
    FOR_SALE("0", "판매중"),
    PURCHASED("1", "구매완료"),
    SHIPPING("2", "배송중"),
    DELIVERED("3", "배송완료");

    private final String code;
    private final String text;

    PurchaseStatus(String code, String text) {
        this.code = code;
        this.text = text;
    }

    public static PurchaseStatus getByCode(String code) {
        return getOptByCode(code)
                .orElse(FOR_SALE);
    }

    public static String getTextByCode(String code) {
        return getOptByCode(code)
                .map(PurchaseStatus::getText)
                .orElse(FOR_SALE.text);
    }

    private static Optional<PurchaseStatus> getOptByCode(String code) {
        return Arrays.stream(PurchaseStatus.values())
                .filter(purchaseStatus -> purchaseStatus.getCode().equals(code))
                .findAny();
    }

    public String getCode() {
        return this.code;
    }

    public String getText() {
        return this.text;
    }
}