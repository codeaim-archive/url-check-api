package com.codeaim.urlcheck.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.codeaim.urlcheck.api.model.Monitor;
import com.codeaim.urlcheck.api.model.MonitorEvent;
import com.codeaim.urlcheck.api.repository.MonitorEventRepository;
import com.codeaim.urlcheck.api.repository.MonitorRepository;

@SpringBootApplication
@EnableScheduling
@EnableJpaRepositories
public class Application implements CommandLineRunner
{
    @Autowired
    private MonitorRepository monitorRepository;

    @Autowired
    private MonitorEventRepository monitorEventRepository;

    public static void main(String[] args) throws Exception {
        SpringApplication.run(Application.class);
    }

    @Override
    public void run(String... args) throws Exception {
        monitorRepository.deleteAll();
        monitorRepository.save(Monitor.builder().userId("gdownes").name("Google").url("http://www.google.com").interval(1).build());
        monitorRepository.save(Monitor.builder().userId("gdownes").name("Facebook").url("http://www.facebook.com").interval(1).build());
        monitorRepository.save(Monitor.builder().userId("gdownes").name("Yahoo").url("http://www.yahoo.com").interval(1).build());
        monitorRepository.save(Monitor.builder().userId("gdownes").name("JSFiddle").url("http://jsfiddle.net/").interval(1).build());

        monitorEventRepository.deleteAll();
        monitorEventRepository.save(MonitorEvent.builder().build());
        monitorEventRepository.save(MonitorEvent.builder().build());
        monitorEventRepository.save(MonitorEvent.builder().build());
    }
}
