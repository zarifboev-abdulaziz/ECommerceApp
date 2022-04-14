package uz.pdp.olchauzcloneapp.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.olchauzcloneapp.common.ApiResponse;
import uz.pdp.olchauzcloneapp.dto.DistrictDto;
import uz.pdp.olchauzcloneapp.entity.address.District;
import uz.pdp.olchauzcloneapp.service.DistrictService;

@RestController
@RequestMapping("/api/district")
@RequiredArgsConstructor
public class DistrictController {

    private final DistrictService districtService;

    @GetMapping
            public HttpEntity<?> allDistrict(){
        ApiResponse apiResponse = districtService.getAllDistrict();
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 404).body(apiResponse);
    }

    @GetMapping("/{districtId}")
    public HttpEntity<?> districtById(@PathVariable Long districtId){
        ApiResponse apiResponse = districtService.getDistrictById(districtId);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 404).body(apiResponse);
    }

    @PostMapping
    public HttpEntity<?> addDistrict(@RequestBody DistrictDto district){
        ApiResponse apiResponse = districtService.addDistrict(district);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 404).body(apiResponse);
    }

    @PutMapping("/{districtId}")
    public HttpEntity<?> editDistrict(@PathVariable Long districtId, @RequestBody DistrictDto district){
        ApiResponse apiResponse = districtService.editDistrict(districtId, district);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 404).body(apiResponse);
    }

    @DeleteMapping("/{districtId}")
    public HttpEntity<?> deleteDistrict(@PathVariable Long districtId){
        ApiResponse apiResponse = districtService.deleteDistrict(districtId);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 404).body(apiResponse);
    }
}
