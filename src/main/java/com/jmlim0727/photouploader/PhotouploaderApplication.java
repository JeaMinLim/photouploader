package com.jmlim0727.photouploader;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PhotouploaderApplication implements CommandLineRunner {

	@Value("${location}")
    private String LOCATION;

	public static void main(String[] args) {
		SpringApplication.run(PhotouploaderApplication.class, args);
	}

	@Override
    public void run(String... args) throws Exception {
        if (LOCATION == null || LOCATION.trim().isEmpty()) {
            System.err.println("Error: --LOCATION is required.");
            System.exit(1); 
        }
        System.out.println("--LOCATION : " + LOCATION);
        
        Path PATH = Paths.get(LOCATION);
        if(Files.exists(PATH) && Files.isDirectory(PATH) && Files.isReadable(PATH)) {
            System.out.println("Directory is accessible.");
        } else {
            System.err.println("Error: Directory is not accessible.");
            System.exit(1);
        }
    }
}
