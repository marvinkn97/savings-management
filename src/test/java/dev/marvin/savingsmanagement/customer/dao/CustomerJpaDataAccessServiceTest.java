package dev.marvin.savingsmanagement.customer.dao;

import dev.marvin.savingsmanagement.customer.repository.CustomerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class CustomerJpaDataAccessServiceTest {
    CustomerJpaDataAccessService underTest;

    @Mock
    CustomerRepository customerRepository;

    @BeforeEach
    void setUp() {
        underTest = new CustomerJpaDataAccessService(customerRepository);
    }

    @Test
    void canGetAllCustomers() {
        //when
        underTest.getAllCustomers();
        //then
        verify(customerRepository).findAll();
    }

    @Test
    void getCustomerById() {
        //given
        long customerId = 1L;
        //when
        underTest.getCustomerById(customerId);
        //then
        verify(customerRepository).findById(customerId);
    }

    @Test
    void saveCustomer() {
        //given
        //when
        //then
    }

    @Test
    void deleteCustomer() {
        //given
        //when
        //then
    }
}