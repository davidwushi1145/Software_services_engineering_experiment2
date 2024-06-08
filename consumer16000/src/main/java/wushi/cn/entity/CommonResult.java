package wushi.cn.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Data // 这个注解包含了 @Getter, @Setter, @ToString 等
public class CommonResult<T> implements Serializable {
    private Integer code;
    private String message;
    private T data; // 注意，这里我将字段名从 result 改为 data，这更符合一般的命名习惯，但这不是必须的
}
