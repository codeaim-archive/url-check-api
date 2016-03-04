package com.codeaim.urlcheck.api.controller;

import com.codeaim.urlcheck.common.model.Check;
import com.codeaim.urlcheck.common.model.Result;
import com.google.common.collect.ImmutableMap;
import org.hibernate.annotations.Immutable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Map;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@Controller
@RequestMapping("/")
public class LandingPageController
{
    @RequestMapping(value = "", method = RequestMethod.GET)
    public ResponseEntity<Map<String, Object>> getLandingPage()
    {
        return new ResponseEntity<>(
                ImmutableMap.<String, Object>builder()
                        .put(Check.class.getSimpleName().toLowerCase(), linkTo(methodOn(CheckController.class).getChecks()).toUri())
                        .put(Result.class.getSimpleName().toLowerCase(), linkTo(methodOn(ResultController.class).getResults()).toUri())
                        .build(),
                HttpStatus.OK
        );
    }
}
