package org.vz.finance.integration.net.ui.core.utils;

public enum HttpCode {

    OK(Integer.valueOf(200)),

    MULTI_STATUS(Integer.valueOf(207)),

    LOGIN_FAIL(Integer.valueOf(303)),

    BAD_REQUEST(Integer.valueOf(400)),

    BAD_API(Integer.valueOf(402)),

    UNAUTHORIZED(Integer.valueOf(401)),

    FORBIDDEN(Integer.valueOf(403)),

    NOT_FOUND(Integer.valueOf(404)),

    REQUEST_TIMEOUT(Integer.valueOf(408)),

    CONFLICT(Integer.valueOf(409)),

    GONE(Integer.valueOf(410)),

    LOCKED(Integer.valueOf(423)),

    INTERNAL_SERVER_ERROR(Integer.valueOf(500)),

    SIGNATURE_FAILURE(Integer.valueOf(40002)),

    FACADE_SERVER_CONNECT_FAIL(Integer.valueOf(7001)),

    FACADE_SERVER_FAIL(Integer.valueOf(7002));

    private final Integer value;


    HttpCode(Integer value) {
        this.value = value;
    }


    public Integer value() {
        return this.value;
    }


    public String msg() {
        return Resources.getMessage("HTTPCODE_" + this.value, new Object[0]);
    }


    public String toString() {
        return this.value.toString();
    }


    public static HttpCode setHttpCode(Integer value) {
        HttpCode[] httpCodes = values();
        for (HttpCode httpCode : httpCodes) {
            if (httpCode.value.equals(value)) {
                return httpCode;
            }
        }
        return null;
    }
}
