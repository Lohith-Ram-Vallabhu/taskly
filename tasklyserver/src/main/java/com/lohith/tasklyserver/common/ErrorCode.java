/**
 * @author Valabhu Lohith Ram
 * created  on 11-Jun-2025
 */
package com.lohith.tasklyserver.common;

/**
 * Standardized error codes for the application
 */
public enum ErrorCode {
    SUCCESS("200", "Operation successful"),
    BAD_REQUEST("400", "Invalid request"),
    UNAUTHORIZED("401", "Unauthorized access"),
    FORBIDDEN("403", "Access forbidden"),
    NOT_FOUND("404", "Resource not found"),
    INTERNAL_SERVER_ERROR("500", "Internal server error"),
    USER_NOT_FOUND("404", "User not found"),
    INVALID_CREDENTIALS("401", "Invalid credentials");

    private final String code;
    private final String message;

    ErrorCode(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}