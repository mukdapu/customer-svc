package com.demo.customer.controller;

import com.demo.customer.dto.CustomerRequest;
import com.demo.customer.dto.CustomerResponse;
import com.demo.customer.model.Customer;
import com.demo.customer.util.EndPointConstants;
import org.assertj.core.api.AssertionsForClassTypes;
import org.assertj.core.groups.Tuple;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;


class CustomerControllerIT extends BaseControllerIT {

    @Autowired
    private TestRestTemplate restClient;

    @BeforeEach
    public void setUp() {
        super.setUp();
    }


    @Test
    void shouldCreateCustomer() {
        //given
        CustomerRequest request = DataMaker.makeCustomerRequest();
        HttpHeaders headers = new HttpHeaders();

        //when
        final ResponseEntity<CustomerResponse> response = restClient.exchange(
                EndPointConstants.CUSTOMERS,
                HttpMethod.POST,
                new HttpEntity<>(request, headers),
                CustomerResponse.class
        );

        //then
        AssertionsForClassTypes.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(response).isNotNull();
        assertThat(response.getBody()).extracting("firstName", "lastName", "age", "spendingLimit", "mobileNumber")
                .containsExactlyInAnyOrder(request.getFirstName(), request.getLastName(), request.getAge(), request.getSpendingLimit(), request.getMobileNumber());
    }

    @Test
    void shouldFailToCreateCustomer() {
        //given
        CustomerRequest request = DataMaker.makeCustomerRequest();
        request.setFirstName(null); // wrong input
        HttpHeaders headers = new HttpHeaders();

        //when
        final ResponseEntity<CustomerResponse> response = restClient.exchange(
                EndPointConstants.CUSTOMERS,
                HttpMethod.POST,
                new HttpEntity<>(request, headers),
                CustomerResponse.class
        );

        //then
        AssertionsForClassTypes.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
    }


    @Test
    void shouldFetchCustomers() {
        //given
        Customer customer = DataMaker.makeCustomer();
        customer.getAddress().forEach(address -> address.setCustomer(customer));
        customerRepository.save(customer);

        TestRestTemplate restTemplate = getTestRestTemplate();
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<List<CustomerResponse>> entity = new HttpEntity<>(null, headers);
        UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(createURLWithPort(EndPointConstants.CUSTOMERS))
                .queryParam("firstName", customer.getFirstName());

        //when
        ResponseEntity<List<CustomerResponse>> response = restTemplate.exchange(builder.toUriString(), HttpMethod.GET,
                entity, new ParameterizedTypeReference<List<CustomerResponse>>() {
                });

        //then
        AssertionsForClassTypes.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response).isNotNull();
        assertThat(response.getBody()).hasSize(1).extracting("id", "firstName", "lastName", "age", "spendingLimit", "mobileNumber")
                .containsExactlyInAnyOrder(Tuple.tuple(customer.getId(), customer.getFirstName(), customer.getLastName(), customer.getAge(), customer.getSpendingLimit(), customer.getMobileNumber()));
    }


    @Test
    void shouldFetchNoCustomers() {
        //given
        Customer customer = DataMaker.makeCustomer();
        customer.getAddress().forEach(address -> address.setCustomer(customer));
        customerRepository.save(customer);

        TestRestTemplate restTemplate = getTestRestTemplate();
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<List<CustomerResponse>> entity = new HttpEntity<>(null, headers);

        UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(createURLWithPort(EndPointConstants.CUSTOMERS))
                .queryParam("firstName", "invalid value");

        //when
        ResponseEntity<List<CustomerResponse>> response = restTemplate.exchange(builder.toUriString(), HttpMethod.GET,
                entity, new ParameterizedTypeReference<List<CustomerResponse>>() {
                });

        //then
        AssertionsForClassTypes.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response).isNotNull();
        assertThat(response.getBody()).isEmpty();
    }
}
