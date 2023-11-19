package com.example.model;

import lombok.*;

import java.util.Collection;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AuthResponse
{
    private String userId;
    private String accesstoken;
    private String refereshToken;
    private long expireAt;
    private Collection<String> authorities;
}
