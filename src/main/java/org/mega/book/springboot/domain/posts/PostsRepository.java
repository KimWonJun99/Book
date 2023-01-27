package org.mega.book.springboot.domain.posts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PostsRepository extends JpaRepository<Posts,Long> {
    //리포지토리 생성방법 : extends JpaRepository<entity클래스명, entity클래스안에 Id 자료형타입>

    @Query("SELECT p FROM Posts p ORDER BY p.id DESC") //  Posts 테이블 내의 모든 데이터를 조회 후, ID를 내림차순 (desc)으로 정렬
    List<Posts> findAllDesc();
}
