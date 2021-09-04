package com.test.springboot.study.web.dto;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;


/*
    1-8. lombok Test 모듈
        실행하면 final Field때문에 문제가 생기는 것이 정상!
        build.gradle에서 추가 작업 필요
 */


public class HelloResponseDtoTest {

    @Test
    public void lombokTest() {

        String name = "test";
        int age = 30;

        HelloResponseDto dto = new HelloResponseDto(name, age);

        assertThat(dto.getName()).isEqualTo(name);
        assertThat(dto.getAge()).isEqualTo(age);
    }
}
