package it.keyover.trsserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import it.keyover.trsserver.common.factory.TwitterClientFactory;

@SpringBootApplication
public class TrsserverApplication {

	public static void main(String[] args) {
		SpringApplication.run(TrsserverApplication.class, args);
	}

}

