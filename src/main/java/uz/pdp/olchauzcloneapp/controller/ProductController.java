package uz.pdp.olchauzcloneapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.olchauzcloneapp.common.ApiResponse;
import uz.pdp.olchauzcloneapp.dto.ProductDto;
import uz.pdp.olchauzcloneapp.entity.enums.Authority;
import uz.pdp.olchauzcloneapp.service.ProductService;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/product")
public class ProductController {
    @Autowired
    ProductService productService;


    @GetMapping
    public HttpEntity<?> getAllProducts() {
        ApiResponse apiResponse = productService.getAllProducts();
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 404).body(apiResponse);
    }


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
    public HttpEntity<?> getProductById(
            @PathVariable Long productId) {
        ApiResponse apiResponse = productService.getProductById(productId);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);

    }

    @GetMapping("/view/product/full-description/{productId}")
    public HttpEntity<?> getProductFullDescription(
            @PathVariable Long productId) {
        ApiResponse apiResponse = productService.getProductFullDescription(productId);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

    @GetMapping("/view/product/full-characteristics/{productId}")
    public HttpEntity<?> getProductFullCharacteristics(
            @PathVariable Long productId) {
        ApiResponse apiResponse = productService.getProductFullCharacteristics(productId);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }


    @PostMapping
    public HttpEntity<?> addProduct(@RequestBody ProductDto productDto, Long id) {
        ApiResponse apiResponse = productService.addOrUpdateProduct(productDto, id);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 404).body(apiResponse);
    }

    @PutMapping("/{id}")
    public HttpEntity<?> updateProduct(@RequestBody ProductDto productDto, @PathVariable Long id) {
        ApiResponse apiResponse = productService.addOrUpdateProduct(productDto, id);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 404).body(apiResponse);
    }

    @DeleteMapping("/{id}")
    public HttpEntity<?> deleteProduct(@PathVariable Long id) {
        ApiResponse apiResponse = productService.deleteProduct(id);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 404).body(apiResponse);
    }

    @GetMapping("/view/product/price/{categoryId}")
    public HttpEntity<?> getProductsByPrice(
            @PathVariable Long categoryId,
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "10") Integer size,
            Integer startingPrice,
            Integer finalPrice) {

        ApiResponse productByPrice = productService.getProductByPrice(page, size, categoryId, startingPrice, finalPrice);
        return ResponseEntity.status(productByPrice.isSuccess() ? 200 : 400).body(productByPrice);
    }

}


