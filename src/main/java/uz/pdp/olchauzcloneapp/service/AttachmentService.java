package uz.pdp.olchauzcloneapp.service;

//Asilbek Fayzullayev 12.04.2022 9:16   

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import uz.pdp.olchauzcloneapp.common.ApiResponse;
import uz.pdp.olchauzcloneapp.entity.Attachment;
import uz.pdp.olchauzcloneapp.entity.AttachmentContent;
import uz.pdp.olchauzcloneapp.repository.AttachmentContentRepository;
import uz.pdp.olchauzcloneapp.repository.AttachmentRepository;

import java.io.IOException;
import java.util.Optional;

@Service
public class AttachmentService {

    @Autowired
    AttachmentRepository attachmentRepository;
    @Autowired
    AttachmentContentRepository attachmentContentRepository;

    public ApiResponse fileUpload(MultipartFile file) throws IOException {
        Attachment attachment = attachmentRepository.save(new Attachment(file.getOriginalFilename(), file.getContentType(), file.getSize()));
        attachmentContentRepository.save(new AttachmentContent(file.getBytes(), attachment));
        return new ApiResponse("Successfully uploaded", true);
    }

    public ResponseEntity<ByteArrayResource> fileDownload(Long attachmentId) {
        AttachmentContent byAttachmentId = attachmentContentRepository.findByAttachmentId(attachmentId);
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(byAttachmentId.getAttachment().getContentType()))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=\"" + byAttachmentId.getAttachment().getOriginalName() + "\"")
                .body(new ByteArrayResource(byAttachmentId.getBytes()));
    }

    public ApiResponse delete(Long id) {
        Optional<Attachment> optionalAttachment = attachmentRepository.findById(id);
        if (!optionalAttachment.isPresent()) {
            return new ApiResponse("Attachment not found", false);
        }
        AttachmentContent attachmentContent = attachmentContentRepository.getById(id);
        attachmentContentRepository.deleteById(attachmentContent.getId());
        attachmentRepository.deleteById(id);
        return new ApiResponse("Successfully deleted", true);
    }
}
