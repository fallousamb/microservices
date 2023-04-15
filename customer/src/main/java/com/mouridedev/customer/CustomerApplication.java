package com.mouridedev.customer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication(scanBasePackages = {
    "com.mouridedev.customer",
    "com.mouridedev.amqp",
})
@EnableDiscoveryClient
@EnableFeignClients(basePackages = "com.mouridedev.clients")
public class CustomerApplication {
  public static void main(String[] args) {
    SpringApplication.run(CustomerApplication.class, args);
  }
}
