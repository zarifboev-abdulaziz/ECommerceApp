package uz.pdp.olchauzcloneapp.controller;

//Asilbek Fayzullayev 12.04.2022 9:22   

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.olchauzcloneapp.common.ApiResponse;
import uz.pdp.olchauzcloneapp.entity.PayType;
import uz.pdp.olchauzcloneapp.service.PayTypeService;

@RestController
@RequestMapping("/api/payType")
public class PayTypeController {

    @Autowired
    PayTypeService payTypeService;

    @GetMapping
    public HttpEntity<?> getAllPayType() {
        ApiResponse allPayType = payTypeService.getAllPayType();
        return ResponseEntity.status(allPayType.isSuccess() ? 200 : 400).body(allPayType);
    }

    @GetMapping("/{id}")
    public HttpEntity<?> getPayTypeById(@PathVariable Long id) {
        ApiResponse payTypeById = payTypeService.getPayTypeById(id);
        return ResponseEntity.status(payTypeById.isSuccess() ? 200 : 400).body(payTypeById);
    }

    @PostMapping
    private HttpEntity<?> addPayType(@RequestBody PayType payType) {
        ApiResponse apiResponse = payTypeService.addPayType(payType);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 400).body(apiResponse);
    }

    @PutMapping("/{id}")
    public HttpEntity<?> editPayTape(@PathVariable Long id, @RequestBody PayType payType) {
        ApiResponse apiResponse = payTypeService.editPayType(payType, id);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 400).body(apiResponse);
    }

    @DeleteMapping("/{id}")
    public HttpEntity<?> delete(@PathVariable Long id) {
        ApiResponse delete = payTypeService.delete(id);
        return ResponseEntity.status(delete.isSuccess() ? 200 : 400).body(delete);
    }
}
