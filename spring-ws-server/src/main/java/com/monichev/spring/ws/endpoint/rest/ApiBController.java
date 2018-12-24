package com.monichev.spring.ws.endpoint.rest;

import java.util.List;

import com.monichev.spring.ws.api_b.ApiB;
import com.monichev.spring.ws.api_b.types.TypeB;
import com.monichev.spring.ws.service.ApiBService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/spring-rs/api-b")
class ApiBController implements ApiB {

    private static final Logger LOGGER = LoggerFactory.getLogger(ApiBController.class);

    private final ApiBService apiBService;

    @Autowired
    public ApiBController(ApiBService apiBService) {
        this.apiBService = apiBService;
    }

    @GetMapping("/getTypeBList")
    @Override
    public List<TypeB> getTypeBList() {
        LOGGER.debug("getTypeBList");
        return apiBService.getTypeBList();
    }
}
