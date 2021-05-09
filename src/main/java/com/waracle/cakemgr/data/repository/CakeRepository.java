package com.waracle.cakemgr.data.repository;

import com.waracle.cakemgr.data.model.CakeModel;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CakeRepository extends CrudRepository<CakeModel, Long> {}


