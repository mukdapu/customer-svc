package com.demo.customer.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
public class CustomerResponse {
    private Long id;
    private String firstName;
    private String lastName;
    private Short age;
    private Double spendingLimit;
    private String mobileNumber;
    private List<AddressResponse> address = new ArrayList<>();
}
