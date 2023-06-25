package com.study.borad.controller;

import com.study.borad.entity.Board;
import com.study.borad.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class BoardController {

    @Autowired
    private BoardService boardService;

    @GetMapping("/board/write") // localhost:8080/board/write
    public String boardWriteForm() {
        return "boardWrite";
    }

    @PostMapping("/board/writepro")
    public String boardWritePro(Board board, Model model, MultipartFile file) throws Exception {

        boardService.write(board, file);

        model.addAttribute("message", "글 작성이 완료되었습니다.");
        model.addAttribute("searchUrl", "/board/list");

        return "message";
    }

    @GetMapping("/board/list")
    public String boardList(Model model, @PageableDefault(page = 0, size = 10, sort = "id", direction = Sort.Direction.DESC)
                            Pageable pageable) {
        Page<Board> list = boardService.boardList(pageable);

        int nowPage = list.getPageable().getPageNumber() + 1;
        int startPage = Math.max(nowPage - 4, 1);
        int endPage = Math.min(nowPage + 5, list.getTotalPages());

        model.addAttribute("list", list);
        model.addAttribute("nowPage", nowPage);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
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
    public String boardDelete(Integer id, Model model) {

        model.addAttribute("message", "글 삭제가 완료되었습니다.");
        model.addAttribute("searchUrl", "/board/list");
        boardService.boardDelete(id);
        // 글 삭제 후 게시물 리스트로 돌아가기
        return "message";
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
    public String boardUpdate(@PathVariable("id") Integer id, Board board,
                              Model model, MultipartFile file) throws Exception {
        /*
        Board boardTmp = boardService.boardView(id);
        boardTmp.setTitle(board.getTitle());
        boardTmp.setContent(board.getContent());

        boardService.write(boardTmp);
        */
        model.addAttribute("message", "글 수정이 완료되었습니다.");
        model.addAttribute("searchUrl", "/board/list");
        
        boardService.boardUpdate(id, board);
    
        return "message";
    }
}
