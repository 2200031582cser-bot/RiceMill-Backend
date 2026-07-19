package com.ricemill.backend.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.io.IOException;
import java.util.UUID;

@RestController
@RequestMapping("/upload")
@CrossOrigin(origins = "*")
public class UploadController {

    @Value("${company.upload.path}")
    private String uploadPath;

    @PostMapping("/company")
    public ResponseEntity<?> uploadCompanyImage(
            @RequestParam("file") MultipartFile file
    ) {

        try {

            if (file.isEmpty()) {
                return ResponseEntity.badRequest()
                        .body("No file selected.");
            }

            File folder = new File(uploadPath);

            if (!folder.exists()) {
                folder.mkdirs();
            }

            String extension = "";

            String originalName = file.getOriginalFilename();

            if (originalName != null && originalName.contains(".")) {
                extension = originalName.substring(originalName.lastIndexOf("."));
            }

            String fileName =
                    UUID.randomUUID().toString() + extension;

            File destination =
                    new File(folder, fileName);

            Files.copy(
                    file.getInputStream(),
                    destination.toPath(),
                    StandardCopyOption.REPLACE_EXISTING
            );

            return ResponseEntity.ok(
                    uploadPath + "/" + fileName
            );

        } catch (Exception e) {

            e.printStackTrace();

            return ResponseEntity.internalServerError()
                    .body(e.getMessage());

        }

    }

}