package uz.pdp.olchauzcloneapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.olchauzcloneapp.payload.ProductRateDto;
import uz.pdp.olchauzcloneapp.repository.ProductRateRepository;
import uz.pdp.olchauzcloneapp.service.ProductRateService;

// Zuhridin Bakhriddinov 4/12/2022 9:58 PM
@RestController
@RequestMapping("/api/productRate")
public class ProductRateController {
    @Autowired
    ProductRateService productRateService;

    @PostMapping
    private HttpEntity<?> addProductRate(@RequestBody ProductRateDto productRateDto) {
        return productRateService.addProductRate(productRateDto);
    }
    @DeleteMapping("/{id}")
    private HttpEntity<?> deleteProductRate(@PathVariable Long id) {
        return productRateService.deleteProductRate(id);
    }

    @GetMapping("/{id}")
    private HttpEntity<?> getProductRate(@PathVariable Long id) {
        return productRateService.getProductRate(id);
    }


}
