package dev.marvin.savingsmanagement.customer.service;

import dev.marvin.savingsmanagement.customer.dao.CustomerDAO;
import dev.marvin.savingsmanagement.customer.domain.Customer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class CustomerService {
    private final CustomerDAO customerDAO;
    public List<Customer> getAllCustomers(){
        return customerDAO.getAllCustomers();
    }
}
