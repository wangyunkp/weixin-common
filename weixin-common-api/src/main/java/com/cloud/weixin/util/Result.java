package com.cloud.weixin.util;

import lombok.Data;

import java.util.Objects;

/**
 * 返回体
 *
 * @param <T>
 */
@Data
public class Result<T> {

    /**
     * 结果编码，0-成功；其它-失败
     *
     * @required
     */
    protected Integer code;

    /**
     * 结果消息
     */
    protected String msg;

    /**
     * 结果数据
     */
    protected T data;

    /**
     * 请求ID
     */
    protected String requestId;


    public boolean success() {
        return Objects.equals(this.code, 0);
    }

    public boolean fail() {
        return !success();
    }

    public static <T> Result<T> ofSuccess() {
        Result<T> result = new Result<T>();
        result.setCode(0);
        result.setMsg("OK");
        return result;
    }
    public static <T> Result<T> ofSuccess(T data) {
        Result<T> result = new Result<T>();
        result.setCode(0);
        result.setMsg("OK");
        result.setData(data);
        return result;
    }

    public static <T> Result<T> ofFail(Integer code, String msg) {
        Result<T> result = new Result<T>();
        result.setCode(code);
        result.setMsg(msg);
        return result;
    }
    public static <T> Result<T> ofFail(Integer code, String msg, T data) {
        Result<T> result = new Result<T>();
        result.setCode(code);
        result.setMsg(msg);
        result.setData(data);
        return result;
    }
}
