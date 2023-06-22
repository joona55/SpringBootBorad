package com.study.borad.service;

import com.study.borad.entity.Board;
import com.study.borad.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoardService {

    @Autowired // 스프링 빈이 알아서 값을 넣어줌
    private BoardRepository boardRepository;
    public void write(Board board) {

        boardRepository.save(board);
    }

    public List<Board> boardList() {

        return boardRepository.findAll();
        //List 안에 담겨있는 모든 요소 반환
    }
}
