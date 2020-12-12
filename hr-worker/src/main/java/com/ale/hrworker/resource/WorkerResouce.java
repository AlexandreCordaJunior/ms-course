package com.ale.hrworker.resource;

import java.util.List;

import com.ale.hrworker.domain.Worker;
import com.ale.hrworker.service.WorkerService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/workers")
public class WorkerResouce {
    
    @Autowired
    private WorkerService service;

    @GetMapping
    public ResponseEntity<List<Worker>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Worker> getById(@PathVariable Long id) {
        Worker worker = service.getById(id);
        if(worker == null) {
            return ResponseEntity.notFound().build();
        }
        else {
            return ResponseEntity.ok(worker);
        }
    }

}
