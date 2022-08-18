package com.example.demo.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.models.OpenAPI;

import org.springdoc.core.AbstractRequestService;
import org.springdoc.core.GenericResponseService;
import org.springdoc.core.OpenAPIService;
import org.springdoc.core.OperationService;
import org.springdoc.core.SpringDocConfigProperties;
import org.springdoc.core.SpringDocProviders;
import org.springdoc.core.customizers.OpenApiCustomiser;
import org.springdoc.core.customizers.OperationCustomizer;
import org.springdoc.core.customizers.RouterOperationCustomizer;
import org.springdoc.core.filters.OpenApiMethodFilter;
import org.springdoc.webmvc.api.OpenApiWebMvcResource;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

import java.util.List;
import java.util.Locale;
import java.util.Optional;

import static org.springdoc.core.Constants.API_DOCS_URL;

@RestController
public class CustomOpenApiWebMvcResource extends OpenApiWebMvcResource {

    public CustomOpenApiWebMvcResource(ObjectFactory<OpenAPIService> openAPIBuilderObjectFactory, AbstractRequestService requestBuilder, GenericResponseService responseBuilder, OperationService operationParser,
        Optional<List<OperationCustomizer>> operationCustomizers, Optional<List<OpenApiCustomiser>> openApiCustomisers, Optional<List<RouterOperationCustomizer>> routerOperationCustomizers,
        Optional<List<OpenApiMethodFilter>> methodFilters,
        SpringDocConfigProperties springDocConfigProperties,
        SpringDocProviders springDocProviders) {
        super(openAPIBuilderObjectFactory, requestBuilder, responseBuilder, operationParser, operationCustomizers, openApiCustomisers, routerOperationCustomizers, methodFilters, springDocConfigProperties,
            springDocProviders);
    }

    @Operation(hidden = true)
    @GetMapping(value = "/doc", produces = MediaType.APPLICATION_JSON_VALUE)
    public OpenAPI openapiJsonAsRealJson(HttpServletRequest request, @Value(API_DOCS_URL) String apiDocsUrl, Locale locale) {
        calculateServerUrl(request, apiDocsUrl, locale);
        return this.getOpenApi(locale);
    }
}