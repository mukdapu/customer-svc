package com.demo.customer.controller;

import com.demo.customer.dto.AddressRequest;
import com.demo.customer.dto.AddressResponse;
import com.demo.customer.dto.CustomerRequest;
import com.demo.customer.dto.CustomerResponse;
import com.demo.customer.enums.AddressType;
import com.demo.customer.model.Address;
import com.demo.customer.model.Customer;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class DataMaker {

    public static CustomerRequest makeCustomerRequest() {
        CustomerRequest customer = new CustomerRequest();
        customer.setFirstName("Bill");
        customer.setLastName("Carson");
        customer.setSpendingLimit(1000d);
        customer.setAge((short) 35);
        customer.setMobileNumber("1-800-232323");
        customer.getAddress().add(makeAddressRequest());
        return customer;
    }

    public static Customer makeCustomer() {
        Customer customer = new Customer();
        customer.setId(1L);
        customer.setFirstName("Bill");
        customer.setLastName("Carson");
        customer.setSpendingLimit(1000d);
        customer.setAge((short) 35);
        customer.setMobileNumber("1-800-232323");
        customer.getAddress().add(makeAddress());
        return customer;
    }

    public static Address makeAddress() {
        Address address = new Address();
        address.setType(AddressType.HOME);
        address.setAddress1("40, Green st");
        address.setAddress2("Mountain blvd");
        address.setCity("Phoenix");
        address.setState("Arizona");
        address.setZip("85001");
        return address;
    }

    public static AddressRequest makeAddressRequest() {
        AddressRequest address = new AddressRequest();
        address.setType(AddressType.HOME);
        address.setAddress1("40, Green st");
        address.setAddress2("Mountain blvd");
        address.setCity("Phoenix");
        address.setState("Arizona");
        address.setZip("85001");
        return address;
    }

    public static CustomerResponse makeCustomerResponse() {
        CustomerResponse customer = new CustomerResponse();
        customer.setId(1L);
        customer.setFirstName("Bill");
        customer.setLastName("Carson");
        customer.setSpendingLimit(1000d);
        customer.setAge((short) 35);
        customer.setMobileNumber("1-800-232323");
        customer.getAddress().add(makeAddressResponse());
        return customer;
    }

    public static AddressResponse makeAddressResponse() {
        AddressResponse address = new AddressResponse();
        address.setId(1L);
        address.setType(AddressType.HOME);
        address.setAddress1("40, Green st");
        address.setAddress2("Mountain blvd");
        address.setCity("Phoenix");
        address.setState("Arizona");
        address.setZip("85001");
        return address;
    }

}
