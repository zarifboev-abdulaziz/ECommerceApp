package uz.pdp.olchauzcloneapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.olchauzcloneapp.common.ApiResponse;
import uz.pdp.olchauzcloneapp.dto.CharacteristicDto;
import uz.pdp.olchauzcloneapp.entity.Characteristic;
import uz.pdp.olchauzcloneapp.entity.CharacteristicCategory;
import uz.pdp.olchauzcloneapp.repository.CharacteristicCategoryRepo;
import uz.pdp.olchauzcloneapp.repository.CharacteristicRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CharacteristicService {

    @Autowired
    CharacteristicRepository characteristicRepository;
    @Autowired
    CharacteristicCategoryRepo characteristicCategoryRepo;

    public ApiResponse getAllCharacteristic() {
        List<Characteristic> characteristics = characteristicRepository.findAll();
        if (characteristics.isEmpty()) {
            return new ApiResponse("Characteristic not found", false);
        }
        return new ApiResponse("Success", true);
    }

    public ApiResponse getCharacteristicById(Long id) {
        Optional<Characteristic> optionalCharacteristic = characteristicRepository.findById(id);
        if (!optionalCharacteristic.isPresent()) {
            return new ApiResponse("Characteristic not found", false);
        }
        Characteristic characteristic = optionalCharacteristic.get();
        return new ApiResponse("Success", true, characteristic);
    }

    public ApiResponse addOrUpdateCharacteristic(CharacteristicDto characteristicDto, Long id) {
        if (id != null) {
            Optional<Characteristic> optionalCharacteristic = characteristicRepository.findById(id);
            if (!optionalCharacteristic.isPresent()) {
                return new ApiResponse("Characteristic not found", false);
            }
            Characteristic characteristic = optionalCharacteristic.get();

            Optional<CharacteristicCategory> optionalCharacteristicCategory = characteristicCategoryRepo.findById(characteristicDto.getCategoryId());
            if (!optionalCharacteristicCategory.isPresent()) {
                return new ApiResponse("CharacteristicCategory not found", false);
            }
            CharacteristicCategory characteristicCategory = optionalCharacteristicCategory.get();

            characteristic.setCategory(characteristicCategory);
            characteristic.setName(characteristicDto.getName());
            characteristicRepository.save(characteristic);
            return new ApiResponse("Success", true);
        } else {

        Characteristic characteristic = new Characteristic();

        Optional<CharacteristicCategory> optionalCharacteristicCategory = characteristicCategoryRepo.findById(characteristicDto.getCategoryId());
        if (!optionalCharacteristicCategory.isPresent()) {
            return new ApiResponse("CharacteristicCategory not found", false);
        }
        CharacteristicCategory characteristicCategory = optionalCharacteristicCategory.get();

        characteristic.setCategory(characteristicCategory);
        characteristic.setName(characteristicDto.getName());
        characteristicRepository.save(characteristic);
        return new ApiResponse("Success", true);
        }
    }

    public ApiResponse deleteCharacteristic(Long id) {
        Optional<Characteristic> optionalCharacteristic = characteristicRepository.findById(id);
        if (!optionalCharacteristic.isPresent()) {
            return new ApiResponse("Characteristic not found", false);
        }
        Characteristic characteristic = optionalCharacteristic.get();
        characteristicRepository.delete(characteristic);
        return new ApiResponse("Success", true);
    }


}
