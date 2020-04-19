package pl.edu.zut.kisi.dyplom.mongo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.MongoTransactionManager;

@SpringBootApplication
public class TestyWydajnosciMongoApplication {

	@Bean
	MongoTransactionManager transactionManager(MongoDbFactory dbFactory) {
		return new MongoTransactionManager(dbFactory);
	}

	public static void main(String[] args) {
		SpringApplication.run(TestyWydajnosciMongoApplication.class, args);
	}

}
