package it.keyover.trsserver;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import com.mongodb.MongoClient;

import it.keyover.trsserver.util.PropertyReader;


@Configuration
@EnableMongoRepositories
class AppConfig extends AbstractMongoConfiguration {

  private final static String BASE_PACKAGE = "it.keyover.trsserver";
	
  @Override
  protected String getDatabaseName() {
    return PropertyReader.getMongoProperties("mongodb.database");
  }

  @Override
  public MongoClient mongoClient() {
    return new MongoClient(PropertyReader.getMongoProperties("mongodb.host"));
  }

  @Override
  protected String getMappingBasePackage() {
    return BASE_PACKAGE;
  }
}