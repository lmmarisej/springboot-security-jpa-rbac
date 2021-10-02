package org.lmmarise.rbac.service;

import org.lmmarise.rbac.entity.SysUser;
import org.lmmarise.rbac.repository.SysUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

/**
 * @author lmmarise.j@gmail.com
 * @since 2021/10/2 12:42 下午
 */
@Service
public class SysUserServiceImpl implements SysUserService {
    @Autowired
    private SysUserRepository sysUserRepository;

    @Override
    public void save(SysUser adminUser) {
        sysUserRepository.save(adminUser);
    }

    @Override
    public Page<SysUser> PageByAdminUser(Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page, size, Sort.Direction.ASC, "id");
        return sysUserRepository.findAll(pageable);
    }

    @Override
    public SysUser findByUserName(String username) {
        return sysUserRepository.findByName(username);
    }
}
