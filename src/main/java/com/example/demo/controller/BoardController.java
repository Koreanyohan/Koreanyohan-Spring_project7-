package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.dto.BoardDTO;
import com.example.demo.service.BoardService;

@Controller
@RequestMapping("/board")
public class BoardController {

    @Autowired
    BoardService service;

    // 메인화면 (p.8)
    @GetMapping("/main")
    public void main() {
    }
    
    
    // 1. 목록화면 (p.22 -> p.26)
    @GetMapping ("/list")
    public void list(Model model) { // 컨트롤러 통해서 dto-> view. 따라서 view에 model이용해서 dto 보내면 된다.
    	List<BoardDTO> list = service.getList();
    	
    	model.addAttribute("list", list);
    }
    
    
    
    // 2-1. 등록화면 (p.32)
    @GetMapping("/register")
    public void register() { // register.html 호출
    	
    }
        
    // 2-2. 등록처리
    @PostMapping("/register") // 위의 메서드는 void 반환이고 이거는 String 반환이라 "~" 부분 같아도 노상관.
    public String registerPost(BoardDTO dto, RedirectAttributes redictRedirectAttributes) {
    						      	//  화면에서 전달받은 게시물정보				ㄴ 전달자 객체 (model과 기능은 같다. redirect할때 쓸 뿐). 여기에 우리가 등록창에서 입력한 것이 들어옴.
    	// 게시물 등록하고 새로운 게시물 번호 반환
    	int no = service.register(dto);
    	
    	// 목록화면에 새로운 게시물 번호 전달
    	redictRedirectAttributes.addFlashAttribute("msg", no);
		
    	// 목록화면으로 이동. html경로 아님. url주소를 작성할 것.
    	return "redirect:/board/list"; // 매핑되어 있는 주소. 따라서 이것은 30행의 클래스 호출
    	
    };

    
    
}
