package es.soprasteria.formacion.listener;

import es.soprasteria.formacion.dto.SkillDto;
import es.soprasteria.formacion.service.SkillService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class Consumer {

  @Autowired
  private SkillService skillService;

  @RabbitListener(queues = {"skill"})
  public void receiveMessageFromFanout1(SkillDto message) {
    skillService.createSkill(message);
  }
}
