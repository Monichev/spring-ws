package com.monichev.spring.ws.endpoint.rest;

import java.util.List;

import com.monichev.spring.ws.api_a.ApiA;
import com.monichev.spring.ws.api_a.types.TypeA;
import com.monichev.spring.ws.service.ApiAService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/spring-rs/api-a")
class ApiAController implements ApiA {

    private static final Logger LOGGER = LoggerFactory.getLogger(ApiAController.class);

    private final ApiAService apiAService;

    @Autowired
    public ApiAController(ApiAService apiAService) {
        this.apiAService = apiAService;
    }

    @GetMapping("/getTypeAList")
    @Override
    public List<TypeA> getTypeAList() {
        LOGGER.debug("getTypeAList");
        return apiAService.getTypeAList();
    }

    @PostMapping("/addTypeA")
    @Override
    public void addTypeA(@RequestBody TypeA typeA) {
        LOGGER.debug("addTypeA");
        apiAService.addTypeA(typeA);
    }

    @PostMapping("/removeTypeA")
    @Override
    public TypeA removeTypeA(@RequestParam long id) {
        LOGGER.debug("removeTypeA");
        return apiAService.removeTypeA(id);
    }
}
