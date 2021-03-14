package com.asset.management.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.asset.management.bean.Category;
import com.asset.management.service.CategoryService;
import com.asset.management.utility.Constant;

@RestController
@RequestMapping("/category")
public class CategoryController {

	@Autowired
	CategoryService categoryService;
	
	@PostMapping("/add")
	public ResponseEntity<String> addCategory(@RequestBody Category category) {
		
		String result=categoryService.saveCategory(category);
		return new ResponseEntity<>(result,HttpStatus.OK);
	}
	
	@PutMapping("/update")
	public ResponseEntity<String> updateCategory(@RequestBody Category category) {
		
		String result=categoryService.updateCategory(category);
		return new ResponseEntity<>(result,HttpStatus.OK);
	}
	
	@PostMapping("/list")
	public ResponseEntity<List<?>> listCategory() {
		
		return new ResponseEntity<>(categoryService.listCategory(),HttpStatus.OK);
	}
}
