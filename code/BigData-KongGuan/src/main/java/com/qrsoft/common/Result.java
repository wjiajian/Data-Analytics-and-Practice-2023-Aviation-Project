package com.qrsoft.common;

import java.io.Serializable;


public class Result<T> implements Serializable {
    private static final long serialVersionUID = 7402793522023784869L;

    /********************基本属性*********************************************************/
    /**操作结果*/
    private boolean isSuccess;
    /**信息*/
    private String msg;
    /**编码*/
    private Integer code;
    /**返回的结果*/
    private T result;

    /********************构造函数*********************************************************/
    public Result() {

    }
    /**
     * 添加，修改，删除 成功时的构造函数
     */
    public Result(boolean isSuccess,Integer code) {
        this.isSuccess = isSuccess;
        this.code = code;
        this.msg = getMessage(code);
    }
    /**
     * 查询成功时的构造函数
     */
    public Result(boolean isSuccess,Integer code,T result) {
        this.isSuccess = isSuccess;
        this.code = code;
        this.msg = getMessage(code);
        this.result = result;
    }

    private String getMessage(Integer code){
        String message = "";
        switch (code) {
            case ResultConstants.C_SUCCESS:
                message = "响应成功";
                break;
            case ResultConstants.C_DATA_NOT_EXIST:
                message = "数据不存在";
                break;
            case ResultConstants.C_SERVER_ERROR:
                message = "程序异常";
                break;

            default:
                break;
        }
        return message;
    }

    /********************封装方法*********************************************************/
    public T getResult() {
        return result;
    }
    public void setResult(T result) {
        this.result = result;
    }
    public boolean getIsSuccess() {
        return isSuccess;
    }
    public void setIsSuccess(boolean isSuccess) {
        this.isSuccess = isSuccess;
    }
    public String getMsg() {
        return msg;
    }
    public void setMsg(String msg) {
        this.msg = msg;
    }
    public Integer getCode() {
        return code;
    }
    public void setCode(Integer code) {
        this.code = code;
    }
}