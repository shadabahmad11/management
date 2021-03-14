package com.asset.management.serviceImpl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.ArrayList;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.asset.management.bean.Category;
import com.asset.management.repository.CategoryRepository;
import com.asset.management.service.CategoryService;
import com.asset.management.utility.Constant;

@SpringBootTest
public class CategoryServiceTest {

	@Mock
	CategoryRepository categoryRepository;
	
	@InjectMocks
    private CategoryService categoryService = new CategoryServiceImpl();
	
	@DisplayName("Test Mock saveCategory when categoryName is unique")
    @Test
    void saveCategory() {
		Category category=new Category();
		category.setCategoryName("Test");
        assertEquals(Constant.ADDED_SUCCESSFULLY, categoryService.saveCategory(category));
    }
	
	@DisplayName("Test Mock saveCategory when categoryName is duplicate")
    @Test
    void saveCategoryDuplicate() {
		Category category=new Category();
		category.setCategoryName("Test");
		Mockito.when(categoryRepository.findByCategoryName(category.getCategoryName())).thenReturn(new Category());
        assertEquals(Constant.DUPLICATE_CATEGORY, categoryService.saveCategory(category));
    }
	
	@DisplayName("Test Mock updateCategory when categoryId is empty")
    @Test
    void updateCategoryWhenCategoryIdNull() {
		Category category=new Category();
        assertEquals(Constant.ID_MISSING, categoryService.updateCategory(category));
    }
	
	@DisplayName("Test Mock updateCategory when categoryName Null")
    @Test
    void updateCategoryWhenCategoryNameNull() {
		Category category=new Category();
		category.setCategoryId(1);
        assertEquals(Constant.CATEGORY_NAME_MISSING, categoryService.updateCategory(category));
    }
	
	@DisplayName("Test Mock updateCategory when categoryName is duplicate")
    @Test
    void updateCategoryWhenCategoryNameIsDuplicate() {
		Category category=new Category();
		category.setCategoryId(1);
		category.setCategoryName("Test");
		Category temp=new Category();
		temp.setCategoryId(1);
		Mockito.when(categoryRepository.findByCategoryName(category.getCategoryName())).thenReturn(temp);
        assertEquals(Constant.ADDED_SUCCESSFULLY, categoryService.updateCategory(category));
    }
	
	@DisplayName("Test Mock updateCategory when categoryName is duplicate")
    @Test
    void updateCategoryWhenCategoryNameIsNotDuplicate() {
		Category category=new Category();
		Mockito.when(categoryRepository.findByCategoryName(category.getCategoryName())).thenReturn(null);
        assertEquals(Constant.DUPLICATE_CATEGORY, categoryService.updateCategory(category));
    }
	
	@DisplayName("Test Mock list Category")
    @Test
    void listCategory() {
		Mockito.when(categoryRepository.findAll()).thenReturn(new ArrayList<>());
        assertNotNull(categoryService.listCategory());
    }
}
