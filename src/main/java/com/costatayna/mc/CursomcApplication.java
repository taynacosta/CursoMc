package com.costatayna.mc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.costatayna.mc.services.S3Services;

@SpringBootApplication
public class CursomcApplication implements CommandLineRunner {
	
	@Autowired
	private S3Services s3Services;
	
	
	public static void main(String[] args) {
		SpringApplication.run(CursomcApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		s3Services.uploadFile("C:\\temp\\fotos\\imagem1.jpg");
		
	}

}
