package com.jmlim0727.photouploader;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class WebController {
    
    @Value("${photouploader.title}")
    private String title;
    @Value("${photouploader.message}")
    private String message;

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


    
}
