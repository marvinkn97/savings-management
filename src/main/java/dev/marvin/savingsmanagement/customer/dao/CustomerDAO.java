package dev.marvin.savingsmanagement.customer.dao;

import dev.marvin.savingsmanagement.customer.domain.Customer;

import java.util.List;
import java.util.Optional;

public interface CustomerDAO {
    List<Customer> getAllCustomers();
    Optional<Customer> getCustomerById(Long customerId);
    void insertCustomer(Customer customer);
    void updateCustomer(Customer customer);
    void deleteCustomerById(Long customerId);
    boolean existsCustomerWithEmail(String email);

}
