package com.demo.customer.controller;

import com.demo.customer.dto.CustomerRequest;
import com.demo.customer.dto.CustomerResponse;
import com.demo.customer.model.Address;
import com.demo.customer.model.Customer;
import com.demo.customer.model.CustomerSearchRequest;
import com.demo.customer.service.CustomerService;
import com.demo.customer.service.MapperService;
import com.demo.customer.util.EndPointConstants;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CustomerController.class)
class CustomerControllerTest extends BaseControllerTest {

    @Autowired
    protected MockMvc mvc;
    @MockBean
    private CustomerService service;
    @MockBean
    private MapperService mapperService;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void shouldCreateCustomers() throws Exception {
        // Given
        Customer customer = DataMaker.makeCustomer();
        CustomerResponse response = DataMaker.makeCustomerResponse();
        Address address = customer.getAddress().get(0);
        HttpHeaders headers = new HttpHeaders();

        when(mapperService.converToCustomer(any(CustomerRequest.class))).thenReturn(customer);
        when(service.createCustomer(any(Customer.class))).thenReturn(customer);
        when(mapperService.converToCustomerResponse(customer)).thenReturn(response);

        // When
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post(EndPointConstants.CUSTOMERS)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .headers(headers)
                .content(objectMapper.writeValueAsString(customer))
                .with(csrf());

        // Then
        mvc.perform(requestBuilder)
                .andExpect(status().isCreated())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.id", equalTo(customer.getId().intValue())))
                .andExpect(jsonPath("$.firstName", equalTo(customer.getFirstName())))
                .andExpect(jsonPath("$.lastName", equalTo(customer.getLastName())))
                .andExpect(jsonPath("$.age", equalTo(customer.getAge().intValue())))
                .andExpect(jsonPath("$.mobileNumber", equalTo(customer.getMobileNumber())))
                .andExpect(jsonPath("$.address", hasSize(1)))
                .andExpect(jsonPath("$.address[0].address1", equalTo(address.getAddress1())))
                .andExpect(jsonPath("$.address[0].address2", equalTo(address.getAddress2())))
                .andExpect(jsonPath("$.address[0].city", equalTo(address.getCity())))
                .andExpect(jsonPath("$.address[0].state", equalTo(address.getState())))
                .andExpect(jsonPath("$.address[0].zip", equalTo(address.getZip())))
                .andReturn();


        //Verify
        verify(service, times(1)).createCustomer(any(Customer.class));
        verifyNoMoreInteractions(service);
    }


    @Test
    void shouldFetchCustomers() throws Exception {
        // Given
        Customer customer = DataMaker.makeCustomer();
        Address address = customer.getAddress().get(0);
        HttpHeaders headers = new HttpHeaders();
        CustomerResponse response = DataMaker.makeCustomerResponse();

        when(service.fetchCustomers(any(CustomerSearchRequest.class))).thenReturn(List.of(customer));
        when(mapperService.converToCustomerResponseList(any())).thenReturn(List.of(response));

        // When
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get(EndPointConstants.CUSTOMERS)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .headers(headers)
                .with(csrf());
        ;

        // Then
        mvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id", equalTo(customer.getId().intValue())))
                .andExpect(jsonPath("$[0].firstName", equalTo(customer.getFirstName())))
                .andExpect(jsonPath("$[0].lastName", equalTo(customer.getLastName())))
                .andExpect(jsonPath("$[0].age", equalTo(customer.getAge().intValue())))
                .andExpect(jsonPath("$[0].mobileNumber", equalTo(customer.getMobileNumber())))
                .andExpect(jsonPath("$[0].address", hasSize(1)))
                .andExpect(jsonPath("$[0].address[0].address1", equalTo(address.getAddress1())))
                .andExpect(jsonPath("$[0].address[0].address2", equalTo(address.getAddress2())))
                .andExpect(jsonPath("$[0].address[0].city", equalTo(address.getCity())))
                .andExpect(jsonPath("$[0].address[0].state", equalTo(address.getState())))
                .andExpect(jsonPath("$[0].address[0].zip", equalTo(address.getZip())))
                .andReturn();

        //Verify
        verify(service, times(1)).fetchCustomers(any(CustomerSearchRequest.class));
        verifyNoMoreInteractions(service);
    }
}
