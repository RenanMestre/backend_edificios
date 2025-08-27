package com.api.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.http.CacheControl;

import java.util.concurrent.TimeUnit;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/css/**", "/js/**", "/images/**")
            .addResourceLocations("classpath:/static/css/", "classpath:/static/js/", "classpath:/static/images/")
            .setCacheControl(CacheControl.maxAge(0, TimeUnit.SECONDS).cachePrivate());
    }

    // ✅ Aqui é onde você configura o CORS
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/api/**") // ou "/*" para todas as rotas
            .allowedOrigins("https://renanmestre.github.io") // substitua com seu domínio do GitHub Pages
            .allowedMethods("GET", "POST", "PUT", "DELETE")
            .allowedHeaders("*");
    }
}
