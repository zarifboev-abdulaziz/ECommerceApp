package uz.pdp.olchauzcloneapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.olchauzcloneapp.entity.FavouriteItem;

public interface FavouriteItemRepository extends JpaRepository<FavouriteItem, Long> {
}
