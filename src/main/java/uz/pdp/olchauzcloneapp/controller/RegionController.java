package uz.pdp.olchauzcloneapp.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.olchauzcloneapp.common.ApiResponse;
import uz.pdp.olchauzcloneapp.entity.address.Region;
import uz.pdp.olchauzcloneapp.service.RegionService;

@RestController
@RequestMapping("/api/region")
@RequiredArgsConstructor
public class RegionController {

    private final RegionService regionService;


    @GetMapping
    public HttpEntity<?> allRegion() {
        ApiResponse apiResponse = regionService.getAllRegion();
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 404).body(apiResponse);
    }

    @GetMapping("/{regionId}")
    public HttpEntity<?> regionById(@PathVariable Long regionId) {
        ApiResponse apiResponse = regionService.getRegionById(regionId);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 404).body(apiResponse);
    }

    @PostMapping
    public HttpEntity<?> addRegion(@RequestBody Region region) {
        ApiResponse apiResponse = regionService.addRegion(region);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 404).body(apiResponse);
    }

    @PutMapping("/{regionId}")
    public HttpEntity<?> editRegion(@PathVariable Long regionId, @RequestBody Region region) {
        ApiResponse apiResponse = regionService.editRegion(regionId, region);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 404).body(apiResponse);
    }

    @DeleteMapping("/{regionId}")
    public HttpEntity<?> deleteRegion(@PathVariable Long regionId) {
        ApiResponse apiResponse = regionService.deleteRegion(regionId);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 404).body(apiResponse);
    }
}
