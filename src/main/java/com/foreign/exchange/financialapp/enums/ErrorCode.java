package com.foreign.exchange.financialapp.enums;

public enum ErrorCode {
    SUCCESS(0, "Success"),
    EXCEPTION(-1, "Please check your url is valid or not. For further information please read README.md file"),
    CHECK_PARAMATER(404, "Please check your url is valid or not. For further information please read README.md file"),
    INTERNAL_ERROR(500, "ErrorCode 500 : Internal Server Error. ");

    private final int code;
    private final String description;

    ErrorCode(int code, String description) {
        this.code = code;
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public int getCode() {
        return code;
    }
}
