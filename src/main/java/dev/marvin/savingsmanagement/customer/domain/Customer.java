package dev.marvin.savingsmanagement.customer.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "tbl_customers")
public class Customer {
    @Id
    private Long id;
    private String name;
    private String email;
    private String mobile;
    private String memberNumber;
    private String createdDate;
    private String updatedDAte;

}
