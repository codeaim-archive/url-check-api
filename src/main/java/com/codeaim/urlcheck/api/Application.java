package com.codeaim.urlcheck.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.codeaim.urlcheck.common.repository.CheckRepository;
import com.codeaim.urlcheck.common.repository.ResultRepository;

@SpringBootApplication
@EnableScheduling
@EnableJpaRepositories
public class Application
{
    @Autowired
    private CheckRepository checkRepository;

    @Autowired
    private ResultRepository resultRepository;

    public static void main(String[] args) throws Exception {
        SpringApplication.run(Application.class);
    }
}
