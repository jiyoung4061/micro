package com.test.springboot.study.domain;

import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

/*
    2-11. JPA Auditing

        @MappedSuperclass
            JPA Entity Class들이 BaseTimeEntity를 상속하는 경우 필드(create..., modi ..)도 컬럼으로 인식해라

        @EntityListeners
            BaseTimeEntity 클래스에 Auditing 기능을 포함해라.

        Posts : 현재 만든 BaseTimeEntity를 상속만 받으면 알아서 시간 관리가 되도록
 */

@Getter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseTimeEntity {
    // abstract class : 객체를 만들기 위한 class가아닌 다른 class가 쓰기 위해 만든 class
    @CreatedDate
    private LocalDateTime createDate;

    @LastModifiedDate
    private LocalDateTime modifiedDate;
}
