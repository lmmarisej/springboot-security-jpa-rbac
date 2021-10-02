package org.lmmarise.rbac.repository;

import org.lmmarise.rbac.entity.SysUser;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author lmmarise.j@gmail.com
 * @since 2021/10/2 12:34 下午
 */
public interface SysUserRepository extends JpaRepository<SysUser, Long> {
    SysUser findByName(String name);

    SysUser findById(long id);
}
