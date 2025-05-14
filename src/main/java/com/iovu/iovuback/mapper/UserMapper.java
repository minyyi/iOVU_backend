package com.iovu.iovuback.mapper;

import com.iovu.iovuback.domain.Users;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
    Users findByProviderAndUid(String provider, String providerUid);
    void insert(Users users);
}
