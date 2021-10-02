package org.lmmarise.rbac.repository;

import org.lmmarise.rbac.entity.SysRole;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author lmmarise.j@gmail.com
 * @since 2021/10/2 12:34 下午
 */
public interface SysRoleRepository extends JpaRepository<SysRole, Long> {
    SysRole findByRole(String name);
}