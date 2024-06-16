package com.demo.customer.enums;


import java.util.Arrays;

public enum AddressType {

    HOME((short) 1),
    OFFICE((short) 2);

    private Short value;

    AddressType(Short value) {
        this.value = value;
    }

    public static AddressType get(Short value) {
        return Arrays.stream(values()).
                filter(sts -> sts.value.equals(value))
                .findAny().orElse(null);
    }

    public Short type() {
        return this.value;
    }

}
