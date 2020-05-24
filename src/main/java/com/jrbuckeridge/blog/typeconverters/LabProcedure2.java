package com.jrbuckeridge.blog.typeconverters;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Arrays;
import java.util.Set;
import java.util.TreeSet;

@Entity
@Table(name = "lab_procedure2")
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LabProcedure2 {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @Column(name = "name")
  private String name;

  @Column(name = "countries")
  private String countries;

  public Set<String> getCountries() {
    Set<String> set = new TreeSet<>();
    if (countries != null) {
      Arrays.stream(countries.split(","))
          .map(String::trim)
          .filter(s -> !"".equals(s))
          .forEach(set::add);
    }
    return set;
  }

  public void setCountries(Set<String> countries) {
    this.countries = treeAsString(countries);
  }

  private static String treeAsString(Set<String> countries) {
    TreeSet<String> orderedSet = new TreeSet<>();
    if (countries != null && !countries.isEmpty()) {
      orderedSet.addAll(countries);
    }
    return "," + String.join(",", orderedSet) + ",";
  }

  public static class LabProcedure2Builder {

    private String countries;

    public LabProcedure2Builder countries(Set<String> countries) {
      this.countries = treeAsString(countries);
      return this;
    }

  }
}
