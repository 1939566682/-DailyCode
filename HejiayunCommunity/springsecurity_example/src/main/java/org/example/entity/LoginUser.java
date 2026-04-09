package org.example.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serial;
import java.util.Collection;
import java.util.List;

/**
 * LoginUser
 *
 * @author Yang QingBo
 * {@code @date} 2026-04-09 16:10
 */

@Setter
@Getter
public class LoginUser implements UserDetails {
    @Serial
    private static final long serialVersionUID = -170084046189122755L;

    private SysUser sysUser;

    public LoginUser() {
    }

    public LoginUser(SysUser sysUser) {
        this.sysUser = sysUser;
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

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
