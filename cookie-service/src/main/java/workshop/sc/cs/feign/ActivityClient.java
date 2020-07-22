package workshop.sc.cs.feign;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import workshop.sc.model.Response;
/*
    TODO 4 biblioteka Feign wygeneruje nam w locie klienta REST.
    Definiujemy:
    1. interfejs z adnotacją @FeignClient(name = "ACTIVITY-SERVICE"),
    2. metodę z adnotacją @GetMapping("/activity")

    name = ACTIVITY-SERVICE - to nazwa, pod jaką komponent activity-service
    będzie rejestrował się w serwerze Eureka. Serwis będzie rejestrował się
    w Eurece (będzie rozpoznawany przez inne serwisy w naszym systemie), pod nazwą
    zdefiniowaną w spring.application.name.

    fallback = ActivityFallback.class - w ten sposób zdefiniowana jest awaryjna implementacja
    metody getResponse ( w przypadku, gdy cookie-service nie może połączyć się z activity-service).
 */
@FeignClient(name = "ACTIVITY-SERVICE", fallback = ActivityFallback.class)
// Adnotacja włączająca load balancer po stronie klienta
@RibbonClient(name = "ACTIVITY-SERVICE")
public interface ActivityClient {
    /*
        W oparciu o poniższą adnotację, oraz name = "ACTIVITY-SERVICE",  feign będzie wiedział, jaki typ rządzania
        ma wysłać klient i na jaki endpoint (http://activity-service/activity)
     */

    @GetMapping("/activity")
    /*
        Oczekiwana odpowiedź w formacie JSON,będzie mapowana do obiektu Response.
     */
    Response getResponse();
}
