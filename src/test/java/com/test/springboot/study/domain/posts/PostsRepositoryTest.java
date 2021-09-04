package com.test.springboot.study.domain.posts;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/*
    1-12
        @AfterEach : 각 단위 테스트가 끝날때 마다 수행하는 작업 정의 <--> @BeforeEach
        JUnit4 에서 @After에 해당됨
        다음 테스트가 다른 데이터 침범을 방지

        posts : Insert / Update 구분하는 방법
        Insert : id가 없으면 글 등록
        Update : id가 있으면 글 수정으로 판단

    1-13
        디비 쿼리가 제대로 수행되는지 확인할 방법이 없다.
        이를 해결하는 방법은 property 설정으로 가능한데
        src/main/resources/application.properties 파일에 속성을 설정한다. --- (application.properties > A1)


*/

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class PostsRepositoryTest {

    @Autowired
    PostsRepository postsRepository;

    @AfterEach
    public void cleanup() {
        postsRepository.deleteAll(); // 내부적으로 DB를 전부 지워줌
    }

    @Test
    public void saveAndLoad() {
        String title = "테스트 제목";
        String content = "테스트 본문";

        postsRepository.save(
                Posts
                    .builder()
                        .title(title)
                        .content(content)
                        .author("jiyoung@test.com")
                    .build());

        // insert --> List

        /*
            [MySQL]
            create table posts (
                id      long auto_increment,
                title   char(500),
                content text,
                author  char(255),
                primary key(id)
            );

            이렇게 MySQL, MariaDB, Oracle의 속성에 맞게 처리하도록 변경하고 싶다면, properties에서 설정!
             --- (application.properties > A2)

        */

        List<Posts> postsList = postsRepository.findAll();
        // findAll() : SELECT * 수행 후 목록 결과를 List Collection에 넣는 작업

        Posts posts = postsList.get(0);
        assertThat(posts.getTitle()).isEqualTo(title);
        assertThat(posts.getContent()).isEqualTo(content);

    }

    /*
        2-12. Auditing

        단위 테스트 확인 후 성공했으면, applicaton 실행 후
        htttp:/localhost:8080/h2-console 에 접속해서
        Posts테이블에 CreateDate, ModifiedDate 필드가 추가된 것을 확인

        2-13 mustache 화면 구성
            매우 간단한 템플릿 엔진
            화면 구성을 위한 틀을 만들어 놓고, 데이터가 추가되어야하는 부분만 프로그램으로 채우는 방식
            장점 : 문법 단순
                  (MVC중) View역할만 수행
                  IntelliJ에서는 plugIns로 간단한 설치(Ctrl+Shift+A) 가능

            Mustache 사용을 위해서는 bundle.gradle에 추가해준다. --> Sync

            src -> resource -> templates -> index.mustache


     */

    @Test
    public void BasetimeEntityRegister() {
        LocalDateTime now = LocalDateTime.of(2021, 8, 25, 16, 10, 22);
        postsRepository.save(Posts.builder().title("title").content("content").author("author").build());

        List<Posts> postsList = postsRepository.findAll();

        Posts posts = postsList.get(0);

        System.out.println("\t\t\t+++++++++ create Date = " + posts.getCreateDate() + ", modify Date = " + posts.getModifiedDate());

        assertThat(posts.getCreateDate()).isAfter(now);
        assertThat(posts.getModifiedDate()).isAfter(now);
    }
}
