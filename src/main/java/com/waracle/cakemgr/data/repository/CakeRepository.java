package com.waracle.cakemgr.data.repository;

import com.waracle.cakemgr.data.model.CakeModel;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CakeRepository extends JpaRepository<CakeModel, Long> {
    boolean existsByTitle(String title);
}


