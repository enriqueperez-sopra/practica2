package es.soprasteria.formacion.service;

import es.soprasteria.formacion.dto.SkillDto;
import java.util.List;

public interface SkillService {

  SkillDto getByNif(String nif);

  List<SkillDto> getAllSkills();

  SkillDto createSkill(SkillDto newSkill);

  SkillDto updateSkill(SkillDto skillToUpdate);

  void deleteSkill(String nif);
}
