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
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class LabProcedure2RepositoryTest {

  @Autowired
  LabProcedure2Repository labProcedure2Repository;

  @Before
  public void setUp() {
    Set<String> countries = new TreeSet<>();
    countries.add("US");
    countries.add("DE");

    labProcedure2Repository.save(LabProcedure2.builder()
        .name("DE/US TEST")
        .countries(countries)
        .build());
  }

  @Test
  public void saveAndFind() {

    Set<String> countries = new TreeSet<>();
    countries.add("CA");
    countries.add("US");

    LabProcedure2 newLabProcedure2 = LabProcedure2.builder()
        .name("TEST")
        .countries(countries)
        .build();
    long id = labProcedure2Repository.save(newLabProcedure2).getId();

    Optional<LabProcedure2> labProcedure2 = labProcedure2Repository.findById(id);
    assertThat(labProcedure2.isPresent(), is(true));
    assertThat(labProcedure2.get().getName(), is(newLabProcedure2.getName()));
    assertThat(labProcedure2.get().getCountries(), is(countries));
  }

  @Test //TODO: failing test
  public void findByCountry() {
    List<LabProcedure2> labProceduresDE =
        labProcedure2Repository.findAllByCountriesContains("DE");
    assertThat(labProceduresDE.size(), is(1));
  }
}