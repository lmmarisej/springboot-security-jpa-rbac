package org.lmmarise.rbac.security.service;

import org.springframework.security.core.Authentication;

import javax.servlet.http.HttpServletRequest;

/**
 * @author lmmarise.j@gmail.com
 * @since 2021/10/2 12:35 下午
 */
public interface RbacService {
    boolean hasPermission(HttpServletRequest request, Authentication authentication);
}