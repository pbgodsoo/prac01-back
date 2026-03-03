package com.example.demo.user;

import com.example.demo.user.model.AuthUserDetails;
import com.example.demo.user.model.User;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;

@Service
public class OAuth2UserService extends DefaultOAuth2UserService {
    private final UserRepository userRepository;

    public OAuth2UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        System.out.println("서비스 코드 실행");
        // OAuth2 로그인 실행
        OAuth2User oAuth2User = super.loadUser(userRequest);

        Map<String, Object> attributes = oAuth2User.getAttributes();
        String providerId = ((Long)attributes.get("id")).toString();
        System.out.println(providerId);

        String email = providerId + "@kakao.social";
        Map properties = (Map) attributes.get("properties");
        String name = (String) properties.get("nickname");

        // DB에 화면이 있나 없나 확인
        Optional<User> result = userRepository.findByEmail(email);

        // 없으면 가입 시켜주기
        // 없으면 가입 시켜주기
        User user = null;

        if (!result.isPresent()) {
            user = userRepository.save(
                    User.builder()
                            .email(email)
                            .name(name)
                            .password("kakao")
                            .enable(true)
                            .role("ROLE_USER")
                            .build()
            );
            return AuthUserDetails.from(user);
        } else {
            user = result.get();
            return AuthUserDetails.from(user);
        }
    }
}
