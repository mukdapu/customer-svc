package com.demo.customer.service;

import com.demo.customer.dto.AddressRequest;
import com.demo.customer.dto.CustomerRequest;
import com.demo.customer.dto.CustomerResponse;
import com.demo.customer.model.Address;
import com.demo.customer.model.Customer;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MapperService {

    private ModelMapper mapper;

    public MapperService(ModelMapper modelMapper) {
        this.mapper = modelMapper;
        this.mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
    }

    public List<CustomerResponse> converToCustomerResponseList(List<Customer> customerList) {
        return customerList.stream().map(this::converToCustomerResponse).toList();
    }

    public CustomerResponse converToCustomerResponse(Customer customer) {
        return mapper.map(customer, CustomerResponse.class);
    }

    public Customer converToCustomer(CustomerRequest request) {
        Customer customer = mapper.map(request, Customer.class);
        request.getAddress().forEach(address->  customer.getAddress().add(converToAddress(address, customer)));
        return customer;
    }

    public Address converToAddress(AddressRequest request, Customer customer) {
        Address address = mapper.map(request, Address.class);
        address.setCustomer(customer);
        return address;
    }
}
