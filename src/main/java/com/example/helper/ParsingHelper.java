package com.example.helper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

public class ParsingHelper {
    private ModelMapper modelMapper;

    @Autowired
    public ParsingHelper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public <T> T mapObject(Object source, Class<T> resultType) {
        return modelMapper.map(source, resultType);
    }

}
