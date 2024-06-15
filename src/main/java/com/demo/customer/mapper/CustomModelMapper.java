package com.demo.customer.mapper;

import org.modelmapper.ModelMapper;

/**
 * This class was added to handle a case when source object to null. We will be
 * returning destination as null.
 */
public class CustomModelMapper extends ModelMapper {
    @Override
    public <D> D map(Object source, Class<D> destinationType) {
        if (source == null) {
            return null;
        }
        return super.map(source, destinationType);
    }
}
