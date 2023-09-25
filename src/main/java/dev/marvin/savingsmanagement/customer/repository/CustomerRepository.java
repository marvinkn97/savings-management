package dev.marvin.savingsmanagement.customer.repository;

import dev.marvin.savingsmanagement.customer.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    boolean existsCustomerByEmail(String email);
}
