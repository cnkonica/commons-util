package com.cnkonica.commons.exception;

import com.cnkonica.commons.result.ResultCode;

public class CommonException extends RuntimeException {
    private static final long serialVersionUID = 1L;
    private final String code;
    private final String message;
    public CommonException(final String code,String message){
        super();
        this.code=code;
        this.message = message;

    }
    public CommonException(final ResultCode bizResultCode){
        if (bizResultCode == null) {
            this.code = "";
            this.message = "";
        } else {
            this.code = bizResultCode.getCode();
            this.message = bizResultCode.getMessage();
        }
    }

    public CommonException(final String code, final String message, final Throwable cause) {
        super(cause);
        this.code = code;
        this.message = message;
    }


    public String getCode() {
        return code;
    }
    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return "CommonException{" +
                "code='" + code + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}
