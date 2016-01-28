package com.codeaim.urlcheck.api.controller;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.codeaim.urlcheck.api.assembler.MonitorResourceAssembler;
import com.codeaim.urlcheck.api.repository.MonitorRepository;
import com.codeaim.urlcheck.api.resource.MonitorResource;

@Controller
@RequestMapping("/monitor")
public class MonitorController
{
    @Autowired
    MonitorRepository monitorRepository;
    @Autowired
    MonitorResourceAssembler monitorResourceAssembler;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public HttpEntity<List<MonitorResource>> getMonitors()
    {
        List<MonitorResource> monitorResources = monitorResourceAssembler
                .toResources(monitorRepository.findAll());

        return new ResponseEntity<>(monitorResources, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public HttpEntity<Resource<MonitorResource>> getMonitor(@PathVariable Long id)
    {
        Resource<MonitorResource> monitorResource = new Resource<>(monitorResourceAssembler
                .toResource(monitorRepository.findOne(id)));

        monitorResource.add(linkTo(methodOn(MonitorController.class).getMonitor(id)).withSelfRel());

        return new ResponseEntity<>(monitorResource, HttpStatus.OK);
    }
}
