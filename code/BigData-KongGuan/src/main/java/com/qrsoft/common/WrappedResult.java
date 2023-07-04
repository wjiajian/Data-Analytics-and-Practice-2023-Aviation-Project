package com.qrsoft.common;

import io.swagger.annotations.ApiModelProperty;
import org.springframework.security.access.AccessDeniedException;
import java.io.Serializable;

public class WrappedResult<T> implements Serializable {

    private static final long serialVersionUID = 1L;
    private static final String ERROR = "error";
    private static final String SUCCESS = "success";
    /**
     * 接口状态
     */
    @ApiModelProperty("接口状态，true成功，false失败")
    private final boolean successful;
    /**
     * 接口返回数据
     */
    @ApiModelProperty("接口成功返回数据主体")
    private final T resultValue;
    /**
     * 接口错误信息
     */
    @ApiModelProperty("接口错误信息")
    private final String resultHint;
    /**
     * 接口返回状态
     * 失败：401，403，error
     * 成功：success
     */
    @ApiModelProperty("接口返回状态，成功：success，失败：401，403，error")
    private final String type;

    private WrappedResult(boolean isSuccess, T data, String resultHint, String type) {
        this.successful = isSuccess;
        this.resultValue = data;
        this.resultHint = resultHint;
        this.type = type;
    }
    public static <T> WrappedResult<T> successWrapedResult(T data) {
        return new WrappedResult<>(true, data, "", SUCCESS);
    }
    public static <T> WrappedResult<T> failedWrappedResult(String exMessage) {
        return new WrappedResult<>(false, null, exMessage, ERROR);
    }
    public static WrappedResult<Boolean> failedWrappedResult(Exception e, Boolean data) {
        if ("java.lang.RuntimeException".equals(e.getClass().getName())) {
            return new WrappedResult<>(false, data, e.getMessage(), ERROR);
        } else if ("org.springframework.security.access.AccessDeniedException".equals(e.getClass().getName())) {
            throw new AccessDeniedException(e.getMessage());
        } else {
            return new WrappedResult<>(false, data, "操作失败", ERROR);
        }
    }
    public static <T> WrappedResult<T> failedWrappedResult(Exception e) {
        if ("java.lang.RuntimeException".equals(e.getClass().getName())) {
            return new WrappedResult<>(false, null, e.getMessage(), ERROR);
        } else if ("org.springframework.security.access.AccessDeniedException".equals(e.getClass().getName())) {
            throw new AccessDeniedException(e.getMessage());
        } else {
            return new WrappedResult<>(false, null, "操作失败", ERROR);
        }
    }
    public static <T> WrappedResult<T> failedWrappedResult(Exception e, String exMessage) {
        if ("java.lang.RuntimeException".equals(e.getClass().getName())) {
            return new WrappedResult<>(false, null, e.getMessage(), ERROR);
        } else if ("org.springframework.security.access.AccessDeniedException".equals(e.getClass().getName())) {
            throw new AccessDeniedException(e.getMessage());
        } else {
            return new WrappedResult<>(false, null, exMessage, ERROR);
        }
    }
    public static <T> WrappedResult<T> failedWrappedResult(String exMessage, String type) {
        return new WrappedResult<>(false, null, exMessage, type);
    }
    public boolean isSuccessful() {
        return this.successful;
    }
    public T getResultValue() {
        return this.resultValue;
    }
    public String getType() {
        return this.type;
    }
    public String getResultHint() {
        return resultHint;
    }
}
