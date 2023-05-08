package com.mouridedev.notification;

import com.mouridedev.amqp.RabbitMQMessageProducer;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

@SpringBootApplication(scanBasePackages = {
    "com.mouridedev.notification",
    "com.mouridedev.amqp",
})
@EnableDiscoveryClient
@EnableFeignClients(basePackages = "com.mouridedev.clients")
@PropertySources({
    @PropertySource("classpath:clients-${spring.profiles.active}.properties")
})
public class NotificationApplication {
  public static void main(String[] args) {
    SpringApplication.run(NotificationApplication.class, args);
  }

//  @Bean
//  CommandLineRunner commandLineRunner(
//      RabbitMQMessageProducer producer,
//      NotificationConfig notificationConfig
//  ) {
//    return args -> {
//      producer.publish(
//          new Person("Fallou", 30),
//          notificationConfig.getInternalExchange(),
//          notificationConfig.getInternalNotificationRoutingkey()
//      );
//    };
//  }
//
//  record Person(String name, int age){}
}
