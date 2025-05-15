package com.iovu.iovuback.mapper;

import com.iovu.iovuback.domain.UserToken;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserTokenMapper {
    void insert(UserToken token);
}
