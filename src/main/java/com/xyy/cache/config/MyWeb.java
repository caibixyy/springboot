package com.xyy.cache.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 静态资源加载不到，实现此接口，可重写addResourceHandlers（）
 */
@Configuration
public class MyWeb implements WebMvcConfigurer {
}
