package wushi.cn.Feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import wushi.cn.Entity.CommonResult;
import wushi.cn.entity.User;

@FeignClient("provider")
public interface ServiceProviderService {
    @GetMapping("/user/getUserById/{id}")
    CommonResult<User> getUserById(@PathVariable("id") Integer id);
}
