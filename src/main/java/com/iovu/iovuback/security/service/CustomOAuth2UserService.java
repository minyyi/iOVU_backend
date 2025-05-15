package com.iovu.iovuback.security.service;

import com.iovu.iovuback.domain.User;
import com.iovu.iovuback.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class CustomOAuth2UserService extends DefaultOAuth2UserService {

    @Autowired
    private UserService userService;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User oAuth2User = super.loadUser(userRequest);

        // 소셜 로그인 제공자 (kakao, google 등)
        String provider = userRequest.getClientRegistration().getRegistrationId();

        // 사용자 정보 추출 및 데이터베이스 저장
        return processOAuth2User(provider, oAuth2User);
    }

    private OAuth2User processOAuth2User(String provider, OAuth2User oAuth2User) {
        Map<String, Object> attributes = oAuth2User.getAttributes();

        String providerId;
        String email = null;
        String name = null;
        String profileUrl = null;

        // 카카오 로그인 처리
        if ("kakao".equals(provider)) {
            providerId = attributes.get("id").toString();
            Map<String, Object> kakaoAccount = (Map<String, Object>) attributes.get("kakao_account");
            Map<String, Object> profile = null;

            if (kakaoAccount != null) {
                email = (String) kakaoAccount.get("email");
                profile = (Map<String, Object>) kakaoAccount.get("profile");
            }

            if (profile != null) {
                name = (String) profile.get("nickname");
                profileUrl = (String) profile.get("profile_image_url");
            }
        } else {
            // 다른 공급자 처리 로직...
            providerId = oAuth2User.getName();
        }

        // 사용자 정보 저장 또는 업데이트
        User user = userService.findOrCreateUser(provider, providerId, email, name, profileUrl);

        // 사용자 정보 반환
        return oAuth2User;
    }
}