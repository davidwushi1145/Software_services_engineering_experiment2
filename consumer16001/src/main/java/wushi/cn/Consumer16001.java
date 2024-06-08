package wushi.cn;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
import wushi.cn.rule.CustomLoadBalancerConfig;

@SpringBootApplication
@LoadBalancerClient(name = "provider", configuration = CustomLoadBalancerConfig.class)
public class Consumer16001 {
    public static void main(String[] args) {
        SpringApplication.run(Consumer16001.class, args);
    }


    @Bean
    @LoadBalanced
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
