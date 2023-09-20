package dev.marvin.savingsmanagement.customer.controller;

import dev.marvin.savingsmanagement.customer.domain.Customer;
import dev.marvin.savingsmanagement.customer.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping(path = "/api/v1/customers")
public class CustomerController {
    private final CustomerService customerService;

    @ResponseStatus(code = HttpStatus.OK)
    @GetMapping
    public List<Customer> getAllCustomers(){
        return customerService.getAllCustomers();
    }
}
