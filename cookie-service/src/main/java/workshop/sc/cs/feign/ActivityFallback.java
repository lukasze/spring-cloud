package workshop.sc.cs.feign;

import org.springframework.stereotype.Component;
import workshop.sc.model.Response;

@Component
public class ActivityFallback implements ActivityClient {

    @Override
    public Response getResponse() {
        return new Response("","take a rest!");
    }
}
