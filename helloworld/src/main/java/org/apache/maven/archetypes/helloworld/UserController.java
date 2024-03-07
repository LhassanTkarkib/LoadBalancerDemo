package org.apache.maven.archetypes.helloworld;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    private final SayHelloServiceLoadBalancedClient sayHelloServiceLoadBalancedClient;

    public UserController(SayHelloServiceLoadBalancedClient sayHelloServiceLoadBalancedClient) {
        this.sayHelloServiceLoadBalancedClient = sayHelloServiceLoadBalancedClient;
    }

    @GetMapping("/user")
    public String user() {
        return sayHelloServiceLoadBalancedClient.greetings();
    }

}