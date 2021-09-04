package com.test.springboot.study;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

// 1-4. 패키지를 만든다
/*
    build.gradle 파일이 변경되면 무조건 Sync를 해줘야한다.
    @SpringBootApplication : SpringBoot의 시작 부분을 알린다.

    run()
        내장  WAS (Built-in)

    File > Settings > Editor > General > Code Completion > Match Case 해제 되었는지 확인

    2-12.
        @EnableJpaAuditing : 이 어플리케이션이 JPA Auditing 어노테이션을 활성화하라고 명령

        저장소와 관련해서, 생성, 수정하는 경우에 Auditing이 동작
        동작을 검증하기 위해 저장소와 관련된 단위테스트
        PostsRepositoryTest
 */
// Auto Import : Alt + Enter
@EnableJpaAuditing
@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
