package com.example.Backend.repository;

import com.example.Backend.model.Config;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface ConfigRepo extends JpaRepository<Config, Integer> {
    Config findTopByOrderByIdDesc();
}

