package com.demo.customer.dto;

import com.demo.customer.enums.AddressType;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class AddressResponse {
    private Long id;
    private AddressType type;
    private String address1;
    private String address2;
    private String city;
    private String state;
    private String zip;
}
