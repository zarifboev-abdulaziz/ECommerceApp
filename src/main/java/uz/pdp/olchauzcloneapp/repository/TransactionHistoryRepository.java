package uz.pdp.olchauzcloneapp.repository;

//Asilbek Fayzullayev 12.04.2022 9:20   

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.olchauzcloneapp.entity.PayType;
import uz.pdp.olchauzcloneapp.entity.TransactionHistory;

public interface TransactionHistoryRepository extends JpaRepository<TransactionHistory,Long> {
}
