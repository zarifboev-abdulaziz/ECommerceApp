package uz.pdp.olchauzcloneapp.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uz.pdp.olchauzcloneapp.common.ApiResponse;
import uz.pdp.olchauzcloneapp.dto.DistrictDto;
import uz.pdp.olchauzcloneapp.entity.address.District;
import uz.pdp.olchauzcloneapp.entity.address.Region;
import uz.pdp.olchauzcloneapp.repository.DistrictRepository;
import uz.pdp.olchauzcloneapp.repository.RegionRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class DistrictService {

    private final DistrictRepository districtRepository;
    private final RegionRepository regionRepository;


    public ApiResponse getAllDistrict() {
        List<District> allDistrict = districtRepository.findAll();
        if (allDistrict.isEmpty()) {
            return new ApiResponse("District not found!", false);
        }
        return new ApiResponse("Success!", true, allDistrict);
    }


    public ApiResponse getDistrictById(Long districtId) {
        Optional<District> districtOptional = districtRepository.findById(districtId);
        return districtOptional.map(district -> new ApiResponse("Success", true, district))
                .orElseGet(() -> new ApiResponse("District not found!", false));
    }


    public ApiResponse addDistrict(DistrictDto district) {
        Optional<Region> optionalRegion = regionRepository.findById(district.getRegionId());
        if (!optionalRegion.isPresent()) {
            return new ApiResponse("Region Not Found!", false);
        }
        District districtByName = districtRepository.findDistrictByName(district.getName());
        if (districtByName != null){
            return new ApiResponse("A district with this name exists", false);
        }
        try {
            Region region = optionalRegion.get();
            District newDistrict = new District();

            newDistrict.setName(district.getName());
            newDistrict.setRegion(region);

            districtRepository.save(newDistrict);

            return new ApiResponse("Success!", true, newDistrict);
        } catch (Exception e) {
            return new ApiResponse("Error!", false);
        }
    }

    public ApiResponse editDistrict(Long districtId, DistrictDto district) {
        Optional<District> optionalDistrict = districtRepository.findById(districtId);
        if (!optionalDistrict.isPresent()) {
            return new ApiResponse("District Not found!", false);
        }
        try {
            Optional<Region> optionalRegion = regionRepository.findById(district.getRegionId());
            Region region = optionalRegion.get();
            District newDistrict = optionalDistrict.get();

            newDistrict.setName(district.getName());
            newDistrict.setRegion(region);

            districtRepository.save(newDistrict);

            return new ApiResponse("Success!", true, newDistrict);
        } catch (Exception e) {
            return new ApiResponse("Error!", false);
        }
    }


    public ApiResponse deleteDistrict(Long districtId) {
        Optional<District> optionalDistrict = districtRepository.findById(districtId);
        if (!optionalDistrict.isPresent()) {
            return new ApiResponse("District Not Found", false);
        }
        try {
            districtRepository.delete(optionalDistrict.get());
            return new ApiResponse("Success!", true);
        } catch (Exception e) {
            return new ApiResponse("Error", false);
        }
    }
}
