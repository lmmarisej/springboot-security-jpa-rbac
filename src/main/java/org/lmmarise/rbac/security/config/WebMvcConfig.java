package org.lmmarise.rbac.security.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 常规MVC配置
 *
 * @author lmmarise.j@gmail.com
 * @since 2021/10/2 12:29 下午
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        // 设置登录处理操作
        registry.addViewController("/home/login").setViewName("user/login");   // 普通用户登录页未实现
        registry.addViewController("/admin/login").setViewName("admin/login");
        registry.addViewController("/admin/rbac").setViewName("admin/rbac");
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // 和页面有关的静态目录都放在项目的static目录下
        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
        // 其中OTA表示访问的前缀。"file:D:/OTA/"是文件真实的存储路径
        registry.addResourceHandler("/UPLOAD/**").addResourceLocations("file:F:/UPLOAD/");
    }
}
