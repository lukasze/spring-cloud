package workshop.sc.cs.feign;

import org.springframework.stereotype.Component;
import workshop.sc.model.Response;

@Component
public class DecisionFallback implements DecisionClient{
    @Override
    public Response getResponse() {
        return new Response(" ","You must");
    }
}
