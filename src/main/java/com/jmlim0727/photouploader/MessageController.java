package com.jmlim0727.photouploader;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MessageController {
    @Autowired
    private MessageSource messageSource;

    @GetMapping("/messages/{code}")
    public String getMessage(@PathVariable String code, Locale locale) {
        return messageSource.getMessage(code, null, locale);
    }
}
