package com.upl.upl_survey;

import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
@MapperScan("com.upl.upl_survey.Dao")
public class UplSurveyServerApplication extends SpringBootServletInitializer {
public static Logger logger= LoggerFactory.getLogger(UplSurveyServerApplication.class);


	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(UplSurveyServerApplication.class);
	}

	public static void main(String[] args) {
		logger.info("Starting application");

		SpringApplication.run(UplSurveyServerApplication.class, args);
	}
}
