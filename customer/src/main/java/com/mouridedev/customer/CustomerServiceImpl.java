package com.mouridedev.customer;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@AllArgsConstructor
public class CustomerServiceImpl implements CustomerService {

  private CustomerRepository customerRepository;
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
    customerRepository.save(customer);
  }
}
