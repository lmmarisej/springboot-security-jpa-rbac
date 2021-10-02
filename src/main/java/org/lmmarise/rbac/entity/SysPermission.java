package org.lmmarise.rbac.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * @author lmmarise.j@gmail.com
 * @since 2021/10/2 12:31 下午
 */
@Entity
public class SysPermission implements Serializable {
    @Id
    @GeneratedValue
    private Integer id;
    private String name;
    @Column(columnDefinition = "enum('menu','button')")   // 资源类型，[menu|button]
    private String resourceType;
    private String url;         // 资源路径
    private String permission;  // 权限字符串,menu例子：role:*，button例子：role:create,role:update,role:delete,role:view
    private Long parentId;      // 父编号
    private String parentIds;   // 父编号列表
    private Boolean available = Boolean.FALSE;
    @Transient
    private List<?> permissions;
    @ManyToMany
    @JoinTable(name = "SysRolePermission", joinColumns = {@JoinColumn(name = "permissionId")}, inverseJoinColumns = {@JoinColumn(name = "roleId")})
    private List<SysRole> roles;

    public List<?> getPermissions() {
        return Arrays.asList(this.permission.trim().split(","));
    }

    public void setPermissions(List<?> permissions) {
        this.permissions = permissions;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getResourceType() {
        return resourceType;
    }

    public void setResourceType(String resourceType) {
        this.resourceType = resourceType;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public String getParentIds() {
        return parentIds;
    }

    public void setParentIds(String parentIds) {
        this.parentIds = parentIds;
    }

    public Boolean getAvailable() {
        return available;
    }

    public void setAvailable(Boolean available) {
        this.available = available;
    }

    public List<SysRole> getRoles() {
        return roles;
    }

    public void setRoles(List<SysRole> roles) {
        this.roles = roles;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SysPermission that = (SysPermission) o;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name) && Objects.equals(resourceType, that.resourceType) && Objects.equals(url, that.url) && Objects.equals(permission, that.permission) && Objects.equals(parentId, that.parentId) && Objects.equals(parentIds, that.parentIds) && Objects.equals(available, that.available) && Objects.equals(permissions, that.permissions) && Objects.equals(roles, that.roles);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, resourceType, url, permission, parentId, parentIds, available, permissions, roles);
    }
}
