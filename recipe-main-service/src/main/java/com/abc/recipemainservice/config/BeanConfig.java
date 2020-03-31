package com.abc.recipemainservice.config;

import com.abc.recipemainservice.context.SpringAppContext;
import com.abc.recipemainservice.feign.FeignErrorDecoder;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class BeanConfig {

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Bean
    public ObjectMapper mapper() {
        return new ObjectMapper();
    }

    @Bean
    public SpringAppContext springAppContext() {
        return new SpringAppContext();
    }

    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }

    @Bean
    public FeignErrorDecoder feignErrorDecoder(){
        return new FeignErrorDecoder();
    }
}
