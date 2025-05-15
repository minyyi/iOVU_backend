package com.iovu.iovuback.service;

import com.iovu.iovuback.domain.User;
import com.iovu.iovuback.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserService {

    private UserMapper userMapper;

    public UserService(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    /**
     * 소셜 로그인 제공자와 제공자 ID로 사용자 조회 또는 생성
     */
    public User findOrCreateUser(String provider, String providerId, String email, String name, String profileUrl) {
        // 기존 사용자 조회
        User user = userMapper.findByProviderAndProviderId(provider, providerId);

        if (user == null) {
            // 새 사용자 생성
            user = new User();
            user.setEmail(email);
            user.setName(name);
            user.setProfile_url(profileUrl);
            user.setProvider(provider.toUpperCase());
            user.setProvider_uid(providerId);

            userMapper.insertUser(user);
        } else {
            // 기존 사용자 정보 업데이트
            if (email != null && !email.equals(user.getEmail())) {
                user.setEmail(email);
            }
            if (name != null && !name.equals(user.getName())) {
                user.setName(name);
            }
            if (profileUrl != null && !profileUrl.equals(user.getProfile_url())) {
                user.setProfile_url(profileUrl);
            }

            userMapper.updateUser(user);
        }

        return user;
    }
}