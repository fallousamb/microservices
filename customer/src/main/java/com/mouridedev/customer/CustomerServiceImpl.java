package com.mouridedev.customer;

import com.mouridedev.clients.fraud.FraudCheckResponse;
import com.mouridedev.clients.fraud.FraudClient;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@Slf4j
@AllArgsConstructor
public class CustomerServiceImpl implements CustomerService {

  private final CustomerRepository customerRepository;
  private final FraudClient fraudClient;
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

    //todo: send notification

  }
}
