package com.sergeyvolkodav.cachecalculator.api.enums;


public enum AppStatusCodes {

    StatusOk("ok"),
    StatusError("error");

    private final String status;

    AppStatusCodes(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}