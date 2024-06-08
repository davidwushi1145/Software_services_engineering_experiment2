package wushi.cn.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import wushi.cn.Entity.CommonResult;
import wushi.cn.Entity.User;

@RestController
@RequestMapping("/user")
public class UserController {
    @GetMapping("/getUserById/{id}")
    public CommonResult<User> getUserById(@PathVariable("id") Integer id) {
        try {
            User user = new User(id, "小米", "123456");
            return new CommonResult<>(200, "success11001", user);
        } catch (Exception e) {
            return new CommonResult<>(500, "error", null);
        }
    }
    @GetMapping("/getUserByName/{name}")
    public CommonResult<User> getUserByName(@PathVariable("name") String name) {
        try {
            User user = new User(1, name, "123456");
            return new CommonResult<>(200, "success11001", user);
        } catch (Exception e) {
            return new CommonResult<>(500, "error", null);
        }
    }
}
