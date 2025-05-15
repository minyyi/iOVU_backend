package com.iovu.iovuback.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserToken {
    private String token_id;
    private String user_id;

    private String refresh_token;
    private Timestamp expires_at;
    private Timestamp created_at;
}
