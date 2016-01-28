package com.codeaim.urlcheck.api.assembler;

import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import org.springframework.stereotype.Component;

import com.codeaim.urlcheck.api.controller.MonitorController;
import com.codeaim.urlcheck.api.model.Monitor;
import com.codeaim.urlcheck.api.resource.MonitorResource;

@Component
public class MonitorResourceAssembler extends ResourceAssemblerSupport<Monitor, MonitorResource>
{
    public MonitorResourceAssembler() {
        super(MonitorController.class, MonitorResource.class);
    }

    @Override
    public MonitorResource toResource(Monitor monitor) {
        return new MonitorResource();
    }
}
