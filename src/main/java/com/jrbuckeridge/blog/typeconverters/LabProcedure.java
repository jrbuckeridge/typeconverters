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
@Table(name = "lab_procedure")
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LabProcedure {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @Column(name = "name")
  private String name;

  @Column(name = "countries")
  @Convert(converter = CountryConverter.class)
  private Set<String> countries;

  public static class CountryConverter implements AttributeConverter<Set<String>, String> {

    @Override
    public String convertToDatabaseColumn(Set<String> attribute) {
      TreeSet<String> orderedSet = new TreeSet<>();
      if (attribute != null) {
        orderedSet.addAll(attribute);
      }
      return "," + String.join(",", orderedSet) + ",";
    }

    @Override
    public Set<String> convertToEntityAttribute(String dbData) {
      Set<String> set = new TreeSet<>();
      if (dbData != null) {
        Arrays.stream(dbData.split(","))
            .map(String::trim)
            .filter(s -> !"".equals(s))
            .forEach(set::add);
      }
      return set;
    }
  }
}
