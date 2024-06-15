package com.demo.customer.repository;

import com.demo.customer.model.Customer;
import com.demo.customer.model.CustomerSearchRequest;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends PagingAndSortingRepository<Customer, Short>, CustomCustomerRepository {
    List<Customer> fetchCustomersBy(CustomerSearchRequest searchRequest);
}
