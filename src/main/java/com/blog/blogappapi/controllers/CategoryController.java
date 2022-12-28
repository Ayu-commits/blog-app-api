package com.blog.blogappapi.controllers;

import com.blog.blogappapi.payloads.ApiResponse;
import com.blog.blogappapi.payloads.CategoryDto;
import com.blog.blogappapi.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.websocket.server.PathParam;
import java.util.List;

@RestController
@RequestMapping("api/categories")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;
    //create
    @PostMapping("/")
    public ResponseEntity<CategoryDto> createCategory(@Valid @RequestBody CategoryDto categoryDto){
            CategoryDto createCategory = categoryService.createCategory(categoryDto);
            return new ResponseEntity<CategoryDto>(createCategory, HttpStatus.CREATED);
    }
    //Update
    @PutMapping("/{categoryId}")
    public ResponseEntity<CategoryDto> updateCategory(@Valid @RequestBody CategoryDto categoryDto, @PathVariable Integer categoryId)
    {
        CategoryDto updatedCategory = categoryService.updateCategory(categoryDto,categoryId);
        return new ResponseEntity<CategoryDto>(updatedCategory, HttpStatus.OK);
    }

    //delete
    @DeleteMapping("/{catId}")
    public ResponseEntity<ApiResponse> deleteCategory( @PathVariable Integer catId)
    {
        categoryService.deleteCategory(catId);
        return new ResponseEntity<ApiResponse>(new ApiResponse("Category is deleted successfully!!",true),
                HttpStatus.OK);
    }
    // getById
    @GetMapping("/{catId}")
    public ResponseEntity<CategoryDto> getCategoryByID( @PathVariable Integer catId)
    {
        CategoryDto categoryDto = categoryService.getCategory(catId);
        return new ResponseEntity<CategoryDto>(categoryDto,HttpStatus.OK);
    }
    //getAll
    @GetMapping("/")
    public ResponseEntity<List<CategoryDto>> getCategory( )
    {
        List<CategoryDto> categories = categoryService.getCategories();
        return ResponseEntity.ok(categories);
    }
}
