package com.demo.customer.model;


import com.demo.customer.enums.AddressType;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
class AddressTypeConverter implements AttributeConverter<AddressType, Short> {

    @Override
    public Short convertToDatabaseColumn(AddressType attribute) {
        return attribute.type();
    }

    @Override
    public AddressType convertToEntityAttribute(Short value) {
        return AddressType.get(value);
    }

}
