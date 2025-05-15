package com.iovu.iovuback.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.sql.Timestamp;
import java.util.UUID;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserToken {
    private String tokenId;       // 자동 생성됨 → 생략 가능
    private String userId;
    private String refreshToken;
    private Date expiresAt;
//     private Timestamp created_at;
}
