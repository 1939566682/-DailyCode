package org.example.pojo;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.hibernate.validator.constraints.Length;

/**
 * @author Yang QingBo
 * {@code @date} 2026-03-29 17:51
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @NotBlank(message = "用户名不可以为空")
    private String uname;
    @NotBlank(message = "密码不可以为空")
    @Length(min=6, max=12,message = "密码长度最少六位最长十二位")
    private String pwd;

}
