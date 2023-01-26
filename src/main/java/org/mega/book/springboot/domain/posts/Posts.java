package org.mega.book.springboot.domain.posts;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.mega.book.springboot.domain.BaseTimeEntity;

import javax.persistence.*;

//Entity 클래스에서는 setter를 절대 만들지 않는다.
@Getter // 모든 필드의 Getter메소드 자동생성 (롬복)
@NoArgsConstructor // 기본생성자 자동추가 (롬복)
@Entity // 테이블과 링크될 클래스임을 나타냄
public class Posts extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 500, nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    private String author; //저자(글쓴이)

    @Builder // 보안성을 강화하기 위해 사용 - @Builder를 통해 제공되는 빌드 클래스를 사용, 생성자에 포함된 필드만 포함 (롬복)
    public Posts(String title, String content, String author){
        this.title = title;
        this.content = content;
        this.author = author;
    }

    public void update(String title, String content){ // 수정이되면 db에도 알아서 변경이 된다. 이미 있는 객체에서 변경되는것
        this.title = title;
        this.content = content;
    }
}
