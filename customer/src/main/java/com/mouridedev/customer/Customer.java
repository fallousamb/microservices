package com.mouridedev.customer;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Customer {

  @Id
  @SequenceGenerator(
      name = "customer_id_sequnce",
      sequenceName = "customer_id_sequnce"
  )
  @GeneratedValue(
      strategy = GenerationType.SEQUENCE,
      generator = "customer_id_sequnce"
  )
  private Integer id;
  private String firstName;
  private String lastName;
  private String email;
}