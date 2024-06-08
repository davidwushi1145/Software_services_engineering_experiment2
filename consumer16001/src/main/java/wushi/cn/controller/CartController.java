package wushi.cn.controller;

import jakarta.annotation.Resource;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import wushi.cn.entity.CommonResult;
import wushi.cn.entity.User;

@RestController
@RequestMapping("/cart")
public class CartController {
    @Resource
    private RestTemplate restTemplate;
    @Resource
    private DiscoveryClient discoveryClient;

    @GetMapping("/getCartByUserId/{userId}")
    public CommonResult<User> getCartByUserId(@PathVariable("userId") Integer userId) {

//        List < ServiceInstance > instances = discoveryClient.getInstances("provider");
//        for (ServiceInstance instance: instances) {
//            System.out.println(instance.getHost() + " " + instance.getPort());
//        }
//        ServiceInstance instance = instances.get(0);

        CommonResult<User> u = restTemplate.getForObject("http://provider/user/getUserById/" + userId, CommonResult.class);
        return u;
    }
}
