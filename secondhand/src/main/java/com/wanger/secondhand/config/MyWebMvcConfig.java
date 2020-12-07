package com.wanger.secondhand.config;


import com.wanger.secondhand.util.StaticUtils;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MyWebMvcConfig  implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler(("/"+ StaticUtils.PREFIX +"/**"))
                .addResourceLocations("file:\\E:\\image\\");

    }
}
