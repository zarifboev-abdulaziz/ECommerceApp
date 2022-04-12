package uz.pdp.olchauzcloneapp.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.olchauzcloneapp.common.ApiResponse;
import uz.pdp.olchauzcloneapp.dto.StreetDto;
import uz.pdp.olchauzcloneapp.entity.address.Street;
import uz.pdp.olchauzcloneapp.service.StreetService;
import uz.pdp.olchauzcloneapp.util.Constants;

@RestController
@RequestMapping("/api/street")
@RequiredArgsConstructor
public class StreetController {

    private final StreetService streetService;


    @GetMapping
    public HttpEntity<?> allStreet(){
        ApiResponse apiResponse = streetService.getAllStreet();
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 404).body(apiResponse);
    }

    @GetMapping("/{streetId}")
    public HttpEntity<?> streetById(@PathVariable Long streetId){
        ApiResponse apiResponse = streetService.getStreetById(streetId);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 404).body(apiResponse);
    }

    @GetMapping("/getStreetByPage")
    public HttpEntity<?> streetByPage(@RequestParam (name = "page", defaultValue = Constants.DEFAULT_PAGE_NUMBER) int page,
                                      @RequestParam (name = "size", defaultValue = Constants.DEFAULT_PAGE_SIZE) int size
    ){
        ApiResponse apiResponse = streetService.getStreetByPage(page, size);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 404).body(apiResponse);
    }

    @PostMapping
    public HttpEntity<?> addStreet(@RequestBody StreetDto street){
        ApiResponse apiResponse = streetService.addStreet(street);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 404).body(apiResponse);
    }

    @PutMapping("/{streetId}")
    public HttpEntity<?> editStreet(@PathVariable Long streetId, @RequestBody StreetDto street){
        ApiResponse apiResponse = streetService.editStreet(streetId, street);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 404).body(apiResponse);
    }

    @DeleteMapping("/{streetId}")
    public HttpEntity<?> deleteStreet(@PathVariable Long streetId){
        ApiResponse apiResponse = streetService.deleteStreet(streetId);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 404).body(apiResponse);
    }
}
