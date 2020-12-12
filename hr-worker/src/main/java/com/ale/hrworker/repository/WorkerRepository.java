package com.ale.hrworker.repository;

import com.ale.hrworker.domain.Worker;

import org.springframework.data.jpa.repository.JpaRepository;

public interface WorkerRepository extends JpaRepository<Worker, Long> {

}
