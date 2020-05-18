package es.soprasteria.formacion.dto;

import java.util.List;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class SkillDto {
  private String nif;
  private List<String> skills;
}