package dev.marvin.savingsmanagement.customer.service;

import dev.marvin.savingsmanagement.customer.dao.CustomerDAO;
import dev.marvin.savingsmanagement.customer.domain.Customer;
import dev.marvin.savingsmanagement.customer.dto.NewCustomerRegistrationRequest;
import dev.marvin.savingsmanagement.exception.DuplicateResourceException;
import dev.marvin.savingsmanagement.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CustomerService {
    private final CustomerDAO customerDAO;

    public CustomerService(@Qualifier(value = "jdbc") CustomerDAO customerDAO) {
        this.customerDAO = customerDAO;
    }

    public List<Customer> getAllCustomers() {
        return customerDAO.getAllCustomers();
    }

    public Customer getCustomerById(Long customerId) {
        return customerDAO.getCustomerById(customerId).orElseThrow(() -> new ResourceNotFoundException("Customer with given id [%s] not found".formatted(customerId)));
    }

    public void insertCustomer(NewCustomerRegistrationRequest request) {
        //check if email is taken
        if (customerDAO.existsCustomerWithEmail(request.email())) {
            throw new DuplicateResourceException("email already taken");
        }
        //save customer
        Customer customer = Customer.builder()
                .name(request.name())
                .email(request.email())
                .mobile(request.mobile())
                .memberNumber(UUID.randomUUID().toString().substring(0, 6))
                .governmentId(request.governmentId())
                .address(request.address())
                .build();

        customerDAO.insertCustomer(customer);
    }
}
