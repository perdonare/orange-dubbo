package com.lance.dubbo.controller;

import java.io.Serializable;
import java.util.Map;

/**
 * Created by perdonare on 2016/4/29.
 */
public class MessageReqVO implements Serializable{
    /**
     * 调用源编号
     */
    private String sourceId;
    /**
     * 调用源密钥
     */
    private String sourceKey;
    /**
     * 请求流水号
     */
    private String requestNo;
    /**
     * 电信产品号
     */
    private String productNo;
    /**
     * 消息类型 00通知 01 消息 02 短信（暂不支持）
     */
    private String msgType;
    /**
     * 应用类型 00翼支付客户端    03 理财客户端 09 小贷
     */
    private String appType;
    /**
     * 消息标题
     */
    private String msgTitle;
    /**
     * 消息内容
     */
    private String msgContent;
    /**
     * 附加参数
     */
    private Map<String,String> extras;
    /**
     * 签名类型
     */
    private String signType;
    /**
     * 签名
     */
    private String sign;

    public String getSourceId() {
        return sourceId;
    }

    public void setSourceId(String sourceId) {
        this.sourceId = sourceId;
    }

    public String getSourceKey() {
        return sourceKey;
    }

    public void setSourceKey(String sourceKey) {
        this.sourceKey = sourceKey;
    }

    public String getRequestNo() {
        return requestNo;
    }

    public void setRequestNo(String requestNo) {
        this.requestNo = requestNo;
    }

    public String getProductNo() {
        return productNo;
    }

    public void setProductNo(String productNo) {
        this.productNo = productNo;
    }

    public String getMsgType() {
        return msgType;
    }

    public void setMsgType(String msgType) {
        this.msgType = msgType;
    }

    public String getAppType() {
        return appType;
    }

    public void setAppType(String appType) {
        this.appType = appType;
    }

    public String getMsgTitle() {
        return msgTitle;
    }

    public void setMsgTitle(String msgTitle) {
        this.msgTitle = msgTitle;
    }

    public String getMsgContent() {
        return msgContent;
    }

    public void setMsgContent(String msgContent) {
        this.msgContent = msgContent;
    }

    public Map<String, String> getExtras() {
        return extras;
    }

    public void setExtras(Map<String, String> extras) {
        this.extras = extras;
    }

    public String getSignType() {
        return signType;
    }

    public void setSignType(String signType) {
        this.signType = signType;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }
}
