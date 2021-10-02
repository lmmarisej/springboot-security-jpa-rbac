package org.lmmarise.rbac;

import org.lmmarise.rbac.entity.SysPermission;
import org.lmmarise.rbac.entity.SysRole;
import org.lmmarise.rbac.entity.SysUser;
import org.lmmarise.rbac.repository.SysPermissionRepository;
import org.lmmarise.rbac.repository.SysRoleRepository;
import org.lmmarise.rbac.repository.SysUserRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.Collections;

/**
 * @author lmmarise.j@gmail.com
 * @since 2021/10/2 12:47 下午
 */
@SpringBootApplication
public class RbacDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(RbacDemoApplication.class, args);
    }

    @Resource
    private SysUserRepository sysUserRepository;
    @Resource
    private SysPermissionRepository sysPermissionRepository;
    @Resource
    private SysRoleRepository sysRoleRepository;

    /**
     * 测试数据
     */
    @PostConstruct
    public void insertTestData() {
        if (sysUserRepository.findByName("1") != null) return;
        // 创建权限
        SysPermission adminPermission = new SysPermission();
        adminPermission.setPermission("user:create,user:update,user:delete,user:view,menu:*");   // 自动根据 "," 转为 permissions
        adminPermission.setAvailable(true);
        sysPermissionRepository.save(adminPermission);

        // 创建角色
        SysRole adminRole = new SysRole();
        adminRole.setRole("ADMIN");
        adminRole.setCnname("系统管理员");
        adminRole.setPermissions(Collections.singletonList(adminPermission));  // 关联具体权限
        adminRole.setAvailable(true);
        sysRoleRepository.save(adminRole);

        // 创建用户
        SysUser admin = new SysUser();
        admin.setCnname("1");
        admin.setName("1");
        admin.setPassword("{noop}1");
        admin.setEnabled(true);
        sysUserRepository.save(admin);

        // 用户授权
        adminRole.setUserInfos(Collections.singletonList(admin));
        sysRoleRepository.save(adminRole);
        admin.setRoles(Collections.singletonList(adminRole));
        sysUserRepository.save(admin);
    }
}
