package com.example.demo.service;

import java.util.List;

import com.example.demo.dto.CategoryDTO;
import com.example.demo.dto.ContactDTO;

public interface ICategoryService {

    List<CategoryDTO> findAll();
}
