package uz.pdp.olchauzcloneapp.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import uz.pdp.olchauzcloneapp.entity.template.AbsEntity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToOne;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "attachment_content")
@OnDelete(action = OnDeleteAction.CASCADE)
public class AttachmentContent extends AbsEntity {
    private byte[] bytes;

    @OneToOne(cascade = CascadeType.MERGE)
    private Attachment attachment;
}
