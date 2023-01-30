package org.mega.book.springboot.config.auth;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


//내가만든 어노테이션을 사용하기위해서는 설정도 필요하다.
//LoginUserArgumentReslover 동작기능 만들고, WebConfig에서 설정하면 사용가능
// indexController에서 수정하면 끝
@Target(ElementType.PARAMETER) // 어노테이션이 생성될 수 있는 위치를 지정 parameter(매개변수) 지정하면 파라미터 선언 객체만 사용 가능
@Retention(RetentionPolicy.RUNTIME) // 어노테이션 클래스로 지정 LoginUser이름을 가진 어노테이션 생성
public @interface LoginUser {
}
