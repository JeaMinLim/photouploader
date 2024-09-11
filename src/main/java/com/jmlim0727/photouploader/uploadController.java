package com.jmlim0727.photouploader;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

public class uploadController {

    @Value("${sharedlocation}")
    private String UPLOAD_DIR;

    public String handleFileUpload(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return "upload";
        }

        try {
            // 파일을 네트워크 공유 폴더에 저장
            Path path = Paths.get(UPLOAD_DIR + file.getOriginalFilename());
            Files.copy(file.getInputStream(), path);
            model.addAttribute("message", "File uploaded successfully: " + file.getOriginalFilename());
        } catch (IOException e) {
            model.addAttribute("message", "Failed to upload file: " + e.getMessage());
        }
        return "upload";
    }
}
