package com.globalsolution2.fiap;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import io.github.cdimascio.dotenv.Dotenv;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class GlobalSolution2ApplicationTests {
	
	@BeforeAll
	static void loadEnv() {
	    Dotenv dotenv = Dotenv.load();
	    dotenv.entries().forEach(entry -> 
	        System.setProperty(entry.getKey(), entry.getValue())
	    );
	}
}

