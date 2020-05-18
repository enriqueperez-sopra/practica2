package es.soprasteria.formacion.entity;

import java.util.List;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Getter
@Setter
@Document
public class Skill {

  @Id
  private String nif;

  @Field(value = "SKILLS")
  private List<String> skills;
}
