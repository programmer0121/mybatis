package org.example.mybatis.city;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Builder
@Getter
@Setter
@ToString
public class City {

  private long id;
  private String name;
  private String state;
  private String country;
}
