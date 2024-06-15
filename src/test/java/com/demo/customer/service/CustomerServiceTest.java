package com.demo.customer.service;

import com.demo.customer.controller.DataMaker;
import com.demo.customer.model.Address;
import com.demo.customer.model.Customer;
import com.demo.customer.model.CustomerSearchRequest;
import com.demo.customer.repository.CustomerRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CustomerServiceTest {

    @InjectMocks
    private CustomerService service;

    @Mock
    private CustomerRepository customerRepository;

    @Test
    void shouldCreateCustomer() {
        //given
        Customer customer = DataMaker.makeCustomer();
        when(customerRepository.save(customer))
                .thenReturn(customer);
        //when
        Customer actual = service.createCustomer(customer);

        //then
        Address address = customer.getAddress().get(0);
        doAssertion(actual, customer, address);
    }

    @Test
    void shouldFetchCustomers() {
        //given
        Customer customer = DataMaker.makeCustomer();
        CustomerSearchRequest request = new CustomerSearchRequest("Krishna", null, null, null, null, null);

        when(customerRepository.fetchCustomersBy(request))
                .thenReturn(Collections.singletonList(customer));

        //when
        List<Customer> customers = service.fetchCustomers(request);


        //then
        assertThat(customers).hasSize(1);
        Customer actual = customers.get(0);
        Address address = customer.getAddress().get(0);
        doAssertion(actual, customer, address);
    }

    private static void doAssertion(Customer actual, Customer customer, Address address) {
        assertThat(actual.getId()).isEqualTo(customer.getId());
        assertThat(actual.getFirstName()).isEqualTo(customer.getFirstName());
        assertThat(actual.getLastName()).isEqualTo(customer.getLastName());
        assertThat(actual.getAge()).isEqualTo(customer.getAge());
        assertThat(actual.getMobileNumber()).isEqualTo(customer.getMobileNumber());
        assertThat(customer.getAddress()).hasSize(1);
        Address actualAddress = customer.getAddress().get(0);
        assertThat(actualAddress.getId()).isEqualTo(address.getId());
        assertThat(actualAddress.getAddress1()).isEqualTo(address.getAddress1());
        assertThat(actualAddress.getAddress2()).isEqualTo(address.getAddress2());
        assertThat(actualAddress.getCity()).isEqualTo(address.getCity());
        assertThat(actualAddress.getState()).isEqualTo(address.getState());
        assertThat(actualAddress.getZip()).isEqualTo(address.getZip());
    }
}
