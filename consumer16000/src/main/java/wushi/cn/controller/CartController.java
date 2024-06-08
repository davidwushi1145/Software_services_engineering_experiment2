package wushi.cn.controller;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import wushi.cn.Entity.CommonResult;
import wushi.cn.Feign.ServiceProviderService;
import wushi.cn.entity.User;

@RestController
@RequestMapping("/cart")
public class CartController {
    @Resource
    private ServiceProviderService serviceProviderService;

    @GetMapping("/getCartByUserId/{userId}")
    @CircuitBreaker(name = "backendA", fallbackMethod = "fallback")
    public CommonResult<User> getCartByUserId(@PathVariable("userId") Integer userId) {
        return serviceProviderService.getUserById(userId);
    }

    public CommonResult<User> fallback(Integer id,Throwable t) {
        t.printStackTrace();
        System.out.println("触发熔断：" + t.getMessage()+ " id: " + id);
        return new CommonResult<>(500, t.getMessage(), new User());
    }
}
