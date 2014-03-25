package org.javaswift.redhatdemo;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Controller;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Configuration for Spring mvc.
 * @author Bas de Vos
 */
@EnableWebMvc
@Configuration
@ComponentScan(basePackageClasses = WebMvcConfig.class, includeFilters = @Filter(Controller.class))
public class WebMvcConfig extends WebMvcConfigurerAdapter {

    /**
     * {@inheritDoc}.
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/css/**").addResourceLocations("/css/");
        registry.addResourceHandler("/images/**").addResourceLocations("/images/");
        registry.addResourceHandler("/favicon.ico").addResourceLocations("/images/favicon.ico");
        registry.addResourceHandler("/script/**").addResourceLocations("/script/");
        registry.addResourceHandler("/fonts/**").addResourceLocations("/fonts/");
        registry.addResourceHandler("/**/*.html").addResourceLocations("/");
    }

    /**
     * {@inheritDoc}.
     */
    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
        converter.setObjectMapper(new ObjectMapper());
        converters.add(converter);
    }

    @Bean
    public MultipartResolver multipartResolver() {
        return new org.springframework.web.multipart.commons.CommonsMultipartResolver();
    }

}
