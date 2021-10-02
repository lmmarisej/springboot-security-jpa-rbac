package org.lmmarise.rbac.security.service;

import org.lmmarise.rbac.entity.SysUser;
import org.lmmarise.rbac.repository.SysUserRepository;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import javax.annotation.Resource;

/**
 * Spring Security Provider
 *
 * @author lmmarise.j@gmail.com
 * @since 2021/10/2 12:45 下午
 */
public class SysSecurityService implements UserDetailsService {     // 为了扩展性，避免使用@Service直接注入IoC
    @Resource
    private SysUserRepository sysUserRepository;

    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        SysUser user = sysUserRepository.findByName(name);
        if (user == null) {
            throw new UsernameNotFoundException("用户名不存在");
        } else if (!user.getEnabled()) {
            throw new LockedException("用户被锁定");
        }
        return user;
    }
}
