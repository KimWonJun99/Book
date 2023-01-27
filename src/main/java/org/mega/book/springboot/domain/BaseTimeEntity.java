package org.mega.book.springboot.domain;

import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@Getter
@MappedSuperclass
// 이를 선언할때에는 되도록 추상클래스로 만드는게 효율적
/*BaseTimeEntity 클래스를 상속 받으면 CreatedDate,LastModifiedDate 컬럼으로 인식하도록 한다. */
@EntityListeners(AuditingEntityListener.class)
// 해당 클래스에 Auditing 기능을 포함시킨다.
// 실행클래스에 @EnableJpaAuditing을 반드시 적용하여 Jpa Auditing을 활성 해야함.
public abstract class BaseTimeEntity {

    @CreatedDate // Entity가 생성되어 저장될때 시간이 자동 저장
    private LocalDateTime createdDate;

    @LastModifiedDate // 조회한 Entity의 값을 변경할때 시간이 자동 저장
    private LocalDateTime modifiedDate;
}
