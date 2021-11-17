package com.example.todolist.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration
public class WebApiConfiguration
{
    @Bean
    public WebMvcConfigurer corsConfigurer()
    {
        return new WebMvcConfigurer()
        {
            @Override
            public void addCorsMappings( CorsRegistry registry )
            {
                registry.addMapping( "/**" )
                        //.allowedOrigins("http://localhost:9001")
                        .allowedOrigins( "*" )
                        .allowedMethods( "GET", "POST", "PUT", "DELETE" )
                        .maxAge( 3600 );
            }
        };
    }
}