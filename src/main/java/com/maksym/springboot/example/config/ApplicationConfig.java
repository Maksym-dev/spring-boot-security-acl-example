package com.maksym.springboot.example.config;

import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.http.converter.ByteArrayHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.http.converter.xml.SourceHttpMessageConverter;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.client.DefaultResponseErrorHandler;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import static java.util.Arrays.asList;

/**
 * Java configuration for application.
 */
@Profile("default")
@Configuration
@EnableTransactionManagement
public class ApplicationConfig {

    private static class OurWebMvcConfigurerAdapter extends WebMvcConfigurerAdapter {


        @Override
        public void addCorsMappings(CorsRegistry registry) {
            registry.addMapping("/**").allowedMethods("*");
        }

        @Override
        public void addResourceHandlers(ResourceHandlerRegistry registry) {
            registry.addResourceHandler("/dist/**").addResourceLocations("classpath:/META-INF/resources/WEB-INF/dist/");
            registry.addResourceHandler("/img/**").addResourceLocations("classpath:/META-INF/resources/WEB-INF/img/");
            registry.addResourceHandler("/fonts/**").addResourceLocations("classpath:/META-INF/resources/WEB-INF/fonts/");

        }

    }

    /**
     * Gets java time module.
     *
     * @return the java time module
     */
    @Bean
    public Module getJavaTimeModule() {
        return new JavaTimeModule();
    }

    /**
     * Rest template.
     *
     * @return the rest template
     */
    @Bean
    public RestTemplate restTemplate() {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.setMessageConverters(asList(
                new SourceHttpMessageConverter(),
                new MappingJackson2HttpMessageConverter(),
                new ByteArrayHttpMessageConverter()));
        return restTemplate;
    }

    /**
     * Default error handler.
     *
     * @return the default response error handler
     */
    @Bean
    public DefaultResponseErrorHandler defaultErrorHandler() {
        return new DefaultResponseErrorHandler();
    }

    /**
     * Property sources placeholder configurer.
     *
     * @return the property sources placeholder configurer
     */
    //To resolve ${} in @Value
    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }

    /**
     * Web configuration.
     *
     * @return the web mvc configuration
     */
    @Bean
    public WebMvcConfigurer webConfigurer() {
        return new OurWebMvcConfigurerAdapter();
    }
}
