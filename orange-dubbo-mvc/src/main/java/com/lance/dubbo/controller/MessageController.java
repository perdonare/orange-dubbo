package com.lance.dubbo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by perdonare on 2016/4/29.
 */
@Controller

public class MessageController {

    @RequestMapping(value = "/message/api/push",method = RequestMethod.POST)
    @ResponseBody
    public MessageRespVO sendMessage(MessageReqVO messageReqVO) {
        System.out.println(messageReqVO);
        MessageRespVO messageRespVO = new MessageRespVO();
        messageRespVO.setResult("sssssssss");
        return messageRespVO;
    }

}
