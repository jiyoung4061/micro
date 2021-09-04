package com.test.springboot.study.config.auth.dto;

import com.test.springboot.study.domain.user.User;
import lombok.Getter;
import java.io.Serializable;


/*
    3-14. Session 관련 기능 추가
        로그인 기능을 관리하는 기술
            1. Cookie   : Client PC에서 로그인 정보 관리  => 서버 부담X / 보안문제 생김
            2. Session  : Server PC에서 로그인 정보 관리 (일반적인 방법) => 모든 로그인 정보를 관리해서 필요에 따라 활용 가능 / 서버 부담O
                    ㄴ 점점 Session기능이 강화되는 것이 웹의 추세

        이 파일까지 등록했을 때, 모든 에러가 사라져야 정상
        IndexController 에 가서 세션처리하면 구글 로그인이 완성
 */
@Getter
public class SessionUser implements Serializable {

    // Session에서 관리할 정보들 정의 --> DB 보면서 확인
    private String name;
    private String email;
    private String picture;

    public SessionUser(User user) {
        this.name = user.getName();
        this.email = user.getEmail();
        this.picture = user.getPicture();
    }
}