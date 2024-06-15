package com.demo.customer.service;

import com.demo.customer.model.Customer;
import com.demo.customer.model.CustomerSearchRequest;
import com.demo.customer.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@RequiredArgsConstructor
public class CustomerService {
    private final CustomerRepository customerRepository;

    @Transactional
    public Customer createCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    @Transactional(readOnly = true)
    public List<Customer> fetchCustomers(CustomerSearchRequest request) {
        return customerRepository.fetchCustomersBy(request);
    }
}
