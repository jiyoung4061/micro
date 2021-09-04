package com.test.springboot.study.domain.posts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/*
    1-11. PostsRepository Interface를 생성

    3-5 DB에서 가져오는 순서를 프로그래머가 정하는 방법

        SELECT * FROM Posts AS p ORDER BY p.id DESC
        SELECT long_name_field as x, id FROM Posts as p ORDER BY p.id DESC, x ASC

        Named SQL(=JPQL)이라는 문법을 공부할 필요 있음.
            ※ 주의 ※
            Posts 이름을 p로 Alias 시키면 Select p 이여야함.

        TODO
            DB에서 데이터를 List형태로 찾아오는 일 까지 함.
            이 데이터를 던져줘야함 = DTO를 새로 추가해줘야한다는 말.
            PostsListResponseDto.java를 새로 만들자.
                ㄴ web/dto/PostsListResponseDto.java
*/

public interface PostsRepository extends JpaRepository<Posts, Long> {

    @Query("SELECT p FROM Posts p ORDER BY p.id DESC")
    List<Posts> findAllDesc();
}
