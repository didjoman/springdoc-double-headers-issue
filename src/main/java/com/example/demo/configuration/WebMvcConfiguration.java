package com.example.demo.configuration;

import io.swagger.v3.core.jackson.mixin.MediaTypeMixin;
import io.swagger.v3.core.jackson.mixin.SchemaMixin;
import io.swagger.v3.oas.models.media.Schema;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

import java.util.Arrays;
import java.util.List;

@Configuration
public class WebMvcConfiguration {

    @Bean
    MappingJackson2HttpMessageConverter getMappingJacksonHttpMessageConverter() {
        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
        converter.setSupportedMediaTypes(List.of(MediaType.APPLICATION_JSON));
        converter.setObjectMapper(new ObjectMapper()
            .setSerializationInclusion(JsonInclude.Include.NON_NULL)
            .addMixIn(io.swagger.v3.oas.models.media.MediaType.class, MediaTypeMixin.class)
            .addMixIn(Schema.class, SchemaMixin.class) // Fixes illegal "exampleSetFlag" property added in Schemas when generating the OpenAPI
        );

        return converter;
    }
}
