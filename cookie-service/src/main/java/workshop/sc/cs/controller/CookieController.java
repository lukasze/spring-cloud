package workshop.sc.cs.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import workshop.sc.cs.feign.ActivityClient;
import workshop.sc.cs.feign.DecisionClient;
import workshop.sc.model.Response;

@RestController
public class CookieController {
    private final ActivityClient activity;
    private final DecisionClient decision;

    @Value("${spring.application.name}")
    private String serviceName;
    /*
        TODO 3 w zeleżności od tego, jak masz ustawione IDE, możesz mieć ostrzeżenie o błędzie.
         IDE 'wydaje się', że ma więcej niż jeden bean do wstrzyknięcia. Wszystko jest w najlepszym porządku ;).

         Przejdźmy do następnego TODO'sa żeby zobaczyć mechanizm FEIGN, HYSTRIX i RIBBON
     */
    public CookieController(ActivityClient activity, DecisionClient decision) {
        this.activity = activity;
        this.decision = decision;
    }

    @GetMapping("/")
    public Response fortune() {
        return getResponse();
    }

    private Response getResponse() {

        return getFortune();
    }

    private Response getFortune() {
        return new Response(serviceName.toUpperCase(),
                decision.getResponse().getMsg() + " " +
                        activity.getResponse().getMsg());
    }
}

/*
       TODO 1 to jest kontroler cookie-service
       jego zadaniem jest zwrócenie ... 'ciasteczka' z wróżbą, np. It's a good day for running.
       Taka wróżba tworzona jest w oparciu o dwa serwisy:
       -decision-service (It's a good day, it's a bad day etc.)
       -activiity service (for running, for a bear etc.).

       Na poniższym schemacie widać 3 miksoserwisy i endpointy.
       Uwaga http://cookie-service, http://activity-service, http://decision-service
       te domeny będą używane w docker-compose, jeśli chcemy lokalnie uruchamiać aplikację,
       potrzebujemy dodać wpisy do hostów. Przejdź do TODO2.


                                                                                      |  activity-service  |
                                                                                      |                    |
                                                                                      |                    |
                                                                                      |     lazy  8010     |
                           +--------------------+  http://activity-service/activity   |     crazy 8011     |
                           |   cookie-service   +------------------------------------>+                    |
                           |                    |                                     |                    |
   http://cookie-service   |                    |                                     +--------------------+
+------------------------->+    default 8080    |
                           |                    |                                     +--------------------+
                           |                    |  http://decision-service/decision   |  decision-service  |
                           |                    +------------------------------------>+                    |
                           +--------------------+                                     |                    |
                                                                                      |    polite 8000     |
                                                                                      |    rough  8001     |
                                                                                      |                    |
                                                                                      |                    |
                                                                                      +--------------------+

 */

/*
    TODO 2 dodaj wpis do hostów:
    Poniżej wszystkie wpisy, których będziemy potrzebowali dla naszej aplikacji 3 serwisy,
    oraz serwer konfiguracyjny i 2 instancje serwera eureka, o których za chwilę powiemy.

       Pomocny artykuł, jak dodawać wpisy do hostów:
       https://www.howtogeek.com/howto/27350/beginner-geek-how-to-edit-your-hosts-file/

       Dodaj poniższe, otwórz konsolęi przetestuj:  ping cookie-service, ping activity-service etc.

       #START fortune cookie
       127.0.0.1       cookie-service
       127.0.0.1       activity-service
       127.0.0.1       decision-service
       127.0.0.1       config-server
       127.0.0.1       eureka-peer1
       127.0.0.1       eureka-peer2
       #END fortune cookie
 */