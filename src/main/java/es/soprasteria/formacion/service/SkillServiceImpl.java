package es.soprasteria.formacion.service;

import es.soprasteria.formacion.dao.SkillRepository;
import es.soprasteria.formacion.dto.SkillDto;
import es.soprasteria.formacion.entity.Skill;
import java.util.List;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class SkillServiceImpl implements SkillService {

  @Autowired
  private SkillRepository skillRepository;

  @Override
  public SkillDto getByNif(String nif) {
    Skill entity = skillRepository.findById(nif).orElse(null);
    if (entity == null) {
      return null;
    } else {
      SkillDto dto = SkillDto.builder()
          .nif(entity.getNif())
          .skills(entity.getSkills())
          .build();
      return dto;
    }
  }

  @Override
  public List<SkillDto> getAllSkills() {
    List<Skill> entities = skillRepository.findAll();
    List<SkillDto> skillDtos = entities.stream()
        .map(entity -> SkillDto.builder()
            .nif(entity.getNif())
            .skills(entity.getSkills())
            .build())
        .collect(Collectors.toList());
    return skillDtos;
  }

  @Override
  public SkillDto createSkill(SkillDto newSkill) {
    if (skillRepository.existsById(newSkill.getNif())) {
      log.info("El usuario existe en base de datos.");
      return null;
    } else {
      return saveSkill(newSkill);
    }
  }

  @Override
  public SkillDto updateSkill(SkillDto skillToUpdate) {
    if (!skillRepository.existsById(skillToUpdate.getNif())) {
      log.info("El usuario no existe en base de datos.");
      return null;
    } else {
      return saveSkill(skillToUpdate);
    }
  }

  @Override
  public void deleteSkill(String nif) {
    skillRepository.deleteById(nif);
  }

  private SkillDto saveSkill(SkillDto skillToSave) {
    Skill entity = new Skill();
    entity.setNif(skillToSave.getNif());
    entity.setSkills(skillToSave.getSkills());

    Skill persistedEntity = skillRepository.save(entity);

    SkillDto skillDto = SkillDto.builder()
        .nif(persistedEntity.getNif())
        .skills(persistedEntity.getSkills())
        .build();
    return skillDto;
  }
}
