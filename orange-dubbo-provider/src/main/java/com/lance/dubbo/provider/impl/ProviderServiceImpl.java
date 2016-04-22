package com.lance.dubbo.provider.impl;

import com.lance.dubbo.provider.ProviderService;
import com.lance.dubbo.provider.model.ProviderRequest;
import com.lance.dubbo.provider.model.ProviderResponse;

/**
 * Created by perdonare on 2016/4/22.
 */
public class ProviderServiceImpl implements ProviderService {

    public ProviderResponse provider(ProviderRequest providerRequest) {
        if (providerRequest!=null) {
            System.out.println(providerRequest);
            ProviderResponse response = new ProviderResponse();
            response.setName(providerRequest.getName());
            response.setAge(providerRequest.getAge());
            return response;
        }
        System.out.println("========null=============");
        return null;
    }
}
