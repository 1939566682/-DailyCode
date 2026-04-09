package org.example.entity;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serial;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * LoginUser
 *
 * @author Yang QingBo
 * {@code @date} 2026-04-09 16:10
 */

@Data
public class LoginUser implements UserDetails {
    @Serial
    private static final long serialVersionUID = -170084046189122755L;

    private SysUser sysUser;

    // 存储权限的集合
    private List<String> permissions;

    // authorities 权限集合
    @JSONField(serialize = false)
    private List<SimpleGrantedAuthority> authorities;

    public LoginUser() {
    }

    public LoginUser(SysUser sysUser) {
        this.sysUser = sysUser;
    }

    public LoginUser(SysUser sysUser, List<String> permissions) {
        this.sysUser = sysUser;
        this.permissions = permissions;
    }

    /**
     * 用于获取用户被授予的权限 可以用于实现访问控制
     * @return
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // 将 permissions 集合中 Sting 类型的权限信息 转为 SimpleGrantedAuthority 类型
//        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
//        permissions.forEach(permission -> authorities.add(new SimpleGrantedAuthority(permission)));

        if (authorities != null) {
            return authorities;
        }

        return permissions.stream()
                .map(SimpleGrantedAuthority::new)
                .toList();
    }

    /**
     * 获取用户密码 用于密码验证
     * @return
     */
    @Override
    public String getPassword() {
        return sysUser.getPassword();
    }

    @Override
    public String getUsername() {
        return sysUser.getUserName();
    }

    /**
     * 账户是否过期
     *
     * @return
     */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /**
     * 账户是否锁定
     *
     * @return
     */
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    /**
     * 用户凭证是否过期
     *
     * @return
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /**
     * 用户是否激活
     *
     * @return
     */
    @Override
    public boolean isEnabled() {
        return true;
    }

}
