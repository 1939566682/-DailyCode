package org.example.hjycommunity.web.test;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * User
 *
 * @author Yang QingBo
 * {@code @date} 2026-04-03 20:06
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @NotBlank(message = "userId 不能为空")
    private String userId;
    @NotBlank(message = "userName 不能为空")
    private String userName;
}
