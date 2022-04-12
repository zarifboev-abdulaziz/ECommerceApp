package uz.pdp.olchauzcloneapp.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uz.pdp.olchauzcloneapp.common.ApiResponse;
import uz.pdp.olchauzcloneapp.entity.address.Region;

import uz.pdp.olchauzcloneapp.repository.RegionRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class RegionService {

    private final RegionRepository regionRepository;

    public ApiResponse getAllRegion() {
        List<Region> allRegion = regionRepository.findAll();
        if (allRegion.isEmpty()) {
            return new ApiResponse("Region Not Found", false);
        }
        return new ApiResponse("Success!", true, allRegion);
    }

    public ApiResponse getRegionById(Long regionId) {
        Optional<Region> byId = regionRepository.findById(regionId);
        return byId.map(region -> new ApiResponse("Success!", false, region))
                .orElseGet(() -> new ApiResponse("Region Not Found!", false));
    }

    public ApiResponse addRegion(Region region) {
        Region regionByName = regionRepository.findRegionByName(region.getName());
        if (regionByName != null){
            return new ApiResponse("A region with this name exists", false);
        }
        try {
            Region newRegion = new Region();
            newRegion.setName(region.getName());
            regionRepository.save(newRegion);
            return new ApiResponse("SuccessQ", true, newRegion);
        } catch (Exception e) {
            return new ApiResponse("Error!", false);
        }
    }

    public ApiResponse editRegion(Long regionId, Region editRegion) {
        Optional<Region> byId = regionRepository.findById(regionId);
        if (!byId.isPresent()) {
            return new ApiResponse("Region Not Found!", false);
        }
        try {
            Region newRegion = byId.get();
            newRegion.setName(editRegion.getName());
            regionRepository.save(newRegion);
            return new ApiResponse("Success!", true, newRegion);
        } catch (Exception e) {
            return new ApiResponse("Error!", false);
        }
    }

    public ApiResponse deleteRegion(Long regionId) {
        Optional<Region> byId = regionRepository.findById(regionId);
        if (!byId.isPresent()) {
            return new ApiResponse("Region Not Found!", false);
        }
        try {
            regionRepository.delete(byId.get());
            return new ApiResponse("Success!", true);
        } catch (Exception e) {
            return new ApiResponse("Error!", false);
        }
    }
}
