package com.lance.dubbo.consumer.impl;

import com.lance.dubbo.consumer.ConsumerService;
import com.lance.dubbo.consumer.model.ConsumerRequest;
import com.lance.dubbo.consumer.model.ConsumerResponse;
import com.lance.dubbo.provider.ProviderService;
import com.lance.dubbo.provider.model.ProviderRequest;
import com.lance.dubbo.provider.model.ProviderResponse;

/**
 * Created by perdonare on 2016/4/22.
 */
public class ConsumerServiceImpl implements ConsumerService {
    private ProviderService providerService;
    public ConsumerResponse consume(ConsumerRequest consumerRequest) throws InterruptedException {
        ProviderRequest providerRequest = new ProviderRequest();
        System.out.println("ready dubbo invoke");
        providerRequest.setName(consumerRequest.getName());
        providerRequest.setAge(consumerRequest.getAge());
        ProviderResponse response = providerService.provider(providerRequest);
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
