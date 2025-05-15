package com.iovu.iovuback.domain;

import lombok.Data;

import java.util.Date;

@Data
public class UserToken {
    private String tokenId;       // 자동 생성됨 → 생략 가능
    private String userId;
    private String refreshToken;
    private Date expiresAt;
}
