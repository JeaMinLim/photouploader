package com.jmlim0727.photouploader;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class WebController {

    @Value("${photouploader.title}")
    private String title;
    @Value("${photouploader.message}")
    private String message;
    @Value("${location}")
    private String location;

    @GetMapping("/")
    public String index(Model model) {
        return "redirect:/informationForm";
    }
    
    @GetMapping("/informationForm")
    public String showForm(Model model) {
        model.addAttribute("title", title);
        model.addAttribute("message", message);
        return "submitForm";
    }

    @PostMapping("/informationForm")
    public String uploadForm(@RequestParam("guest") String guest,
                             @RequestParam("relation") String relation,
                             @RequestParam("name") String name,
                             Model model) {
        model.addAttribute("guest", guest);
        model.addAttribute("relation", relation);
        model.addAttribute("name", name);

        return "uploadForm";
    }

    @PostMapping("/uploadFiles")
    public String uploadFiles(@RequestParam("path") String path, @RequestParam("files") List<MultipartFile> files, Model model) {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HHmmss");
        String formattedNow = now.format(formatter);

        File directory = new File(location + "\\" + path + "_" + formattedNow );
        if (!directory.exists()) {
            directory.mkdirs();
        }

        for (MultipartFile file : files) {
            try {             
                String filePath = location + "\\" + path + "_" + formattedNow + "\\" + file.getOriginalFilename();
                System.out.println("filePath = " + filePath);
                file.transferTo(new File(filePath));           
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        model.addAttribute("message", "Upload Complete");
        return "uploadStatus";
    }
}
