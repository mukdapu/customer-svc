package com.demo.customer.model;


public record CustomerSearchRequest(String firstName, String lastName, String city, String state, Integer limit,
                                    Integer offset) {
}
