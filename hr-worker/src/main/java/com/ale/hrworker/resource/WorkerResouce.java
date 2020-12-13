package com.ale.hrworker.resource;

import java.util.List;

import com.ale.hrworker.domain.Worker;
import com.ale.hrworker.service.WorkerService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RefreshScope
@RestController
@RequestMapping("/workers")
public class WorkerResouce {

    private final WorkerService service;

    private final Environment env;

    private static Logger logger = LoggerFactory.getLogger(WorkerResouce.class);

    @Value("${test.config}")
    private String testConfig;

    @Autowired
    public WorkerResouce(WorkerService service, Environment env) {
        this.service = service;
        this.env = env;
    }

    @GetMapping
    public ResponseEntity<List<Worker>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Worker> getById(@PathVariable Long id) {
        logger.info("PORT - " + env.getProperty("local.server.port"));
        Worker worker = service.getById(id);
        if(worker == null) {
            return ResponseEntity.notFound().build();
        }
        else {
            return ResponseEntity.ok(worker);
        }
    }

    @GetMapping("/configs")
    public ResponseEntity<?> getConfigs() {
        logger.info("CONFIG = " + testConfig);
        return ResponseEntity.ok("");
    }

}
