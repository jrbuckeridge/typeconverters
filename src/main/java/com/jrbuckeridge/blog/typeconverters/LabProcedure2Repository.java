package com.jrbuckeridge.blog.typeconverters;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LabProcedure2Repository extends CrudRepository<LabProcedure2, Long> {

  List<LabProcedure2> findAllByCountriesContains(String country);

}
