package com.lw.activitidemo;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ImportResource;
import tk.mybatis.spring.annotation.MapperScan;
import tk.mybatis.spring.mapper.MapperScannerConfigurer;

import java.util.Properties;

@ImportResource(value="classpath:activiti.cfg.xml")
@SpringBootApplication
@MapperScan(basePackages = "com.lw.activitidemo.dao")
public class ActivitidemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(ActivitidemoApplication.class, args);
	}

	@Bean
	public MapperScannerConfigurer create(){
		MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();
		mapperScannerConfigurer.setBasePackage("com.lw.activitidemo.dao");
		Properties properties = new Properties();
		properties.setProperty("mappers","tk.mybatis.mapper.common.Mapper");
		mapperScannerConfigurer.setProperties(properties);
		return mapperScannerConfigurer;
	}
}
