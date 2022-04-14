package uz.pdp.olchauzcloneapp.controller;

//Asilbek Fayzullayev 12.04.2022 12:08   

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.olchauzcloneapp.common.ApiResponse;
import uz.pdp.olchauzcloneapp.dto.CartDto;
import uz.pdp.olchauzcloneapp.dto.OrderItemDto;
import uz.pdp.olchauzcloneapp.service.CartService;

@RestController
@RequestMapping("/api/cart")
public class CartController {

    @Autowired
    CartService cartService;

    @GetMapping
    public HttpEntity<?> getAllProductInTheCart() {
        ApiResponse allCart = cartService.getAllCart();
        return ResponseEntity.status(allCart.isSuccess() ? 200 : 400).body(allCart);
    }


    @GetMapping("/ordered-products")
    public HttpEntity<?> getOrderedProducts() {
        ApiResponse allCart = cartService.getOrderedProducts();
        return ResponseEntity.status(allCart.isSuccess() ? 200 : 400).body(allCart);
    }

    @DeleteMapping("/{id}")
    public HttpEntity<?> clearAllProductsInTheCart(@PathVariable Long id) {
        ApiResponse apiResponse = cartService.clearCart(id);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 400).body(apiResponse);
    }

    @PostMapping("/product")
    public HttpEntity<?> addProductToCart(@RequestBody CartDto cartDto) {
        ApiResponse apiResponse = cartService.addProductToCart(cartDto);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 400).body(apiResponse);

    }

    @PostMapping("/product/quantity")
    public HttpEntity<?> setProductQuantity(@RequestBody OrderItemDto orderItemDto) {
        ApiResponse apiResponse = cartService.setProductQuantity(orderItemDto);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 400).body(apiResponse);

    }


}
