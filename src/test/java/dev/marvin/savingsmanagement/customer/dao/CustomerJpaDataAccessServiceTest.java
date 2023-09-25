package dev.marvin.savingsmanagement.customer.dao;

import com.github.javafaker.Faker;
import dev.marvin.savingsmanagement.customer.domain.Customer;
import dev.marvin.savingsmanagement.customer.repository.CustomerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

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
    void canGetCustomerById() {
        //given
        long customerId = 1L;
        //when
        underTest.getCustomerById(customerId);
        //then
        verify(customerRepository).findById(customerId);
    }

    @Test
    void canSaveCustomer() {
        //given
        String email = faker().internet().safeEmailAddress() + UUID.randomUUID().toString().substring(0,4);

        Customer customer = Customer.builder()
                .name(faker().name().fullName())
                .email(email)
                .mobile(faker().phoneNumber().cellPhone())
                .memberNumber(UUID.randomUUID().toString().substring(0,6))
                .governmentId(UUID.randomUUID().toString().substring(0,5))
                .address(faker().address().streetAddress())
                .build();
        //when
        underTest.insertCustomer(customer);

        //then
        verify(customerRepository).save(customer);
    }

    @Test
    void updateCustomer() {
        //given
        String email = faker().internet().safeEmailAddress() + UUID.randomUUID().toString().substring(0,4);

        Customer customer = Customer.builder()
                .name(faker().name().fullName())
                .email(email)
                .mobile(faker().phoneNumber().cellPhone())
                .memberNumber(UUID.randomUUID().toString().substring(0,6))
                .governmentId(UUID.randomUUID().toString().substring(0,5))
                .address(faker().address().streetAddress())
                .build();

        //when
        underTest.updateCustomer(customer);

        //then
        verify(customerRepository).save(customer);
    }

    @Test
    void deleteCustomerById() {
        //given
        long customerId = 1L;

        //when
        underTest.deleteCustomerById(customerId);

        //then
        verify(customerRepository).deleteById(customerId);
    }

    @Test
    void existsCustomerWithEmail() {
        //given
        String email = faker().internet().safeEmailAddress() + UUID.randomUUID().toString().substring(0,4);

        //when
        underTest.existsCustomerWithEmail(email);

        //then
        verify(customerRepository).existsCustomerByEmail(email);
    }

    private static Faker faker(){
        return new Faker();
    }


}