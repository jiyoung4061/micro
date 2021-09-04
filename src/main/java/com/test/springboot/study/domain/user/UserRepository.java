package com.test.springboot.study.domain.user;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/*
    3-12. UserRepository
    /domain/user/Role, User, UserRepository 생성 완료
    build.gradle 설정 --- Sync
        -아래 내용 추가
        implementation('org.springframework.boot:spring-boot-starter-oauth2-client')
        implementation('org.springframework.session:spring-session-jdbc')
        testImplementation("org.springframework.security:spring-security-test")

    study.config.auth/SecurityConfig.java
 */

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
}
