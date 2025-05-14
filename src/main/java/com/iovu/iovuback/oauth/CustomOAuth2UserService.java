package com.iovu.iovuback.oauth;

import com.iovu.iovuback.domain.UserToken;
import com.iovu.iovuback.domain.Users;
import com.iovu.iovuback.mapper.UserTokenMapper;
import com.iovu.iovuback.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Date;
import java.util.Map;
import java.util.UUID;

@Service
public class CustomOAuth2UserService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {

    @Autowired
    private UserService userService;

    @Autowired
    private UserTokenMapper userTokenMapper;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        // 1. 기본 서비스 위임
        OAuth2UserService<OAuth2UserRequest, OAuth2User> delegate = new DefaultOAuth2UserService();
        OAuth2User oAuth2User = delegate.loadUser(userRequest);

        // 2. 네이버 로그인 구분
        String registrationId = userRequest.getClientRegistration().getRegistrationId(); // "naver"
        String userNameAttributeName = userRequest.getClientRegistration()
                .getProviderDetails().getUserInfoEndpoint().getUserNameAttributeName(); // "response"

        // 3. 네이버는 JSON 안에 "response" 키 아래 실제 데이터가 있음
        Map<String, Object> attributes = oAuth2User.getAttributes();
            System.out.println("OAuth2User attributes: " + oAuth2User.getAttributes());
        Map<String, Object> response = (Map<String, Object>) attributes.get("response");
        if (response == null) {
            throw new OAuth2AuthenticationException("Naver 응답에 'response' 필드가 없습니다.");
        }

        // 4. 여기서 DB 저장, JWT 발급 등 가능
        String name = (String) response.get("name");
        String email = (String) response.get("email");
        String profileUrl = (String) response.get("profile_image");
        String providerUid = (String) response.get("id");
        String userId  = UUID.randomUUID().toString().replace("-", "");

        Users users = new Users();
        users.setUserId(userId);
        users.setName(name);
        users.setEmail(email);
        users.setProfileUrl(profileUrl);
        users.setProvider("NAVER");
        users.setProviderUid(providerUid);

//        userService.saveNew(users);
        Users savedUser = userService.saveNew(users);
        System.out.println("✅ savedUserId: " + savedUser.getUserId());

        // 사용자 정보 저장 이후, 토큰 생성 및 저장
        String refreshToken = UUID.randomUUID().toString();  // 간단한 UUID 토큰

        UserToken userToken = new UserToken();
        userToken.setUserId(savedUser.getUserId());  // users는 DB에서 저장 or 조회된 사용자
        userToken.setRefreshToken(refreshToken);
        userToken.setExpiresAt(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24 * 30)); // 30일 후 만료

        userTokenMapper.insert(userToken);

        // 5. OAuth2User 반환
        return new DefaultOAuth2User(
                Collections.singleton(() -> "ROLE_USER"),
                response,
                "email"
        );
    }
}
