package dev.marvin.savingsmanagement.customer.repository;

import dev.marvin.savingsmanagement.customer.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    @Query(value = "SELECT c FROM Customer c WHERE c.email = :email")
    boolean existsCustomerByEmail(@Param(value = "email") String email);
}
