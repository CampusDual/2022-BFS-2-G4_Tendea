package com.example.demo.dto;

import java.util.List;

import javax.validation.constraints.NotEmpty;

import com.example.demo.entity.Product;
import com.example.demo.utils.Constant;

public class CategoryDTO {
    private Integer id;
    
    @NotEmpty(message = Constant.NAME_REQUIRED)
    private String name;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
}
