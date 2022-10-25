package com.example.demo.rest.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.CategoryDTO;
import com.example.demo.dto.ContactDTO;
import com.example.demo.service.ICategoryService;
import com.example.demo.service.IContactService;

@CrossOrigin(origins = {"http://localhost:4201"})
@RestController
@RequestMapping(CategoriesController.REQUEST_MAPPING)
public class CategoriesController {
    
    public static final String REQUEST_MAPPING = "categories";
    private static final Logger LOGGER = LoggerFactory.getLogger(CategoriesController.class);

    @Autowired
    private ICategoryService categoryService;
    
    /**
     * Devuelve todos los categoriass que se encuentran en la tabla
     * 
     * @return categorias que alguno de sus campos contenga la 'query'
     *         independientemente de las mayúsculas.
     * @since 0.0.5
     */
    @GetMapping(path = "/getCategories")
    // @PreAuthorize("hasAnyAuthority('CONTACTS')")
    public @ResponseBody List<CategoryDTO> findAll() {
        LOGGER.info("findAll in progress...");
        return categoryService.findAll();
    }
}
