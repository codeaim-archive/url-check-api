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

import com.codeaim.urlcheck.api.assembler.MonitorEventResourceAssembler;
import com.codeaim.urlcheck.api.model.MonitorEvent;
import com.codeaim.urlcheck.api.repository.MonitorEventRepository;

@Controller
@RequestMapping("/monitor-event")
public class MonitorEventController
{
    @Autowired
    MonitorEventRepository monitorEventRepository;
    @Autowired
    MonitorEventResourceAssembler monitorEventResourceAssembler;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public HttpEntity<List<MonitorEvent>> getMonitorEvents()
    {
        List<MonitorEvent> monitorEvents = monitorEventRepository.findAll();

        return new ResponseEntity<>(monitorEvents, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public HttpEntity<Resource<MonitorEvent>> getMonitorEvent(@PathVariable Long id)
    {
        Resource<MonitorEvent> monitorEventResource =
                new Resource<>(monitorEventRepository.findOne(id));

        monitorEventResource.add(linkTo(methodOn(MonitorEventController.class).getMonitorEvent(id)).withSelfRel());

        return new ResponseEntity<>(monitorEventResource, HttpStatus.OK);
    }
}
