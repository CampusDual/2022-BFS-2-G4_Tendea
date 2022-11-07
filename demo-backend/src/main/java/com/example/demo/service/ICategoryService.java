package com.example.demo.service;

import java.util.List;

import com.example.demo.dto.CategoryDTO;
import com.example.demo.dto.ContactDTO;

public interface ICategoryService {

	/**
	 * Obtiene todas las categorias de la bd
	 * @return
	 */
    List<CategoryDTO> findAll();
    
    /**
     * Obtiene una categoria por el id indicado
     * @param id
     * @return categoryDTO
     */
    CategoryDTO getCategory(Integer id);
}
