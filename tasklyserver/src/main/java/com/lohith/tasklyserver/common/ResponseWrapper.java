/**
 * @author Valabhu Lohith Ram
 * created  on 11-Jun-2025
 */
package com.lohith.tasklyserver.common;

import org.springframework.http.HttpStatus;

/**
 * A wrapper class for standardizing API responses across controllers
 */
public class ResponseWrapper<T> {
    private ErrorCode errorCode;
    private T responseData;
    private HttpStatus httpStatusCode;

    public ResponseWrapper() {
    }

    public ResponseWrapper(ErrorCode errorCode, T responseData, HttpStatus httpStatusCode) {
        this.errorCode = errorCode;
        this.responseData = responseData;
        this.httpStatusCode = httpStatusCode;
    }

    public ErrorCode getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(ErrorCode errorCode) {
        this.errorCode = errorCode;
    }

    public T getResponseData() {
        return responseData;
    }

    public void setResponseData(T responseData) {
        this.responseData = responseData;
    }

    public HttpStatus getHttpStatusCode() {
        return httpStatusCode;
    }

    public void setHttpStatusCode(HttpStatus httpStatusCode) {
        this.httpStatusCode = httpStatusCode;
    }
}