package uz.pdp.olchauzcloneapp.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import uz.pdp.olchauzcloneapp.entity.template.AbsEntity;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "transaction_histories_products")
public class TransactionHistoryProducts extends AbsEntity {

    @ManyToOne
    private TransactionHistory transactionHistory;
    @ManyToOne
    private Product product;
    private int quantity;

}
