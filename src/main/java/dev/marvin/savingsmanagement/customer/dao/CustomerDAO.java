package dev.marvin.savingsmanagement.customer.dao;

import dev.marvin.savingsmanagement.customer.domain.Customer;

import java.util.List;

public interface CustomerDAO {
    List<Customer> getAllCustomers();
    Customer getCustomerById(Long customerId);
    Customer saveCustomer(Customer customer);
    void deleteCustomer(Customer customer);

}
