package com.lance.dubbo.consumer.impl;

import com.alibaba.dubbo.rpc.RpcException;
import com.lance.dubbo.consumer.ConsumerService;
import com.lance.dubbo.consumer.model.ConsumerRequest;
import com.lance.dubbo.consumer.model.ConsumerResponse;
import com.lance.dubbo.provider.ProviderService;
import com.lance.dubbo.provider.model.ProviderRequest;
import com.lance.dubbo.provider.model.ProviderResponse;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.Set;

/**
 * Created by perdonare on 2016/4/22.
 */
public class ConsumerServiceImpl implements ConsumerService {
    private ProviderService providerService;
    public ConsumerResponse consume(ConsumerRequest consumerRequest) throws InterruptedException {
        ProviderRequest providerRequest = new ProviderRequest();
        System.out.println("ready dubbo invoke");
        //设置为空验证参数
//        providerRequest.setName(consumerRequest.getName());
        providerRequest.setAge(consumerRequest.getAge());
        ProviderResponse response = null;
        try {
            response = providerService.provider(providerRequest);
        }catch (RpcException re) {
            if (re.getCause() instanceof ConstraintViolationException) {
                ConstraintViolationException ve = (ConstraintViolationException) re.getCause(); // 里面嵌了一个ConstraintViolationException
                Set<ConstraintViolation<?>> violations = ve.getConstraintViolations();
                for (ConstraintViolation constraintViolation : violations) {
                    System.out.println(constraintViolation.getMessage());
                }
            }

        }
        ConsumerResponse consumerResponse = new ConsumerResponse();
        if (response!=null) {
            consumerResponse.setAge(response.getAge());
            consumerResponse.setName(response.getName());
        }
        return consumerResponse;
    }

    public void setProviderService(ProviderService providerService) {
        this.providerService = providerService;
    }
}
