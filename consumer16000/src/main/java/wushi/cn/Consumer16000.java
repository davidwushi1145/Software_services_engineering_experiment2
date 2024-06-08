package wushi.cn;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
import wushi.cn.rule.CustomLoadBalancerConfig;
import wushi.cn.rule.CustomThreeTimesBalancerConfig;
import wushi.cn.rule.ThreeTimeLoadBalancer;

@SpringBootApplication
@EnableFeignClients
@LoadBalancerClient(name = "provider", configuration = CustomThreeTimesBalancerConfig.class)
public class Consumer16000 {
    public static void main(String[] args) {
        SpringApplication.run(Consumer16000.class, args);
    }
}
