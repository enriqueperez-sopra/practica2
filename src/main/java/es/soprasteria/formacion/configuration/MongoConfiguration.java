package es.soprasteria.formacion.configuration;

import brave.Tracing;
import brave.mongodb.MongoDBTracing;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.event.CommandListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MongoConfiguration {

  @Bean
  public MongoClient instrumentation() {
    CommandListener listener = MongoDBTracing.create(Tracing.current())
        .commandListener();
    MongoClientSettings settings = MongoClientSettings.builder()
        .addCommandListener(listener)
        .build();
    return MongoClients.create(settings);
  }
}
