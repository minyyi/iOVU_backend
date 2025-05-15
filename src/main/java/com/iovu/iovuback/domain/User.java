package com.iovu.iovuback.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private String user_id;
    private String name;
    private String email;
    private String profile_url;

    private String provider;
    private String provider_uid;
    private Timestamp created_at;
}
