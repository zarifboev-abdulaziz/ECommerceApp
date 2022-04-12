package uz.pdp.olchauzcloneapp.controller;

//Asilbek Fayzullayev 12.04.2022 9:22   

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.olchauzcloneapp.common.ApiResponse;
import uz.pdp.olchauzcloneapp.entity.FavouriteItem;
import uz.pdp.olchauzcloneapp.entity.PayType;
import uz.pdp.olchauzcloneapp.repository.FavouriteItemRepository;
import uz.pdp.olchauzcloneapp.service.FavouriteItemService;
import uz.pdp.olchauzcloneapp.service.PayTypeService;

@RestController
@RequestMapping("/api/favouriteItem")
public class FavouriteItemController {

    @Autowired
    FavouriteItemService favouriteItemService;

    @GetMapping
    public HttpEntity<?> getAllFavouriteItem() {
        ApiResponse allFavouriteItem = favouriteItemService.getAllFavouriteItem();
        return ResponseEntity.status(allFavouriteItem.isSuccess() ? 200 : 400).body(allFavouriteItem);
    }

    @GetMapping("/{id}")
    public HttpEntity<?> getFavouriteItemById(@PathVariable Long id) {
        ApiResponse apiResponse = favouriteItemService.getFavouriteItemById(id);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 400).body(apiResponse);
    }

    @PostMapping("/{productId}")
    private HttpEntity<?> addFavouriteItem(@PathVariable Long productId) {
        ApiResponse apiResponse = favouriteItemService.addFavouriteItem(productId);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 400).body(apiResponse);
    }


    @DeleteMapping("/{id}")
    public HttpEntity<?> delete(@PathVariable Long id) {
        ApiResponse delete = favouriteItemService.delete(id);
        return ResponseEntity.status(delete.isSuccess() ? 200 : 400).body(delete);
    }
}
