package com.lance.dubbo.consumer;

import com.lance.dubbo.consumer.model.ConsumerRequest;
import com.lance.dubbo.consumer.model.ConsumerResponse;

/**
 * Created by perdonare on 2016/4/22.
 */
public interface ConsumerService {
    ConsumerResponse consume(ConsumerRequest consumerRequest) throws InterruptedException;
}
