package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.CategoryDTO;
import com.example.demo.dto.mapper.CategoryMapper;
import com.example.demo.dto.mapper.ContactMapper;
import com.example.demo.entity.Category;
import com.example.demo.entity.Contact;
import com.example.demo.repository.CategoryRepository;
import com.example.demo.repository.ContactRepository;

@Service
public class CategoryServiceImpl  extends AbstractDemoService implements ICategoryService {

    @Autowired
    private CategoryRepository categoryRepository;
    
    @Override
    public List<CategoryDTO> findAll() {
        List<Category> lCategories = categoryRepository.findAll();
        return CategoryMapper.INSTANCE.categoryToCategoryDtoList(lCategories);
    }

	@Override
	public CategoryDTO getCategory(Integer id) {
		Category category = categoryRepository.findById(id).orElse(null);
		return CategoryMapper.INSTANCE.categoryToCategoryDto(category);
	}

	/**
	 * Devuelve una categoria por el id
	 */
	@Override
	public Category findById(Integer id) {
		return categoryRepository.findById(id).orElse(null);
	}
	
	

}
