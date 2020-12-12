package com.ale.hrworker.service;

import java.util.List;

import com.ale.hrworker.domain.Worker;
import com.ale.hrworker.repository.WorkerRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WorkerService {
    @Autowired
    private WorkerRepository repository;

    public List<Worker> getAll() {
        return repository.findAll();
    }

    public Worker getById(Long id) {
        return repository.findById(id).orElse(null);
    }
}
