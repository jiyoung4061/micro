package com.test.springboot.study.web;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;
import static org.assertj.core.api.Assertions.assertThat;

/*
    3-1.
        index.mustache의 body부분의 텍스트를 가져오는지 확인하는 절차
        단위 테스트 확인
        Application 확인

        http://localhost:8080 했을때 화면에 bootstrap글씨가 나오면 정상
        mustache와 연동확인, index파일에 접근 되는 것 확인
 */

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = RANDOM_PORT)
public class IndexControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void loadMappingPage() {
        String body = this.restTemplate.getForObject("/", String.class);
        assertThat(body).contains("bootstrap test");

    }
}

