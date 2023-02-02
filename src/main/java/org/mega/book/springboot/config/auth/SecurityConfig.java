package org.mega.book.springboot.config.auth;

import lombok.RequiredArgsConstructor;
import org.mega.book.springboot.domain.user.Role;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@RequiredArgsConstructor
@EnableWebSecurity // 스프링 시큐리티 설정 활성화
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final CustomOAuth2UserService customOAuth2UserService;
    @Override
    protected void configure(HttpSecurity http) throws Exception{
        http.csrf().disable()
                .headers().frameOptions().disable()
                .and()
                .authorizeRequests()// 인증요청이오면
                .antMatchers("/","/css/**","images/**","/js/**","h2-console/**","/profile").permitAll()
                .antMatchers("/api/v1/**").hasRole(Role.USER.name()) // USER권한인 사람한테만 공개
                .and()
                .logout().logoutSuccessUrl("/")//로그아웃이 성공하면 "/"메인페이지로 간다.
                .and()
                .oauth2Login().userInfoEndpoint().userService(customOAuth2UserService);//로그인 성공 이후 사용자 정보를 가져올때 설정 담당
    }
}
