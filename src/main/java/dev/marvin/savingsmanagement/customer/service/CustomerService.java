package dev.marvin.savingsmanagement.customer.service;

import dev.marvin.savingsmanagement.customer.dao.CustomerDAO;
import dev.marvin.savingsmanagement.customer.domain.Customer;
import dev.marvin.savingsmanagement.customer.dto.CustomerFieldUpdateRequest;
import dev.marvin.savingsmanagement.customer.dto.NewCustomerRegistrationRequest;
import dev.marvin.savingsmanagement.customer.dto.RequestValidationException;
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

    public void updateCustomer(Long customerId, CustomerFieldUpdateRequest request) {

        Customer existingCustomer = customerDAO.getCustomerById(customerId).orElseThrow(() -> new ResourceNotFoundException("Customer with given id [%s] not found".formatted(customerId)));

        boolean changes = false;

        if (request.name() != null && !request.name().isEmpty() && !request.name().equals(existingCustomer.getName())) {
            existingCustomer.setName(request.name());
            changes = true;
        }

        if (request.email() != null && !request.email().isEmpty() && !request.email().equals(existingCustomer.getEmail())) {
            if (customerDAO.existsCustomerWithEmail(request.email())) {
                throw new DuplicateResourceException("email already taken");
            }
            existingCustomer.setEmail(request.email());
            changes = true;
        }

        if (request.mobile() != null && !request.mobile().isEmpty() && !request.mobile().equals(existingCustomer.getMobile())) {
            existingCustomer.setMobile(request.mobile());
            changes = true;
        }

        if (request.address() != null && !request.address().isEmpty() && !request.address().equals(existingCustomer.getAddress())) {
            existingCustomer.setName(request.address());
            changes = true;
        }

        if (!changes) {
            throw new RequestValidationException("no data changes found");
        }

        customerDAO.updateCustomer(existingCustomer);
    }
}
