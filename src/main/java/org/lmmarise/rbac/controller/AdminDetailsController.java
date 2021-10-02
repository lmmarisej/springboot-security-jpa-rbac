package org.lmmarise.rbac.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author lmmarise.j@gmail.com
 * @since 2021/10/2 3:49 下午
 */
@RestController
@RequestMapping("/admin")
public class AdminDetailsController {

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping("/auth")
    public String authentication() {
        return SecurityContextHolder.getContext().getAuthentication().toString();
    }

    @PreAuthorize("hasRole('ROLE_ADMIN1')")     // 加了一个1，差异性测试
    @RequestMapping("/auth1")
    public String authentication1() {
        return SecurityContextHolder.getContext().getAuthentication().toString();
    }

}
