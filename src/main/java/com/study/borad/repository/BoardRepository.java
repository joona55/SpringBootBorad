package com.study.borad.repository;

import com.study.borad.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
//Repository 안에 테이블, 프라이머리 키 입력
public interface BoardRepository extends JpaRepository<Board, Integer> {

}
