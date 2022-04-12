package uz.pdp.olchauzcloneapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.olchauzcloneapp.common.ApiResponse;
import uz.pdp.olchauzcloneapp.dto.CharacteristicDto;
import uz.pdp.olchauzcloneapp.service.CharacteristicService;

@RestController
@RequestMapping("/api/characteristic")
public class CharacteristicController {

    @Autowired
    CharacteristicService characteristicService;

    @GetMapping
    public HttpEntity<?> getAllCharacteristic(){
        ApiResponse allCharacteristic = characteristicService.getAllCharacteristic();
        return ResponseEntity.status(allCharacteristic.isSuccess()?200:404).body(allCharacteristic);
    }

    @GetMapping("/{id}")
    public HttpEntity<?> getCharacteristicById(@PathVariable Long id){
        ApiResponse characteristicById = characteristicService.getCharacteristicById(id);
        return ResponseEntity.status(characteristicById.isSuccess()?200:404).body(characteristicById);
    }

    @PostMapping
    public HttpEntity<?> addCharacteristic(@RequestBody CharacteristicDto characteristicDto,
                                           Long id){
        ApiResponse apiResponse = characteristicService.addOrUpdateCharacteristic(characteristicDto, id);
        return ResponseEntity.status(apiResponse.isSuccess()?200:404).body(apiResponse);
    }

    @PutMapping("/{id}")
    public HttpEntity<?> updateCharacteristic(@RequestBody CharacteristicDto characteristicDto,
                                              @PathVariable Long id){
        ApiResponse apiResponse = characteristicService.addOrUpdateCharacteristic(characteristicDto, id);
        return ResponseEntity.status(apiResponse.isSuccess()?200:404).body(apiResponse);
    }

    @DeleteMapping("/{id}")
    public HttpEntity<?> deleteCharacteristic(@PathVariable Long id){
        ApiResponse apiResponse = characteristicService.deleteCharacteristic(id);
        return ResponseEntity.status(apiResponse.isSuccess()?200:404).body(apiResponse);
    }


}
