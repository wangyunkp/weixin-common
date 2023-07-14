package com.cloud.weixin.error;

import lombok.Getter;

@Getter
public class WxErrorException extends Exception {
    private static final long serialVersionUID = -6357149550353160810L;

    /**
     * 微信错误代码.
     */
    private Integer errorCode;

    /**
     * 微信错误信息.
     * （如果可以翻译为中文，就为中文）
     */
    private String errorMsg;


    private static final int DEFAULT_ERROR_CODE = -99;

    public WxErrorException(String message) {
        this(DEFAULT_ERROR_CODE, message);
    }

    public WxErrorException(int errorCode, String errorMsg) {
        super("错误代码： " + errorCode + ", 错误信息： " + errorMsg);
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }

    public WxErrorException(int errorCode, String errorMsg, Throwable cause) {
        super("错误代码： " + errorCode + ", 错误信息： " + errorMsg,cause);
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }

    public WxErrorException(Throwable cause) {
        super(cause.getMessage(), cause);
        this.errorMsg = cause.getMessage();
        this.errorCode = DEFAULT_ERROR_CODE;
    }

}
