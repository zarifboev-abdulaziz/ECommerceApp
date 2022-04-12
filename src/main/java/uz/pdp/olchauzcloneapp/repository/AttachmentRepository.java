package uz.pdp.olchauzcloneapp.repository;

import lombok.Lombok;
import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.olchauzcloneapp.entity.Attachment;

public interface AttachmentRepository extends JpaRepository<Attachment, Long> {
}
