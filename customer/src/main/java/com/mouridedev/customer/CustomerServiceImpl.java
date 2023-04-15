package com.mouridedev.customer;

import com.mouridedev.amqp.RabbitMQMessageProducer;
import com.mouridedev.clients.fraud.FraudCheckResponse;
import com.mouridedev.clients.fraud.FraudClient;
import com.mouridedev.clients.notification.NotificationRequest;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@AllArgsConstructor
public class CustomerServiceImpl implements CustomerService {

  private final CustomerRepository customerRepository;
  private final FraudClient fraudClient;
  private final RabbitMQMessageProducer rabbitMQMessageProducer;
  @Override
  public void registerCustomer(final CustomerRegistrationRequest customerRegistrationRequest) {
    Customer customer = Customer.builder()
        .firstName(customerRegistrationRequest.firstName())
        .lastName(customerRegistrationRequest.lastName())
        .email(customerRegistrationRequest.email())
        .build();
    //todo: check if email is valid
    //todo: check if email is not token
    //todo: check if customer is db

    customerRepository.saveAndFlush(customer);
    // check if customer is fraudster
    FraudCheckResponse fraudCheckResponse = fraudClient.isFraudster(customer.getId());
    if (fraudCheckResponse.getIsFraudster()) {
      throw new IllegalStateException("fraudster");
    }

    final NotificationRequest notificationRquest = new NotificationRequest(
        customer.getId(),
        customer.getEmail(),
        String.format("Hi %s, welcome to Mouride Dev...", customer.getFirstName())
    );
    //Send to amqp
    rabbitMQMessageProducer.publish(
        notificationRquest,
        "internal.exchange",
        "internal.notification.routing-key"
        );
  }
}
