package es.soprasteria.formacion.dao;

import es.soprasteria.formacion.entity.Skill;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SkillRepository extends MongoRepository<Skill, String> {

}
