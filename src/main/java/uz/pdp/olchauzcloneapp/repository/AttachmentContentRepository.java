package uz.pdp.olchauzcloneapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.olchauzcloneapp.entity.AttachmentContent;

public interface AttachmentContentRepository extends JpaRepository<AttachmentContent,Long> {
    AttachmentContent findByAttachmentId(Long attachmentId);
}
