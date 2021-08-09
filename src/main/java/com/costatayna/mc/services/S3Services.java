package com.costatayna.mc.services;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;

@Service
public class S3Services {
	
	private Logger LOG = LoggerFactory.getLogger(S3Services.class);

	@Autowired
	private AmazonS3 s3client;
	
	@Value("${s3.bucket}")
	private String bucketName;
	
	public URI uploadFile(MultipartFile multipartfile) {
		try {
			String fileName = multipartfile.getOriginalFilename();
			InputStream is = multipartfile.getInputStream();
			String contentType = multipartfile.getContentType();
			return uploadFile(is, fileName, contentType);
		} 
		catch (IOException e) {
			throw new RuntimeException("Erro de IO" + e.getMessage());
		}
		
	}
	
	public URI uploadFile(InputStream is, String fileName, String ContentType) {
		try {
			ObjectMetadata meta = new ObjectMetadata();
			meta.setContentType(ContentType);
			LOG.info("Iniciando upload");
			s3client.putObject(bucketName, fileName, is, meta);
			LOG.info("Upload finalizado");
			return s3client.getUrl(bucketName, fileName).toURI();
		} 
		catch (URISyntaxException e) {
			throw new RuntimeException("Erro ao converter URL a URI");
		}
	}
	
	
}
