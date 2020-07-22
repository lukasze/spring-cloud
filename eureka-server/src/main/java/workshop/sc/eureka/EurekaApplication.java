package workshop.sc.eureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
/*
	TODO 10 - serwer eureka

	Tradycyjnie - dodana zależność, adnotacja w klasie głównej, wpisy w pliku konfiguracyjnym.
	Uwaga boostrap.yml wskazuje na to, że jakieś ważne wartości będą pobierane
	z serwera konfiguracyjnego - możemy je podejrzeć w repozytoritum git.



 */
@EnableEurekaServer
public class EurekaApplication {

	public static void main(String[] args) {
		SpringApplication.run(EurekaApplication.class, args);
	}

}
