package com.study.borad.controller;

import com.study.borad.entity.Board;
import com.study.borad.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class BoardController {

    @Autowired
    private BoardService boardService;

    @GetMapping("/board/write") // localhost:8080/board/write
    public String boardWriteForm() {
        return "boardWrite";
    }

    @PostMapping("/board/writepro")
    public String boardWritePro(Board board) {

        boardService.write(board);

        return "";
    }

    @GetMapping("/board/list")
    public String boardList(Model model) {

        model.addAttribute("list", boardService.boardList());
        // 리스트 안에 있는 보드를 리스트라는 이름으로 전송

        return "boardlist";
    }

    @GetMapping("/board/view")
    public String boardView(Model model, Integer id) {
        //localhost:8080/board/view?id=1 ...
        model.addAttribute("board", boardService.boardView(id));
        return "boardview";
    }

    @GetMapping("/board/delete")
    public String boardDelete(Integer id) {

        boardService.boardDelete(id);
        // 글 삭제 후 게시물 리스트로 돌아가기
        return "redirect:/board/list";
    }

    @GetMapping("/board/modify/{id}")
    public String boardModify(@PathVariable("id") Integer id, Model model) {
    // @PathVariable : URL 내 id 부분을 인식하여 파라미터로 받음.

        model.addAttribute("board", boardService.boardView(id));

        return "boardModify";
    }

    /*
     * 덮어씌우는 방식은 문제가 생길 수 있기 때문에 JPA 변경감지를 통해
     * 엔티티에 수정이 이루어질 경우 트랙잭션이 끝날 때 자동으로 DB에
     * 반영되기 때문에 JPA 변경감지, JPA merge, JPA persist 등의
     * 공부가 필요하다.
     */
    @PostMapping("/board/update/{id}")
    public String boardUpdate(@PathVariable("id") Integer id, Board board) {
        /*
        Board boardTmp = boardService.boardView(id);
        boardTmp.setTitle(board.getTitle());
        boardTmp.setContent(board.getContent());

        boardService.write(boardTmp);
        */
        boardService.boardUpdate(id, board);

        return "redirect:/board/list";
    }
}
