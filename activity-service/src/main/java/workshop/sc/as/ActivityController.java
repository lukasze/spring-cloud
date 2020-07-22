package workshop.sc.as;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import workshop.sc.model.Response;

import java.util.Random;

@RestController
public class ActivityController {
    /*
        TODO 11 jeden z serwisów, z których korzysta cookie-service

        cookie-service będzie łączył się na endpoint http://cookie-service/activity
        W ActivityController mamy, metodę getResponseWithRandomDecision, która
        w oparciu o wstrzykniętą tablicę activities zwróci nam np. for a marathon.

        @Value("${activities}") - ta wartość będzie pobrana z serwera konfiguracyjnego.
        Np. dla profilu lazy, dostępne wartości to: to grab a bear,to eat a cookie,to take a nap

        http://config-server/activity-service/lazy

        https://github.com/lukasze/fortune-cookie/blob/master/activity-service.yml

        Podejrzyj dodane zależności i adnotacje w klasie głównej.

        Analogicznie zachowuje się komponent decision-service.
     */
    @Value("${activities}")
    private String [] activities;
    // Property out of the box
    @Value("${spring.application.name}")
    private String serviceName;

    @GetMapping("/activity")
    public Response activity() {
        return getResponseWithRandomDecision();
    }


    private Response getResponseWithRandomDecision() {
        String msg = activities[new Random().nextInt(activities.length)];
        return new Response(serviceName.toUpperCase(), msg);
    }
}
