package com.jrbuckeridge.blog.typeconverters;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.TreeSet;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DataJpaTest
public class LabProcedureRepositoryTest {

  @Autowired
  LabProcedureRepository labProcedureRepository;

  @Before
  public void setUp() {
    Set<String> countries = new TreeSet<>();
    countries.add("US");
    countries.add("DE");

    labProcedureRepository.save(LabProcedure.builder()
        .name("DE/US TEST")
        .countries(countries)
        .build());
  }

  @Test
  public void saveAndFind() {

    Set<String> countries = new TreeSet<>();
    countries.add("CA");
    countries.add("US");

    LabProcedure newLabProcedure = LabProcedure.builder()
        .name("TEST")
        .countries(countries)
        .build();
    long id = labProcedureRepository.save(newLabProcedure).getId();

    Optional<LabProcedure> labProcedure = labProcedureRepository.findById(id);
    assertThat(labProcedure.isPresent(), is(true));
    assertThat(labProcedure.get().getName(), is(newLabProcedure.getName()));
    assertThat(labProcedure.get().getCountries(), is(countries));
  }

//  @Test //TODO: failing test
//  public void findByCountry() {
//    List<LabProcedure> labProceduresDE =
//        labProcedureRepository.findAllByCountriesContains("DE");
//    assertThat(labProceduresDE.size(), is(1));
//  }
}