package org.lmmarise.rbac.repository;

import org.lmmarise.rbac.entity.SysPermission;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author lmmarise.j@gmail.com
 * @since 2021/10/2 12:33 下午
 */
public interface SysPermissionRepository extends JpaRepository<SysPermission, Long> {
    SysPermission findById(long id);
}
