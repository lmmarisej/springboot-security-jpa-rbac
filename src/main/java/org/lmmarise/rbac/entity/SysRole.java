package org.lmmarise.rbac.entity;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

/**
 * @author lmmarise.j@gmail.com
 * @since 2021/10/2 12:32 下午
 */
@Entity
public class SysRole {
    @Id
    @GeneratedValue
    private Integer id;
    private String cnname;
    private String role;        // 角色标识程序中判断使用,如"sys",这个是唯一的:
    private String description; // 角色描述,UI界面显示使用
    private Boolean available = Boolean.FALSE; // 是否可用,如果不可用将不会添加给用户
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "SysRolePermission", joinColumns = {@JoinColumn(name = "roleId")}, inverseJoinColumns = {@JoinColumn(name = "permissionId")})
    private List<SysPermission> permissions;    // 角色 -- 权限关系,多对多关系
    @ManyToMany
    @JoinTable(name = "SysUserRole", joinColumns = {@JoinColumn(name = "roleId")}, inverseJoinColumns = {@JoinColumn(name = "uid")})
    private List<SysUser> userInfos;    // 用户 - 角色关系定义,一个角色对应多个用户

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCnname() {
        return cnname;
    }

    public void setCnname(String cnname) {
        this.cnname = cnname;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getAvailable() {
        return available;
    }

    public void setAvailable(Boolean available) {
        this.available = available;
    }

    public List<SysPermission> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<SysPermission> permissions) {
        this.permissions = permissions;
    }

    public List<SysUser> getUserInfos() {
        return userInfos;
    }

    public void setUserInfos(List<SysUser> userInfos) {
        this.userInfos = userInfos;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SysRole sysRole = (SysRole) o;
        return Objects.equals(id, sysRole.id) && Objects.equals(cnname, sysRole.cnname) && Objects.equals(role, sysRole.role) && Objects.equals(description, sysRole.description) && Objects.equals(available, sysRole.available) && Objects.equals(permissions, sysRole.permissions) && Objects.equals(userInfos, sysRole.userInfos);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, cnname, role, description, available, permissions, userInfos);
    }
}
