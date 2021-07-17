package com.adventureincpod.springmagicshoppe.webserver.app;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfiguration implements WebMvcConfigurer {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/{spring:\\[^(api)]}")
                .setViewName("forward:/");
        registry.addViewController("/{spring:\\[^(api)]}/{spring:\\w+}")
                .setViewName("forward:/");
        registry.addViewController("/{spring:\\[^(api)]}/**{spring:?!(\\.js|\\.css)$}")
                .setViewName("forward:/");
    }
}
