package uz.pdp.olchauzcloneapp.service;

//Asilbek Fayzullayev 12.04.2022 9:21   

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.olchauzcloneapp.common.ApiResponse;
import uz.pdp.olchauzcloneapp.entity.PayType;
import uz.pdp.olchauzcloneapp.repository.PayTypeRepository;

import javax.servlet.annotation.WebServlet;
import java.util.List;
import java.util.Optional;

@Service
public class PayTypeService {

    @Autowired
    PayTypeRepository payTypeRepository;

    public ApiResponse getAllPayType() {
        List<PayType> payTypeList = payTypeRepository.findAll();
        if (payTypeList.size() == 0) {
            return new ApiResponse("List Empty", false);
        }
        return new ApiResponse("Success", true, payTypeList);
    }

    public ApiResponse getPayTypeById(Long id) {
        Optional<PayType> optionalPayType = payTypeRepository.findById(id);
        if (!optionalPayType.isPresent()) {
            return new ApiResponse("PayType not found", false);
        }
        return new ApiResponse("Success", true, optionalPayType);
    }

    public ApiResponse addPayType(PayType payType) {
        try {
            PayType save = payTypeRepository.save(payType);
            return new ApiResponse("Successfully added", true, save);
        } catch (Exception e) {
            return new ApiResponse("Error", false);
        }
    }

    public ApiResponse editPayType(PayType payType, Long id) {
        Optional<PayType> optionalPayType = payTypeRepository.findById(id);
        if (!optionalPayType.isPresent()) {
            return new ApiResponse("PayType not found", false);
        }
        PayType editPayType = optionalPayType.get();
        payType.setName(editPayType.getName());
        payType.setCommissionFee(editPayType.getCommissionFee());
        PayType save = payTypeRepository.save(payType);
        return new ApiResponse("Successfully saved", true, save);
    }

    public ApiResponse delete(Long id){
        try {
            payTypeRepository.deleteById(id);
            return new ApiResponse("Deleted",true);
        }catch (Exception e){
            return new ApiResponse("Error",false);
        }
    }


}
