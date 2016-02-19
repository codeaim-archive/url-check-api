package com.codeaim.urlcheck.api.controller;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;
import static org.springframework.http.HttpStatus.NO_CONTENT;
import static org.springframework.web.bind.annotation.RequestMethod.DELETE;

import java.util.List;
import java.util.Objects;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.codeaim.urlcheck.common.model.Check;
import com.codeaim.urlcheck.common.model.Result;
import com.codeaim.urlcheck.common.repository.CheckRepository;

@Controller
@RequestMapping("/check")
public class CheckController
{
    @Autowired
    CheckRepository checkRepository;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public ResponseEntity<List<Check>> getChecks()
    {
        return new ResponseEntity<>(checkRepository.findAll(), HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Check> getCheck(@PathVariable Long id)
    {
        Check entity = checkRepository.findOne(id);
        if (entity == null)
        {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(entity, HttpStatus.OK);
    }


    @RequestMapping(value = "", method = RequestMethod.POST)
    public ResponseEntity<Check> createCheck(
            @RequestBody @Valid Check entity,
            BindingResult bindingResult
    )
    {
        if (bindingResult.hasErrors())
        {
            return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
        }

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(linkTo(methodOn(CheckController.class)
                .getCheck(checkRepository.save(entity).getId()))
                .toUri());

        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Check> updateCheck(
            @PathVariable(value = "id") Long id,
            @RequestBody @Valid Check entity,
            BindingResult bindingResult
    )
    {
        if (bindingResult.hasErrors() || !Objects.equals(id, entity.getId()))
        {
            return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
        }

        Check existingCheck = checkRepository.findOne(entity.getId());
        if (existingCheck == null)
        {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        checkRepository.save(entity);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = DELETE)
    public ResponseEntity<Result> deleteCheck(
            @PathVariable(value = "id") Long id
    )
    {
        Check entity = checkRepository.findOne(id);
        if (entity == null)
        {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        checkRepository.delete(entity);
        return new ResponseEntity<>(NO_CONTENT);
    }
}
