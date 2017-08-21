package com.platform.parent.response;

import com.platform.parent.util.ErrorCode;

/**
 * Created by tqyao.
 */
public class ErrorResponse {
    private ErrorCode errorCode;

    public ErrorResponse(ErrorCode errorCode) {
        this.errorCode = errorCode;
    }

    public ErrorCode getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(ErrorCode errorCode) {
        this.errorCode = errorCode;
    }
}
