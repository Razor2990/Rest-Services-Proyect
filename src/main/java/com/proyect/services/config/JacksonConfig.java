package com.proyect.services.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Configuration
public class JacksonConfig {
//
//    @Bean
//    ObjectMapper objectMapper() {
//        ObjectMapper objectMapper = new ObjectMapper();
//        SimpleModule module = new SimpleModule();
//        module.addDeserializer(LocalDate.class, new LocalDateDeserializer(DateTimeFormatter.ofPattern("yyyy/MM/dd")));
//        objectMapper.registerModule(module);
//        return objectMapper;
//    }
}
