package dev.marvin.savingsmanagement.customer.dao;

import dev.marvin.savingsmanagement.customer.repository.CustomerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;

class CustomerJpaDataAccessServiceTest {
    CustomerJpaDataAccessService underTest;

    @Autowired
    CustomerRepository customerRepository;

    @BeforeEach
    void setUp() {
        underTest = new CustomerJpaDataAccessService(customerRepository);
    }

    @Test
    void getAllCustomers() {
    }

    @Test
    void getCustomerById() {
    }

    @Test
    void saveCustomer() {
    }

    @Test
    void deleteCustomer() {
    }
}