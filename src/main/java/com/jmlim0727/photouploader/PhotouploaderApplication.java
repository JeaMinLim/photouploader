package com.jmlim0727.photouploader;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;


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
            System.exit(1); // 애플리케이션 종료
        }

        System.out.println("--LOCATION : " + LOCATION);
    }
}
