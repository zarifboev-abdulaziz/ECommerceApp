package uz.pdp.olchauzcloneapp.controller;

//Asilbek Fayzullayev 12.04.2022 9:18   

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import uz.pdp.olchauzcloneapp.common.ApiResponse;
import uz.pdp.olchauzcloneapp.service.AttachmentService;

import java.io.IOException;

@RestController
@RequestMapping("/api/attachment")
public class AttachmentController {

    @Autowired
    AttachmentService attachmentService;

    @PostMapping("/upload")
    public HttpEntity<?> fileUpload(MultipartFile file) throws IOException {
        ApiResponse apiResponse = attachmentService.fileUpload(file);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 400).body(apiResponse);
    }

    @GetMapping("/{attachmentId}")
    public HttpEntity<?> downloadFile(@PathVariable Long attachmentId) {
        return attachmentService.fileDownload(attachmentId);
    }

    @DeleteMapping("/{id}")
    public HttpEntity<?> deleteFile(@PathVariable Long id) {
        ApiResponse delete = attachmentService.delete(id);
        return ResponseEntity.status(delete.isSuccess() ? 200 : 400).body(delete);
    }
}
