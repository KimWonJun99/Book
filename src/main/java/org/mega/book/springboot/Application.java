package org.mega.book.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

//DB보는법 -> 앱실행 후 -> localhost:8080/h2-console 입력 -> select *from posts; 입력 후 Run

//머스테치란? 심플한 템플릿 엔진
//쉬프트키 2번 + 플러그인 -> Mustache 검색 후 다운받아야 머스테치 사용가능
//그리고 build.gradle -> implementation('org.springframework.boot:spring-boot-starter-mustache') 첨부해야한다.
//templates파일안에 파일선택 후 파일명.mustache 생성 후 진행하면 됨.

//자바스크립트는 static파일 안에 생성
@EnableJpaAuditing
@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class,args);
    }
}