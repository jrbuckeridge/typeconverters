package com.jrbuckeridge.blog.typeconverters;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LabProcedureRepository extends CrudRepository<LabProcedure, Long> {

  List<LabProcedure> findAllByCountriesContains(String country); // failing method

}
