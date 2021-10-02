package org.lmmarise.rbac.security.service;

import org.lmmarise.rbac.entity.SysPermission;
import org.lmmarise.rbac.entity.SysRole;
import org.lmmarise.rbac.entity.SysUser;
import org.lmmarise.rbac.repository.SysPermissionRepository;
import org.lmmarise.rbac.repository.SysUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

import javax.servlet.http.HttpServletRequest;
import java.util.HashSet;
import java.util.Set;

/**
 * 在代码中通过SpEL表达式使用
 *
 * @author lmmarise.j@gmail.com
 * @since 2021/10/2 12:35 下午
 */
@Component("rbacService")
public class RbacServiceImpl implements RbacService {
    private final AntPathMatcher AntPathMatcher = new AntPathMatcher();
    @Autowired
    private SysPermissionRepository permissionRepository;
    @Autowired
    private SysUserRepository sysUserRepository;

    @Override
    public boolean hasPermission(HttpServletRequest request, Authentication authentication) {
        Object principal = authentication.getPrincipal();
        boolean hasPermission = false;
        if (principal instanceof UserDetails) { // 如果能获取到Principal对象不为空证明，授权已经通过
            // 登录的用户名
            String userName = ((UserDetails) principal).getUsername();
            // 获取请求登录的url
            Set<String> urls = new HashSet<>();     // 用户具备的系统资源集合，从数据库读取
            Set<String> curds = new HashSet<>();    // 用户具备的系统资源集合，从数据库读取
            SysUser sysUser = sysUserRepository.findByName(userName);
            try {
                for (SysRole role : sysUser.getRoles()) {
                    for (SysPermission permission : role.getPermissions()) {
                        urls.add(permission.getUrl());
//                         curds.add(permission.getPermission());
                        curds.add(permission.getPermission() + permission.getUrl());
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            //urls.add("/sys/user/add");
            for (String url : urls) {
                if (AntPathMatcher.match(url, request.getRequestURI())) {
                    hasPermission = true;
                    break;
                }
            }
        }
        return hasPermission;
    }
}
