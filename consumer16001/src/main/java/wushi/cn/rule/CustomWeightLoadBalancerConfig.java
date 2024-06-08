package wushi.cn.rule;

import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.loadbalancer.core.ServiceInstanceListSupplier;
import org.springframework.context.annotation.Bean;
import reactor.core.publisher.Flux;

import java.util.List;

public class CustomWeightLoadBalancerConfig {
    @Bean
    public ServiceInstanceListSupplier discoveryClientServiceInstanceListSupplier() {
        return new ServiceInstanceListSupplier() {
            @Override
            public Flux<List<ServiceInstance>> get() {
                return null;
            }
            @Override
            public String getServiceId() {
                return "custom";
            }
        };
    }
}
