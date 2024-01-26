package com.root.springboot.demo.entity;

public enum CategoryEnum {
    SOFTWARE_DEVELOPER("Software Developer"),
    SCRUM_MASTER("Scrum Master"),
    PRODUCT_OWNER("Product Owner"),
    UX_DESIGNER("UX Designer"),
    CUSTOMER_ASSISTANT("Customer Assistant");

    private final String displayName;

    CategoryEnum(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}