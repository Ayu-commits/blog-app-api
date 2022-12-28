package com.blog.blogappapi.services.impl;

import com.blog.blogappapi.Exceptions.ResourceNotFoundException;
import com.blog.blogappapi.entities.Category;

import com.blog.blogappapi.payloads.CategoryDto;
import com.blog.blogappapi.repositories.CategoryRepo;
import com.blog.blogappapi.services.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepo categoryRepo;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public CategoryDto createCategory(CategoryDto categoryDto) {
        Category cat = modelMapper.map(categoryDto,Category.class);
        Category addedCat = categoryRepo.save(cat);
        return modelMapper.map(addedCat,CategoryDto.class);
    }

    @Override
    public CategoryDto updateCategory(CategoryDto categoryDto, Integer categoryId) {
        Category cat = this.categoryRepo.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("Category","Category Id",categoryId));
        cat.setCategoryTitle(categoryDto.getCategoryTitle());
        cat.setCategoryDescription(categoryDto.getCategoryDescription());
        Category updatedCategory=categoryRepo.save(cat);
        return modelMapper.map(updatedCategory,CategoryDto.class);
    }

    @Override
    public void deleteCategory(Integer categoryId) {
        Category cat = categoryRepo.findById(categoryId).orElseThrow(()->    new ResourceNotFoundException("Category","Category Id",categoryId));
        categoryRepo.delete(cat);
    }

    @Override
    public CategoryDto getCategory(Integer categoryId) {
        Category cat = categoryRepo.findById(categoryId).orElseThrow(()->    new ResourceNotFoundException("Category","Category Id",categoryId));
        return modelMapper.map(cat,CategoryDto.class);
    }

    @Override
    public List<CategoryDto> getCategories() {
        List<Category> categories = categoryRepo.findAll();
        List<CategoryDto> categoryDtos = categories.stream().map((cat)->modelMapper.map(cat,CategoryDto.class)).
                collect(Collectors.toList());
        return categoryDtos;
    }
}
