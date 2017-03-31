package org.example.organizer.api;

import org.h2.server.web.WebServlet;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DatabaseViewerConfiguration {
	@Bean
	public ServletRegistrationBean h2ViewerServlet() {
		ServletRegistrationBean s = new ServletRegistrationBean();
		s.setServlet(new WebServlet());
		s.addUrlMappings("/console/*");
		return s;
	}
}
