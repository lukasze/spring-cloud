package workshop.sc.cs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
/*
    TODO 7 adnotacje włączające techologie spring cloud:
    klient eureki i aktywacja feign

    Użycie technologii spring cloud w 3 krokach (nie zawsze potrzeba wszystkich 3):
    - dodaj zależność do projektu
    - dodaj adnotację w klasie głównej
    - dodaj odpowiedni wpis w pliku konfiguracyjnym projektu

 */
@EnableDiscoveryClient
@EnableFeignClients
public class CookieServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(CookieServiceApplication.class, args);
    }

}
