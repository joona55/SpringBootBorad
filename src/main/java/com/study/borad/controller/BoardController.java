package com.study.borad.controller;

import com.study.borad.entity.Board;
import com.study.borad.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
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

    @PostMapping("board/writepro")
    public String boardWritePro(Board board) {

        boardService.write(board);

        return "";
    }
}
