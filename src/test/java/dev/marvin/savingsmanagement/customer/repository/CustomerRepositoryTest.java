package dev.marvin.savingsmanagement.customer.repository;

import dev.marvin.savingsmanagement.AbstractTestContainerTest;
import dev.marvin.savingsmanagement.customer.domain.Customer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;


@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class CustomerRepositoryTest extends AbstractTestContainerTest {

    @Autowired
    CustomerRepository underTest;

    @Test
    void checkIfExistsCustomerByEmail() {
        String email = faker().internet().safeEmailAddress() + UUID.randomUUID().toString().substring(0, 4);

        Customer customer = Customer.builder()
                .name(faker().name().fullName())
                .email(email)
                .mobile(faker().phoneNumber().cellPhone())
                .memberNumber(UUID.randomUUID().toString().substring(0, 6))
                .governmentId(UUID.randomUUID().toString().substring(0, 5))
                .address(faker().address().streetAddress())
                .build();
        underTest.save(customer);

        //when
        var actual = underTest.existsCustomerByEmail(email);

        //then
        assertThat(actual).isTrue();
    }

    @Test
    void willReturnFalseIfEmailNotTaken() {
        //given
        String email = faker().internet().safeEmailAddress() + UUID.randomUUID().toString().substring(0, 4);

        //when
        var actual = underTest.existsCustomerByEmail(email);

        //then
        assertThat(actual).isFalse();

    }
}