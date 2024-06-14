/*
package com.jayfox.wigell_padel.configurations;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Configuration
public class TimeDateConfig {

    private static final String DATETIME_FORMAT = "yyyy-MM-dd'T'HH:mm";
    private static final DateTimeFormatter DATETIME_FORMATTER = DateTimeFormatter.ofPattern(DATETIME_FORMAT);

    @Bean
    public ObjectMapper objectMapper() {
        ObjectMapper mapper = new ObjectMapper();
        JavaTimeModule module = new JavaTimeModule();
        module.addSerializer(LocalDateTime.class, new LocalDateTimeSerializer(DATETIME_FORMATTER));
        module.addDeserializer(LocalDateTime.class, new LocalDateTimeDeserializer(DATETIME_FORMATTER));
        mapper.registerModule(module);
        return mapper;
    }
}

*/
