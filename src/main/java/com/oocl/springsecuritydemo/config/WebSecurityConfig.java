package com.oocl.springsecuritydemo.config;

import com.oocl.springsecuritydemo.entities.UserDetail;
import com.oocl.springsecuritydemo.service.UserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
//开启在方法是使用权限的注解，间controller层的  @PreAuthorize("hasAnyRole('ADMIN')")
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailService userDetailService;
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //进行角色校验时候，Spring中的角色是带有ROLE_的前缀的,如ROLE_ADMIN
        auth.userDetailsService(userDetailService).passwordEncoder(new BCryptPasswordEncoder());
//        auth.inMemoryAuthentication().withUser("jaylon")
//                .password(passwordEncoder().encode("123456"))
//                .roles("admin","user");
//
//        auth.inMemoryAuthentication().withUser("kate")
//                .password(passwordEncoder().encode("123456"))
//                .roles("user");
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/h2-console/**", "/login" , "/login.html").permitAll()
//                .antMatchers("/user/**").hasAnyRole("ADMIN", "CUSTOMER")
                .anyRequest().authenticated()
                //自定义登录界面，需要登录时候会跳转到自定义的登录界面
                .and().formLogin().loginPage("http://localhost:8081/login-form")
                //与登录界面登录时候的url匹配http://localhost:7061/login，登录界面使用表单登录表单的输入框的name必须是username和password
                .loginProcessingUrl("/login")
                .and().logout().permitAll()
                .and()
//                .csrf().ignoringAntMatchers("/h2-console/**")
                .csrf().disable()
                .headers().frameOptions().sameOrigin()
        ;
    }
}
