package com.corn.market.common.fileUpload;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

@Configuration
public class FileUploadConfig {
	
	@Bean
	public CommonsMultipartResolver multipartResolver() {
		CommonsMultipartResolver commonsMultipartResolver = new CommonsMultipartResolver();
		commonsMultipartResolver.setDefaultEncoding("UTF-8"); // 인코딩 설정
		commonsMultipartResolver.setMaxUploadSize(50 * 1024 * 1024); // 파일 용량 설정
		commonsMultipartResolver.setMaxInMemorySize(50 * 1024 * 1024); // (100 * 1024 * 1024) 100MB
		return commonsMultipartResolver;
	}

}
