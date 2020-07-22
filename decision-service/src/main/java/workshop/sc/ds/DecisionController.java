package workshop.sc.ds;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import workshop.sc.model.Response;

import java.util.Random;

@RestController
public class DecisionController {

    @Value("${decisions}")
    private String [] decisions;
    @Value("${spring.application.name}")
    private String serviceName;

    @GetMapping("/decision")
    public Response decision() {
        return getResponseWithRandomDecision();
    }


    private Response getResponseWithRandomDecision() {
        String msg = decisions[new Random().nextInt(decisions.length)];
        return new Response(serviceName.toUpperCase(), msg);
    }
}
