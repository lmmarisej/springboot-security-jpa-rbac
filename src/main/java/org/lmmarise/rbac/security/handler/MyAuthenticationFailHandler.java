package org.lmmarise.rbac.security.handler;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 认证失败处理器（用户名密码错误执行）
 *
 * @author lmmarise.j@gmail.com
 * @since 2021/10/2 12:38 下午
 */
@Component("myAuthenticationFailHandler")
public class MyAuthenticationFailHandler extends SimpleUrlAuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest req, HttpServletResponse resp, AuthenticationException e) throws IOException {
        req.setCharacterEncoding("UTF-8");
        // 获得用户名密码（测试获取操作，显然不能返还给用户）
        String username = req.getParameter("uname");
        String password = req.getParameter("pwd");
        resp.setContentType("application/json;charset=utf-8");
        PrintWriter out = resp.getWriter();
        out.write("{\"status\":\"error\",\"message\":\"用户名或密码错误\"}");
        out.flush();
        out.close();
    }
}