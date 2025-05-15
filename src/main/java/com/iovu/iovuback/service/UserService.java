package com.iovu.iovuback.service;

import com.iovu.iovuback.domain.Users;
import com.iovu.iovuback.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserMapper userMapper;

    public Users saveNew(Users users) {
        Users existing = userMapper.findByProviderAndUid(users.getProvider(), users.getProviderUid());
        if (existing == null) {
            userMapper.insert(users);
            return users;
        }
        return existing;
    }
}
