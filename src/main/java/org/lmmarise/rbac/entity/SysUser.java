package org.lmmarise.rbac.entity;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

/**
 * 实现UserDetails，以支持SpringSecurity认证
 *
 * @author lmmarise.j@gmail.com
 * @since 2021/10/2 12:32 下午
 */
@Entity
public class SysUser implements UserDetails {
    @Id
    @GeneratedValue
    private long id;
    @Column(nullable = false, unique = true)
    private String name;
    private String password;
    private String cnname;
    private Boolean enabled = Boolean.TRUE;
    @ManyToMany(cascade = {CascadeType.REFRESH}, fetch = FetchType.EAGER)
    private List<SysRole> roles;    // 多对多映射，用户角色

    public long getId() {
        return id;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public String getCnname() {
        return cnname;
    }

    public void setCnname(String cnname) {
        this.cnname = cnname;
    }

    public void setId(long id) {
        this.id = id;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<SysRole> getRoles() {
        return roles;
    }

    public void setRoles(List<SysRole> roles) {
        this.roles = roles;
    }

    // 获取当前用户实例的password
    @Override
    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public SysUser(String name, String password) {
        this.name = name;
        this.password = password;
    }


    public SysUser() {
    }

    // 根据自定义逻辑来返回用户权限，如果用户权限返回空或者和拦截路径对应权限不同，验证不通过
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();
        List<SysRole> roles = this.getRoles();
        for (SysRole role : roles) {
            // 在SpringSecurity中以字符串来表示角色，都需要加 ROLE_ 前缀，注意！！！是字符串！！！例如，SpEL表达式，hasRole("ROLE" + admin)
            // 其它情况不用加，例如"权限"。
            authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getRole()));
        }
        return authorities;
    }

    // 获取当前用户实例的name
    @Override
    public String getUsername() {
        return this.name;
    }


    // 帐号是否不过期，false则验证不通过
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    // 帐号是否不锁定，false则验证不通过
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    // 凭证是否不过期，false则验证不通过
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    // 该帐号是否启用，false则验证不通过
    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SysUser sysUser = (SysUser) o;
        return id == sysUser.id && Objects.equals(name, sysUser.name) && Objects.equals(password, sysUser.password) && Objects.equals(cnname, sysUser.cnname) && Objects.equals(enabled, sysUser.enabled) && Objects.equals(roles, sysUser.roles);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, password, cnname, enabled, roles);
    }
}
