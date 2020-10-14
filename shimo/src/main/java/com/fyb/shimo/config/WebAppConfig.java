package com.fyb.shimo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.CacheControl;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebAppConfig implements WebMvcConfigurer {

    /**
     * 自定义静态资源映射
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**/index.html", "/")//设置哪些静态资源不缓存
                .addResourceLocations("classpath:/static/")
                .setCacheControl(CacheControl.noStore());//用 noStore 才起效

    }

}
