package com.cnkonica.commons.result;

public enum  CommonResultCode implements ResultCode {
    SUCCESS("00000","操作成功"),
    SYSTEM_ERROR("90001","系统错误"),
    REMOTE_ERROR("90002","远程错误"),
    ILLEGAL_ARGUMENT("90003","非法参数"),
    DATA_NOT_EXIST("90004","数据不存在")
    ;
    private final String code;
    private final String message;


    CommonResultCode(final String code, final String message) {
        this.code = code;
        this.message = message;

    }

    @Override
    public String getCode() {
        return this.code;
    }

    @Override
    public String getMessage() {
        return this.message;
    }

}
