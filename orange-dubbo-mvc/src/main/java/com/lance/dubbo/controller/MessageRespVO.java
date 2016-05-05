package com.lance.dubbo.controller;


import java.io.Serializable;

/**
 * Created by perdonare on 2016/4/29.
 */

public class MessageRespVO implements Serializable{
    /**
     * 是否成功
     */
    private Boolean success;
    /**
     * 应答对象
     */
    private String result;
    /**
     * 应答码
     */
    private String errorCode;
    /**
     * 应答描述信息
     */
    private String errorMsg;

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }
}
