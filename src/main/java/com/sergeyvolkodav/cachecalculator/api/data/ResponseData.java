package com.sergeyvolkodav.cachecalculator.api.data;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.sergeyvolkodav.cachecalculator.api.enums.AppStatusCodes;

public class ResponseData {

    private AppStatusCodes status;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String error;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String result;

    public ResponseData(AppStatusCodes status) {
        this.status = status;
    }

    public ResponseData(AppStatusCodes status, Exception error) {
        this.status = status;
        this.error = error.getLocalizedMessage();
    }

    public ResponseData(AppStatusCodes status, String result) {
        this.status = status;
        this.result = result;
    }

    public String getError() {
        return error;
    }

    public String getStatus() {
        return status.getStatus();
    }

    public String getResult() {
        return result;
    }
}
