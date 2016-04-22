package com.lance.dubbo.provider;


import com.lance.dubbo.provider.model.ProviderRequest;
import com.lance.dubbo.provider.model.ProviderResponse;

/**
 * Created by perdonare on 2016/4/22.
 */
public interface ProviderService {
    ProviderResponse provider(ProviderRequest providerRequest) throws InterruptedException;
}
