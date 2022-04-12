package uz.pdp.olchauzcloneapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.olchauzcloneapp.common.ApiResponse;
import uz.pdp.olchauzcloneapp.repository.ProductRepository;
import uz.pdp.olchauzcloneapp.service.ProductService;

@RestController
@RequestMapping("/api/product")
public class ProductController {
    @Autowired
    ProductService productService;

    @GetMapping("/view/{categoryId}")
    public HttpEntity<?> getProductsByCategory(
            @PathVariable Long categoryId,
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "10") Integer size,
            @RequestParam(defaultValue = "") String search) {

        ApiResponse apiResponse = productService.getProductsByCategory(page, size, categoryId, search);

        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

    @GetMapping("/view/product/{productId}")
    public HttpEntity<?> getProductsById(
            @PathVariable Long productId) {
        ApiResponse apiResponse = productService.getProductsById(productId);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);

    }

}


