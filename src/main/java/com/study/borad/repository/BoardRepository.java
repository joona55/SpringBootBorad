package com.study.borad.repository;

import com.study.borad.entity.Board;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
//Repository 안에 테이블, 프라이머리 키 입력
public interface BoardRepository extends JpaRepository<Board, Integer> {

    // 제목에 특정 키워드가 포함된 모든 데이터를 검색(findBy(컬럼)Containing)
    Page<Board> findByTitleContaining(String searchKeyword, Pageable pageable);
}
