package com.test.springboot.study;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// 1-4. 패키지를 만든다
// build.gradle 파일이 변경되면 무조건 Sync를 해줘야한다.
// Auto Import : Alt + Enter
@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
