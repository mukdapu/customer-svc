package com.demo.customer.dto;

import com.demo.customer.enums.AddressType;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


@Data
public class AddressRequest {
    @NotNull(message = "cus-3")
    private AddressType type;
    @NotBlank(message = "cus-4")
    private String address1;
    private String address2;
    @NotBlank(message = "cus-5")
    private String city;
    @NotBlank(message = "cus-6")
    private String state;
    @NotNull
    @NotBlank(message = "cus-7")
    private String zip;
}
