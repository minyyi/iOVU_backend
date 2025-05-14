package com.iovu.iovuback.domain;

import lombok.Data;

@Data
public class Users {
    private String userId;
    private String name;
    private String email;
    private String profileUrl;
    private String provider;
    private String providerUid;
}
