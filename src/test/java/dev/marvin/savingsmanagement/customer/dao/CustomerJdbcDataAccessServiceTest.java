package dev.marvin.savingsmanagement.customer.dao;

import dev.marvin.savingsmanagement.AbstractTestContainerTest;
import dev.marvin.savingsmanagement.customer.domain.Customer;
import dev.marvin.savingsmanagement.customer.rowmapper.CustomerRowMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

class CustomerJdbcDataAccessServiceTest extends AbstractTestContainerTest {

    CustomerJdbcDataAccessService underTest;

    @BeforeEach
    void setUp() {
        underTest = new CustomerJdbcDataAccessService(new JdbcTemplate(dataSource()), new CustomerRowMapper());
    }

    @Test
    void canGetAllCustomers() {
        Customer customer = Customer.builder()
                .name(faker().name().fullName())
                .email(faker().internet().safeEmailAddress() + UUID.randomUUID().toString().substring(0,4))
                .mobile(faker().phoneNumber().cellPhone())
                .memberNumber(UUID.randomUUID().toString().substring(0,6))
                .governmentId(UUID.randomUUID().toString().substring(0,5))
                .address(faker().address().streetAddress())
                .build();
        underTest.insertCustomer(customer);

        //when
        List<Customer> actual = underTest.getAllCustomers();

        //then
        assertThat(actual).isNotEmpty();
    }

    @Test
    void canGetCustomerById() {
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
        underTest.insertCustomer(customer);

        long customerId = underTest.getAllCustomers().stream()
                .filter(c -> c.getEmail().equals(email))
                .findFirst()
                .map(Customer::getId)
                .orElseThrow();
        //when
        Optional<Customer> actual = underTest.getCustomerById(customerId);

        //then
        assertThat(actual).isPresent().hasValueSatisfying(c -> {
            assertThat(c.getId()).isEqualTo(customerId);
            assertThat(c.getName()).isEqualTo(customer.getName());
            assertThat(c.getEmail()).isEqualTo(customer.getEmail());
            assertThat(c.getMobile()).isEqualTo(customer.getMobile());
            assertThat(c.getMemberNumber()).isEqualTo(customer.getMemberNumber());
            assertThat(c.getGovernmentId()).isEqualTo(customer.getGovernmentId());
            assertThat(c.getAddress()).isEqualTo(customer.getAddress());
        });
    }

    @Test
    void willNotPresentWhenGetCustomerById(){
        //given
        long customerId = -1;

        //when
        Optional<Customer> actual = underTest.getCustomerById(customerId);

        //then
        assertThat(actual).isNotPresent();
    }

    @Test
    void canInsertCustomer() {
        String email = faker().internet().safeEmailAddress() + UUID.randomUUID().toString().substring(0,4);

        Customer customer = Customer.builder()
                .name(faker().name().fullName())
                .email(email)
                .mobile(faker().phoneNumber().cellPhone())
                .memberNumber(UUID.randomUUID().toString().substring(0,6))
                .governmentId(UUID.randomUUID().toString().substring(0,5))
                .address(faker().address().streetAddress())
                .build();
        underTest.insertCustomer(customer);

        //when
        Optional<Customer> actual = underTest.getAllCustomers().stream()
                .filter(c -> c.getEmail().equals(email))
                .findFirst();

        //then
        assertThat(actual).isPresent();
    }

    @Test
    void updateCustomer() {
    }

    @Test
    void deleteCustomer() {
    }
}