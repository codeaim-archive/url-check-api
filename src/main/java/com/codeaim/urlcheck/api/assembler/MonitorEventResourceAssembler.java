package com.codeaim.urlcheck.api.assembler;

import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import org.springframework.stereotype.Component;

import com.codeaim.urlcheck.api.controller.MonitorEventController;
import com.codeaim.urlcheck.api.model.MonitorEvent;
import com.codeaim.urlcheck.api.resource.MonitorEventResource;

@Component
public class MonitorEventResourceAssembler extends ResourceAssemblerSupport<MonitorEvent, MonitorEventResource>
{
    public MonitorEventResourceAssembler() {
        super(MonitorEventController.class, MonitorEventResource.class);
    }

    @Override
    public MonitorEventResource toResource(MonitorEvent monitorEvent) {
        return new MonitorEventResource();
    }
}
