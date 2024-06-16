package com.demo.customer.service;

import com.demo.customer.model.Customer;
import com.demo.customer.model.CustomerSearchRequest;
import com.demo.customer.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Service method for the Customer
 * <p>
 * Provides service methods for various service operations.
 */

@Service
@RequiredArgsConstructor
public class CustomerService {
    private final CustomerRepository customerRepository;

    /**
     * Creates new Customer
     *
     * @param customer Customer
     * @return Customer
     */
    @Transactional
    public Customer createCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    /**
     * Fetches a list of customer based on the input search criteria in the request.
     *
     * @param request CustomerSearchRequest
     * @return List<Customer>
     */
    @Transactional(readOnly = true)
    public List<Customer> fetchCustomers(CustomerSearchRequest request) {
        return customerRepository.fetchCustomersBy(request);
    }
}
