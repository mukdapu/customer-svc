package com.demo.customer.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;


@Data
public class CustomerRequest {
    @NotBlank(message = "cus-1")
    private String firstName;
    @NotBlank(message = "cus-2")
    private String lastName;
    private Short age;
    private Double spendingLimit;
    private String mobileNumber;
    private List<AddressRequest> address = new ArrayList<>();
}
