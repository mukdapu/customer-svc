package com.demo.customer;

import com.demo.customer.controller.BaseControllerIT;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class CustomerApplicationTests extends BaseControllerIT {

    @Test
    void contextLoads() {
        Assertions.assertDoesNotThrow(this::doNotThrowException);
    }

    private void doNotThrowException() {
        //This method will never throw exception
    }
}
