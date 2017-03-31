package org.example.organizer.api;

import org.springframework.context.annotation.Configuration;
import org.springframework.hateoas.config.EnableHypermediaSupport;
import org.springframework.hateoas.config.EnableHypermediaSupport.HypermediaType;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@EnableHypermediaSupport(type = HypermediaType.HAL)
@Configuration
public class WebConfiguration extends WebMvcConfigurerAdapter {
}
