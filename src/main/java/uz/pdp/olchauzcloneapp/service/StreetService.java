package uz.pdp.olchauzcloneapp.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uz.pdp.olchauzcloneapp.common.ApiResponse;
import uz.pdp.olchauzcloneapp.dto.StreetDto;
import uz.pdp.olchauzcloneapp.entity.address.District;
import uz.pdp.olchauzcloneapp.entity.address.Street;
import uz.pdp.olchauzcloneapp.entity.enums.Authority;
import uz.pdp.olchauzcloneapp.repository.DistrictRepository;
import uz.pdp.olchauzcloneapp.repository.StreetRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class StreetService {

    private final StreetRepository streetRepository;
    private final DistrictRepository districtRepository;

    public ApiResponse getAllStreet() {
        List<Street> allStreet = streetRepository.findAll();
        if (allStreet.isEmpty()) {
            return new ApiResponse("Street not found", false);
        }
        return new ApiResponse("Success!", true, allStreet);
    }


    public ApiResponse getStreetById(Long streetId) {
        Optional<Street> optionalStreet = streetRepository.findById(streetId);
        return optionalStreet.map(street -> new ApiResponse("Success!", true, street))
                .orElseGet(() -> new ApiResponse("Street not found!", false));
    }


    public ApiResponse getStreetByPage(int page, int size) {
        Pageable pageable = PageRequest.of(page - 1, size);
        Page<Street> streetPage = streetRepository.findAll(pageable);
        if (streetPage.getSize() == 0) {
            return new ApiResponse("List empty!", false);
        }
        return new ApiResponse("Success", true, streetPage);
    }


    public ApiResponse addStreet(StreetDto street) {
        Optional<District> optionalDistrict = districtRepository.findById(street.getDistrictId());
        if (!optionalDistrict.isPresent()) {
            return new ApiResponse("District not found", false);
        }
        Street streetByName = streetRepository.findStreetByName(street.getName());
        if (streetByName != null) {
            return new ApiResponse("A street with this name exists", false);
        }
        try {
            District district = optionalDistrict.get();
            Street newStreet = new Street();

            newStreet.setDistrict(district);
            newStreet.setName(street.getName());
            newStreet.setApartmentNumber(street.getApartmentNumber());
            newStreet.setFlatNumber(street.getFlatNumber());
            newStreet.setEntranceNumber(street.getEntranceNumber());
            newStreet.setFloor(street.getFloor());

            streetRepository.save(newStreet);

            return new ApiResponse("Success!", true, newStreet);
        } catch (Exception e) {
            return new ApiResponse("Error!", false);
        }
    }

    public ApiResponse editStreet(Long streetId, StreetDto street) {
        Optional<Street> optionalStreet = streetRepository.findById(streetId);
        if (!optionalStreet.isPresent()) {
            return new ApiResponse("Street not found!", false);
        }
        try {
            Optional<District> optionalDistrict = districtRepository.findById(street.getDistrictId());
            District district = optionalDistrict.get();
            Street newStreet = optionalStreet.get();

            newStreet.setName(street.getName());
            newStreet.setApartmentNumber(street.getApartmentNumber());
            newStreet.setFlatNumber(street.getFlatNumber());
            newStreet.setEntranceNumber(street.getEntranceNumber());
            newStreet.setFloor(street.getFloor());
            newStreet.setDistrict(district);

            streetRepository.save(newStreet);
            return new ApiResponse("Success!", true, newStreet);
        } catch (Exception e) {
            return new ApiResponse("Error!", false);
        }
    }

    public ApiResponse deleteStreet(Long streetId) {
        Optional<Street> optionalStreet = streetRepository.findById(streetId);
        if (!optionalStreet.isPresent()) {
            return new ApiResponse("Street not found!", false);
        }
        try {

            streetRepository.delete(optionalStreet.get());

            return new ApiResponse("Success!", true);
        } catch (Exception e) {
            return new ApiResponse("Error", false);
        }
    }


}
