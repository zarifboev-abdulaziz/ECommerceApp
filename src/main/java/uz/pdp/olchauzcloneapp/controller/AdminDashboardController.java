package uz.pdp.olchauzcloneapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.pdp.olchauzcloneapp.common.ApiResponse;
import uz.pdp.olchauzcloneapp.projection.AdminDashboardProjection;
import uz.pdp.olchauzcloneapp.repository.ProductRepository;

// Zuhridin Bakhriddinov 4/14/2022 4:42 PM
@RestController
@RequestMapping("/api/dashboard")
public class AdminDashboardController {
    @Autowired
    ProductRepository productRepository;

    @GetMapping
    public HttpEntity<?> getAdminDashboard() {
        AdminDashboardProjection adminDashboard = productRepository.getAdminDashboard();
        return new ResponseEntity(new ApiResponse("Success",
                true, adminDashboard), HttpStatus.OK);
    }
}
