// 7장 p.18 
package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.dto.BoardDTO;
import com.example.demo.entity.Board;

@SpringBootTest
public class BoardServiceTest {  // cf) BoardService라고 클래스이름 만들면 ㅈ된다.
	@Autowired
	BoardService service; // BoardService인페 구현한 BoardServiceImpl 객체로서 생성됨

	@Test 
	public void 게시물등록() { // p.18
		
		BoardDTO dto = BoardDTO.builder()
							.title("2번글").content("내용입니다").writer("또치")
							.build(); 
		
		int no = service.register(dto); 
		// 인페인 BoardService 타입의 service변수의 추상클래스 register 호출 
		// -> @Service선언하여 추상메서드 구현한 BoardServiceImpl의 register클래스 호출
		
		System.out.println("새로운 게시물 번호 : " + no);
	}
	
	@Test
	public void 게시물목록조회() { //(p.19-21)
		List<BoardDTO> list = service.getList(); // DTO타입 게시물목록 반환하는 추상 메서드 getList이용해서  BoardService인페 구현한 BoardServiceImpl 객체(service)에서 entity 리스트추출해서 dto리스트로 넣음
		for (BoardDTO dto : list) {
			System.out.println(dto);
		}
	}
	
	
	
}







