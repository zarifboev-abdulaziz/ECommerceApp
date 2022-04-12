package uz.pdp.olchauzcloneapp.repository;

//Asilbek Fayzullayev 12.04.2022 9:20   

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.olchauzcloneapp.entity.TransactionHistory;
import uz.pdp.olchauzcloneapp.entity.TransactionHistoryProducts;

public interface TransactionHistoryProductsRepository extends JpaRepository<TransactionHistoryProducts,Long> {
}
