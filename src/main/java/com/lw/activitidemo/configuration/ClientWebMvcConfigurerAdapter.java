package com.lw.activitidemo.configuration;

import com.lw.activitidemo.web.inteceptor.LoginInterceptor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.*;

@Configuration
@EnableWebMvc
@Component
public class ClientWebMvcConfigurerAdapter implements WebMvcConfigurer {

//    private ApplicationContext applicationContext;
//
//    public ClientWebMvcConfigurerAdapter(){
//        super();
//    }

    @Override//这里是让dispatcherServlet不拦截
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**").addResourceLocations(ResourceUtils.CLASSPATH_URL_PREFIX+"/static/");
        registry.addResourceHandler("/templates/**").addResourceLocations(ResourceUtils.CLASSPATH_URL_PREFIX+"/templates/");
    }

//    @Override
//    public void setApplicationContext(ApplicationContext applicationContext) {
//        this.applicationContext = applicationContext;
//    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
//        super.addInterceptors(registry);
        LoginInterceptor loginInterceptor = new LoginInterceptor();
        InterceptorRegistration ir = registry.addInterceptor(loginInterceptor);
        ir.addPathPatterns("/**").excludePathPatterns("/login","/login.ftl","/tologin","/static/**");
//        ir.excludePathPatterns("login.html");

    }
}
