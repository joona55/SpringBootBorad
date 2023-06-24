package com.study.borad.service;

import com.study.borad.entity.Board;
import com.study.borad.repository.BoardRepository;
import org.hibernate.cache.spi.support.AbstractReadWriteAccess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class BoardService {

    @Autowired // 스프링 빈이 알아서 값을 넣어줌
    private BoardRepository boardRepository;
    // 글 작성 처리
    public void write(Board board) {

        boardRepository.save(board);
    }

    // 게시물 리스트 처리
    public List<Board> boardList() {

        return boardRepository.findAll();
        //List 안에 담겨있는 모든 요소 반환
    }

    // 특정 게시물 불러오기
    public Board boardView(Integer id) {
        // 특정 id 번호 게시물을 불러온다.
        return boardRepository.findById(id).get();
    }

    // 특정 게시물 삭제
    public void boardDelete(Integer id) {
        boardRepository.deleteById(id);
    }


}
