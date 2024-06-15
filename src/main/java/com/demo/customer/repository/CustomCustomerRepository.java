package com.demo.customer.repository;

import com.demo.customer.model.Customer;
import com.demo.customer.model.CustomerSearchRequest;

import java.util.List;


interface CustomCustomerRepository {
    List<Customer> fetchCustomersBy(CustomerSearchRequest searchRequest);
}
