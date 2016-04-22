package com.lance.dubbo;

import com.lance.dubbo.consumer.ConsumerService;
import com.lance.dubbo.consumer.model.ConsumerRequest;
import com.lance.dubbo.consumer.model.ConsumerResponse;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Hello world!
 *
 */
public class App {
    public static void main( String[] args ) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:app-config.xml");
        context.start();
        ConsumerService consumerService = context.getBean(ConsumerService.class);
        ConsumerRequest consumerRequest = new ConsumerRequest();
        consumerRequest.setAge(27);
        consumerRequest.setName("lance");
        ConsumerResponse consumerResponse = consumerService.consume(consumerRequest);
        System.out.println(consumerResponse);
    }
}
