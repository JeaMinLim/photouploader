package com.jmlim0727.photouploader;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class WebController {
    @Value("${location}")
    private String location;

    @GetMapping("/")
    public String index(Locale locale, Model model) {
        return "redirect:/informationForm";
    }
    
    @GetMapping("/informationForm")
    public String showForm(Locale locale, Model model) {
        return "submitForm";
    }

    @PostMapping("/informationForm")
    public String uploadForm(@RequestParam("guest") String guest,
                             @RequestParam("relation") String relation,
                             @RequestParam("name") String name,
                             Locale locale, Model model) {
        model.addAttribute("guest", guest);
        model.addAttribute("relation", relation);
        model.addAttribute("name", name);
        return "uploadForm";
    }

    @PostMapping("/uploadFiles")
    public ResponseEntity<?> uploadFiles(Locale locale, @RequestParam("path") String path, @RequestParam("files") List<MultipartFile> files) {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HHmmss");
        String formattedNow = now.format(formatter);

        File directory = new File(location + "\\" + path + "_" + formattedNow );
        if (!directory.exists()) {
            directory.mkdirs();
        }

        int totalFiles = files.size();
        int uploadedFiles = 0;

        for (MultipartFile file : files) {
            try {             
                String filePath = location + "\\" + path + "_" + formattedNow + "\\" + file.getOriginalFilename();
                System.out.println("filePath = " + filePath);
                file.transferTo(new File(filePath));
                
                uploadedFiles++;

            } catch (IOException e) {
                e.printStackTrace();
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("File upload failed");
            }
        }
        return ResponseEntity.ok(Map.of("totalFiles", totalFiles, "uploadedFiles", uploadedFiles));
    }
}
