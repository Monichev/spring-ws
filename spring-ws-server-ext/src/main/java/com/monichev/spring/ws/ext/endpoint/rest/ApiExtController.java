package com.monichev.spring.ws.ext.endpoint.rest;

import java.util.List;

import com.monichev.spring.ws.ext.service.ApiExtService;
import com.monichev.spring.ws.api_ext.ApiExt;
import com.monichev.spring.ws.api_ext.types.TypeExt;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/spring-rs/api-ext")
class ApiExtController implements ApiExt {

    private static final Logger LOGGER = LoggerFactory.getLogger(ApiExtController.class);

    private final ApiExtService apiExtService;

    @Autowired
    public ApiExtController(ApiExtService apiExtService) {
        this.apiExtService = apiExtService;
    }

    @GetMapping("/getTypeExtList")
    @Override
    public List<TypeExt> getTypeExtList() {
        LOGGER.debug("getTypeExtList");
        return apiExtService.getTypeExtList();
    }
}
