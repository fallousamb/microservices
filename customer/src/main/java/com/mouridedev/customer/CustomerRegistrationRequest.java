package com.mouridedev.customer;

public record CustomerRegistrationRequest(
    String firstName,
    String lastName,
    String email
){}
