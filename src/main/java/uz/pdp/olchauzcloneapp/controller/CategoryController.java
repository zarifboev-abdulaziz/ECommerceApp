package uz.pdp.olchauzcloneapp.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.olchauzcloneapp.common.ApiResponse;
import uz.pdp.olchauzcloneapp.dto.CategoryDto;
import uz.pdp.olchauzcloneapp.service.CategoryService;

@RestController
@RequestMapping("/api/category")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping
    public HttpEntity<?> allCategories(){
        ApiResponse apiResponse = categoryService.getAllCategories();
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 404).body(apiResponse);
    }

    @GetMapping("/{categoryId}")
    public HttpEntity<?> getCategoryById(@PathVariable Long categoryId){
        ApiResponse apiResponse = categoryService.getCategoryById(categoryId);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 404).body(apiResponse);
    }

    @GetMapping("/getParentId/{parentId}")
    public HttpEntity<?> getCategoryByParentId(@PathVariable Long parentId){
        ApiResponse categoryByParentId = categoryService.getCategoryByParentId(parentId);
        return ResponseEntity.status(categoryByParentId.isSuccess() ? 200 : 404).body(categoryByParentId);
    }

    @PostMapping
    public HttpEntity<?> addCategory(@RequestBody CategoryDto categoryDto){
        ApiResponse apiResponse = categoryService.addCategory(categoryDto);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 404).body(apiResponse);
    }

    @PutMapping("/{categoryId}")
    public HttpEntity<?> editCategory(@PathVariable Long categoryId, @RequestBody CategoryDto categoryDto){
        ApiResponse apiResponse = categoryService.editCategory(categoryId, categoryDto);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 404).body(apiResponse);
    }

    @DeleteMapping("/{categoryId}")
    public HttpEntity<?> deleteCategory(@PathVariable Long categoryId){
        ApiResponse apiResponse = categoryService.deleteCategory(categoryId);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 404).body(apiResponse);
    }
}
