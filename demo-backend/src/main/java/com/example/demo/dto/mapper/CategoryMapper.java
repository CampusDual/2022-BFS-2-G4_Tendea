package com.example.demo.dto.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.example.demo.dto.CategoryDTO;
import com.example.demo.entity.Category;

@Mapper
public interface CategoryMapper {
    CategoryMapper INSTANCE = Mappers.getMapper( CategoryMapper.class );
    
    CategoryDTO categoryToCategoryDto(Category category);
    
    List<CategoryDTO> categoryToCategoryDtoList(List<Category> category);
    
    Category categoryDTOtoCategory(CategoryDTO categorytdto);
}
