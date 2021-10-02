package org.lmmarise.rbac.service;

import org.lmmarise.rbac.entity.SysUser;
import org.springframework.data.domain.Page;

/**
 * @author lmmarise.j@gmail.com
 * @since 2021/10/2 12:42 下午
 */
public interface SysUserService {
    void save(SysUser adminUser);       // 保存用户

    Page<SysUser> PageByAdminUser(Integer page, Integer size);  // 对用户数据进行分页

    SysUser findByUserName(String username);
}
