package org.mega.book.springboot.config.auth;

import lombok.RequiredArgsConstructor;
import org.mega.book.springboot.config.auth.dto.OAuthAttributes;
import org.mega.book.springboot.config.auth.dto.SessionUser;
import org.mega.book.springboot.domain.user.User;
import org.mega.book.springboot.domain.user.UserRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.Collections;

@RequiredArgsConstructor
@Service
public class CustomOAuth2UserService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {
    private final UserRepository userRepository; // 쿼리문 실행
    private final HttpSession httpSession; // Http 세션
    //Session은 클라이언트와 서버간의 연결이 지속적으로 유지되는 상태를 뜻함
    //하나의 고유한 객체

    //Http 특성
    //클라이언트와 서버가 요청과 응답을 주고 받으면 연결이 끊어짐
    //클라이언트가 다시 요청하면 서버는 이전 요청을 기억할 수 없음.
    //클라이언트와 서버는 서로 상태를 유지하지 않는다.

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException{
        OAuth2UserService<OAuth2UserRequest, OAuth2User> delegate = new DefaultOAuth2UserService(); // 업캐스팅
        //DefaultOAuth2UserService는 OAuth2UserService의 구현체이다.
        //해당 클래스를 이용해서 userRequest에 있는 정보를 빼낼 수 있다.

        OAuth2User oAuth2User = delegate.loadUser(userRequest); // loadUser()는 자식이 정의한 함수로 실행

        String registrationId = userRequest.getClientRegistration().getRegistrationId(); // 등록된 아이디 빼기

        String userNameAttributeName = userRequest.getClientRegistration().getProviderDetails().getUserInfoEndpoint()
                .getUserNameAttributeName(); // 등록된 이름 빼기

        OAuthAttributes attributes = OAuthAttributes
                .of(registrationId, userNameAttributeName, oAuth2User.getAttributes());

        User user = saveOrUpdate(attributes);

        httpSession.setAttribute("user",new SessionUser(user)); // 이게 핵심

        return new DefaultOAuth2User(Collections.singleton(new SimpleGrantedAuthority(user.getRoleKey())),
                attributes.getAttributes(),
                attributes.getNameAttributeKey());
    }

    private User saveOrUpdate(OAuthAttributes attributes){
        User user = userRepository.findByEmail(attributes.getEmail())
                .map(entity -> entity.update(attributes.getName(), attributes.getPicture())) // 객체 있으면 수정
                .orElse(attributes.toEntity()); // 객체 없으면 생성
        return userRepository.save(user); // db에 저장
    }
}
