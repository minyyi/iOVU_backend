package com.iovu.iovuback.mapper;

import com.iovu.iovuback.domain.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import com.iovu.iovuback.domain.Users;

@Mapper
public interface UserMapper {
    // 소셜 로그인 제공자와 제공자 ID로 사용자 조회
    User findByProviderAndProviderId(@Param("provider") String provider, @Param("provider_uid") String providerId);

    // 사용자 등록
    void insertUser(User user);

    // 사용자 정보 업데이트
    void updateUser(User user);

    // 이메일로 사용자 조회
    User findByEmail(String email);

    // ID로 사용자 조회
    User findByUserId(String userId);
  
    Users findByProviderAndUid(String provider, String providerUid);
    
    void insert(Users users);
}
