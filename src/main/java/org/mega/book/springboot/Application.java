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


/*구글연동 방법
* https://console.cloud.google.com 입력 -> 구글 로그인
* 새 프로젝트 생성
* 탐색메뉴 -> API 및 서비스 -> 사용자 인증 정보 클릭
* 사용자 인증 정보이용 하려면 OAuth 동의 화면 구성 해야함.
* 동의 화면 구성후에, 사용자 인증 정보 -> 사용자 인증 정보 만들기 -> Oauth 클라이언트 ID 클릭
* 웹 애플리케이션 클릭 -> 승인된 리디렉션 URL에서 URL추가 -> http://localhost:8080/login/oauth2/code/google 추가 -> 만들기
* 사용자인증정보 가보면 웹 클라이언트 생성되어있고, 클릭하면 클라이언트ID,비밀번호 있음.
* templates -> 리소스번들 생성 -> application-oauth.properties -> 인증관련 정보
* spring.profiles.include=oauth 코드를 application.properties에 추가 - 인증관련 정보가 담긴 properties 파일명과 동일해야한다.
* implementation('org.springframework.boot:spring-boot-starter-oauth2-client') 코드를 build.gradle에 추가
* 구글에서 클라이언트 생성할때 리디렉션url(oauth2)와 동일해야한다.
* config패키지 생성 -> auth(인증)패키지 생성 -> SecurityConfig 클래스 생성
* CustomOAuth2UserService 생성
* auth패키지안에 dto 패키지 생성 -> OAuthAttributes 클래스 생성
* dto 패키지안에 SessionUser 클래스 생성*/

/*네이버 연동 방법
* https://developers.naver.com/apps/#/register?api=nvlogin 입력
* 네이버 로그인 -> 애플리케이션 등록(애플리케이션 이름 + 사용API(네이버로그인) + 서비스환경(pc웹)
*               + 로그인 오픈 API 서비스URL(http://localhost:8080/login/oauth2/code/naver)
*                               콜백 URL(http://localhost:8080/login/oauth2/code/naver)
*application-oauth.properties에서 설정
* OAuthAttributes에서 코드추가
* */
//@EnableJpaAuditing -> JpaConfig에 따로 만들기
@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class,args);
    }
}