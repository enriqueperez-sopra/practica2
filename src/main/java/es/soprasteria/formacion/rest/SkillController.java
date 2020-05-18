package es.soprasteria.formacion.rest;

import es.soprasteria.formacion.dto.SkillDto;
import es.soprasteria.formacion.service.SkillService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/v1/skill")
public class SkillController {
  @Autowired
  private SkillService skillService;

  @ApiOperation(value="Recupera listado de skills")
  @ApiResponses(value = {
      @ApiResponse(code = 200, message = "Skills recuperadas correctamente"),
      @ApiResponse(code = 404, message = "No hay skills en base de datos"),
  })
  @GetMapping(value = "/", produces = {
      MediaType.APPLICATION_JSON_VALUE,
      MediaType.APPLICATION_XML_VALUE
  })
  public ResponseEntity<List<SkillDto>> listSkills() {
    List<SkillDto> skills = skillService.getAllSkills();
    if (skills == null || skills.isEmpty()) {
      return ResponseEntity.notFound().build();
    } else {
      return ResponseEntity.ok(skills);
    }
  }


  @ApiOperation(value="Recupera una skill en función de su NIF")
  @ApiResponses(value = {
      @ApiResponse(code = 200, message = "Skill recuperada correctamente"),
      @ApiResponse(code = 404, message = "Skill no existe en base de datos"),
  })
  @GetMapping(value = "/{nif}", produces = {
      MediaType.APPLICATION_JSON_VALUE,
      MediaType.APPLICATION_XML_VALUE
  })
  public ResponseEntity<SkillDto> findSkill(@PathVariable(name="nif") String nif) {
    SkillDto skill = skillService.getByNif(nif);
    if (skill == null) {
      return ResponseEntity.notFound().build();
    } else {
      return ResponseEntity.ok(skill);
    }
  }

  @ApiOperation(value="Crea una skill en base de datos")
  @ApiResponses(value = {
      @ApiResponse(code = 200, message = "Skill creada en base de datos"),
      @ApiResponse(code = 400, message = "Skill ya existe en base de datos"),
  })
  @PostMapping(value = "/", produces = {
      MediaType.APPLICATION_JSON_VALUE,
      MediaType.APPLICATION_XML_VALUE
  })
  public ResponseEntity<SkillDto> createPerson(@RequestBody SkillDto newSkill) {
    SkillDto skill = skillService.createSkill(newSkill);
    if (skill == null) {
      return ResponseEntity.badRequest().build();
    } else {
      return ResponseEntity.ok(skill);
    }
  }

  @ApiOperation(value="Actualiza una skill en base de datos")
  @ApiResponses(value = {
      @ApiResponse(code = 200, message = "Skill actualizada en base de datos"),
      @ApiResponse(code = 400, message = "Skill no existe previamente en base de datos"),
  })
  @PutMapping(value = "/{nif}", produces = {
      MediaType.APPLICATION_JSON_VALUE,
      MediaType.APPLICATION_XML_VALUE
  })
  public ResponseEntity<SkillDto> updateSkill(@PathVariable(name="nif") String nif,
      @RequestBody SkillDto newSkill) {
    newSkill.setNif(nif);
    SkillDto skill = skillService.updateSkill(newSkill);
    if (skill == null) {
      return ResponseEntity.badRequest().build();
    } else {
      return ResponseEntity.ok(skill);
    }
  }

  @ApiOperation(value="Elimina una skill de base de datos a partir de su NIF")
  @ApiResponses(value = {
      @ApiResponse(code = 200, message = "Skill eliminada de base de datos")
  })
  @DeleteMapping("/{nif}")
  public ResponseEntity updatePerson(@PathVariable(name="nif") String nif) {
    skillService.deleteSkill(nif);
    return ResponseEntity.noContent().build();
  }
}
