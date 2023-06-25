package com.study.borad.service;

import com.study.borad.entity.Board;
import com.study.borad.repository.BoardRepository;
import org.hibernate.MultiIdentifierLoadAccess;
import org.hibernate.cache.spi.support.AbstractReadWriteAccess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.File;
import java.util.List;
import java.util.UUID;

@Service
public class BoardService {

    @Autowired // 스프링 빈이 알아서 값을 넣어줌
    private BoardRepository boardRepository;
    // 글 작성 처리
    public void write(Board board, MultipartFile file) throws Exception {

        String path = System.getProperty("user.dir") + "\\src\\main\\resources\\static\\files";
        // 랜덤으로 고유 id를 만듬
        UUID uuid = UUID.randomUUID();
        // 저장될 파일 이름 생성
        String fileName = uuid + "_" + file.getOriginalFilename();

        File saveFile = new File(path, fileName);
        file.transferTo(saveFile);

        board.setFilename(fileName);
        board.setFilepath("/files/" + fileName);

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

    // 변경 감지(Dirty Check)를 통한 게시물 수정
    @Transactional
    public Board boardUpdate(Integer id, Board board) throws Exception {
        // 영속 상태로 변경
        Board boardRes = boardRepository.findById(id).get();

        boardRes.setTitle(board.getTitle());
        boardRes.setContent(board.getContent());

        return boardRes;
    }
}
