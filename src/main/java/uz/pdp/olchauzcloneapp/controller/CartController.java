package uz.pdp.olchauzcloneapp.controller;

//Asilbek Fayzullayev 12.04.2022 12:08   

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.pdp.olchauzcloneapp.common.ApiResponse;
import uz.pdp.olchauzcloneapp.service.CartService;

@RestController
@RequestMapping("/api/cart")
public class CartController {

    @Autowired
    CartService cartService;

    @GetMapping
    public HttpEntity<?> getAllCart() {
        ApiResponse allCart = cartService.getAllCart();
        return ResponseEntity.status(allCart.isSuccess() ? 200 : 400).body(allCart);
    }

    @DeleteMapping
    public HttpEntity<?> clearCart() {
        ApiResponse apiResponse = cartService.clearCart();
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 400).body(apiResponse);
    }
}
