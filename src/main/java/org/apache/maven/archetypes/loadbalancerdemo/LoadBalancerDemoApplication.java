package org.apache.maven.archetypes.loadbalancerdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LoadBalancerDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(LoadBalancerDemoApplication.class, args);
    }

}
