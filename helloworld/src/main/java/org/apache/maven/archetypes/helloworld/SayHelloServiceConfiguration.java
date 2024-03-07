package org.apache.maven.archetypes.helloworld;

import org.springframework.beans.factory.ObjectProvider;
import org.springframework.cloud.client.DefaultServiceInstance;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.loadbalancer.core.RandomLoadBalancer;
import org.springframework.cloud.loadbalancer.core.ReactorLoadBalancer;
import org.springframework.cloud.loadbalancer.core.ServiceInstanceListSupplier;
import org.springframework.cloud.loadbalancer.core.WeightFunction;
import org.springframework.cloud.loadbalancer.support.ServiceInstanceListSuppliers;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SayHelloServiceConfiguration {
//
//    @Bean
//    public ServiceInstanceListSupplier serviceInstanceListSupplier(ConfigurableApplicationContext context) {
//        ServiceInstanceListSupplier serviceInstanceListSupplier = ServiceInstanceListSuppliers.from("say-hello",
//                new DefaultServiceInstance("1", "say-hello", "localhost", 8083, false),
//                new DefaultServiceInstance("2", "say-hello", "localhost", 8082, false)
//        );
//
//        return ServiceInstanceListSupplier.builder()
//                .withBase(serviceInstanceListSupplier)
//                .build(context);
//
//    }

//=============================================================================================================== Random Algorithm

//    @Bean
//    public ServiceInstanceListSupplier serviceInstanceListSupplier(ConfigurableApplicationContext context) {
//
//        ServiceInstanceListSupplier serviceInstanceListSupplier = ServiceInstanceListSuppliers.from("say-hello",
//                new DefaultServiceInstance("myservice1", "say-hello", "localhost", 8083, false),
//                new DefaultServiceInstance("myservice2", "say-hello", "localhost", 8082, false)
//        );
//
//        return ServiceInstanceListSupplier.builder()
//                .withBase(serviceInstanceListSupplier)
//                .build(context);
//
//    }
//
//    @Bean
//    public ReactorLoadBalancer<ServiceInstance> randomLoadBalancer(ObjectProvider<ServiceInstanceListSupplier> serviceInstanceListSupplier) {
//        return new RandomLoadBalancer(serviceInstanceListSupplier, "say-hello");
//
//    }

    //=============================================================================================================== Weighted Algorithm


    @Bean
    public ServiceInstanceListSupplier serviceInstanceListSupplier(ConfigurableApplicationContext context) {

        WeightFunction weightFunction = instance -> {
            if (instance.getPort() == 8083) {
                return 4;
            } else if (instance.getPort() == 8082) {
                return 1;
            } else {
                return 0;
            }
        };



        ServiceInstanceListSupplier serviceInstanceListSupplier = ServiceInstanceListSuppliers.from("say-hello",
                new DefaultServiceInstance("myservice1", "say-hello", "localhost", 8083, false),
                new DefaultServiceInstance("myservice2", "say-hello", "localhost", 8082, false)
        );

        return ServiceInstanceListSupplier.builder()
                .withBase(serviceInstanceListSupplier)
                .withWeighted(weightFunction)
                .build(context);

    }

    //===============================================================================================================


}
