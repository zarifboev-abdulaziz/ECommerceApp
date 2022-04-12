package uz.pdp.olchauzcloneapp.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.apache.catalina.LifecycleState;
import uz.pdp.olchauzcloneapp.entity.template.AbsEntity;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "products")
public class Product extends AbsEntity {
    private String name;
    private String shortDescription;

    private double price;
    private float warrantyPeriodMonth;
    private double discount;
    @ManyToMany
    private List<Attachment> photos;
    @ManyToOne
    private Attachment coverImage;
    @ManyToMany
    private List<CharacteristicsValues> characteristicsValues;
    @ManyToOne
    private Category category;



}
