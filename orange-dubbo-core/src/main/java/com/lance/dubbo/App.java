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
    public static void main( String[] args ) throws InterruptedException {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:app-config.xml");
        context.start();
        final ConsumerService consumerService = context.getBean(ConsumerService.class);
        final ConsumerRequest consumerRequest = new ConsumerRequest();
        consumerRequest.setAge(27);
        consumerRequest.setName("lance");
        for (int i = 0; i < 1; i++) {
            Thread.sleep(10);
            Thread thread = new Thread(new Runnable() {
                public void run() {
                    ConsumerResponse consumerResponse = null;
                    try {
                        consumerResponse = consumerService.consume(consumerRequest);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(consumerResponse);
                }
            });
            thread.start();
        }

       /* ConsumerResponse consumerResponse = consumerService.consume(consumerRequest);
        System.out.println(consumerResponse);*/
        Thread.sleep(30000);
    }
}
