package com.test.springboot.study.domain.posts;

import com.test.springboot.study.domain.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;


/*
    1-10. Posts 클래스 생성
    게시판 : 제목, 내용, 작성자(날짜 등은 나중에 추가 예정)
    id : 자동으로 결정되기 때문에 id항목은 별도 필드로 두지 않는다.
    @Entity : JPA에서 필요한 Annotation
        테이블과 클래스를 매핑
        Posts.java ---> posts DB table 내부적으로 자동으로 만들어줌
        MyPosts.java ---> my_post DB table을 내부적으로 만들어준다.

    @Id : 데이터베이스의 키 값
    @GeneratedValue : 키 생성
*/

/*
    2-11. JPA Auditing
        Posts가 BaseTimeEntity 추상 클래스를 상속만 받으면 시간관리가 됨.
 */
@Getter
@NoArgsConstructor
@Entity
public class Posts extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // 글 번호

    @Column(length = 500, nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    private String author;

    @Builder
    public Posts(String title, String content, String author) {
        this.title = title;
        this.content = content;
        this.author = author;
    }

    /*
        2-7. Posts에 update()기능 추가
            이제 서비스에서도 update를 추가한다.
            Repository에 업데이트를 할 수 있는 코드 확인을 해야한다.


    */
    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }

    /*

     */
}
