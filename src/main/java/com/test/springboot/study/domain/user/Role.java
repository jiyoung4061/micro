package com.test.springboot.study.domain.user;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Role {
    // key값에는 반드시 ROLE_를 붙인다.
    // ex. ROLE_ADMIN
    GUEST("ROLE_GUEST", "손님"),
    USER("ROLE_USER", "회원");


    private final String key;
    private final String title;
}
