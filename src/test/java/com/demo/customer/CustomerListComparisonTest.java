package com.demo.customer;

import com.demo.customer.model.Customer;
import lombok.extern.apachecommons.CommonsLog;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@CommonsLog
public class CustomerListComparisonTest {

    public static void main(String[] args) {

        // Customer Test data
        List<Customer> customerListA = List.of(makeData(1L, "Bill", "Carson"),
                makeData(2L, "Adam", "Sandler"),
                makeData(3L, "Clint", "Eastwood"));
        List<Customer> customerListListB = List.of(makeData(2L, "Adam", "Sandler"),
                makeData(4L, "Joe", "Biden"),
                makeData(5L, "Eddy", "Murphy"),
                makeData(6L, "James", "Bond"));

        // Customers in A
        List<Customer> onlyInListA = new ArrayList<>(customerListA);
        onlyInListA.removeAll(customerListListB);

        // Customers in B
        List<Customer> onlyInListB = new ArrayList<>(customerListListB);
        onlyInListB.removeAll(customerListA);

        // Customers in both A and B
        Set<Customer> inBothSet = new HashSet<>(customerListA);
        inBothSet.addAll(onlyInListB);
        List<Customer> inBothLists = inBothSet.stream().toList();

        log.info("Customers only in list A: " + onlyInListA);
        log.info("Customers only in list B: " + onlyInListB);
        log.info("Customers in both list A and list B: " + inBothLists);
    }

    private static Customer makeData(Long id, String firstName, String lastName) {
        Customer customer = new Customer();
        customer.setId(id);
        customer.setFirstName(firstName);
        customer.setLastName(lastName);
        return customer;
    }
}
