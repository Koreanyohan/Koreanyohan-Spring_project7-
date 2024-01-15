// 7장 p.18 
package com.example.demo.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.dto.BoardDTO;

@SpringBootTest
public class BoardServiceTest {  // cf) BoardService라고 클래스이름 만들면 ㅈ된다.
	@Autowired
	BoardService service; // BoardService인페 구현한 BoardServiceImpl 객체로서 생성됨

	@Test
	public void 게시물등록() {
		
		BoardDTO dto = BoardDTO.builder()
							.title("2번글").content("내용입니다").writer("또치")
							.build();
		
		int no = service.register(dto); 
		// 인페인 BoardService 타입의 service변수의 추상클래스 register 호출 
		// -> @Service선언하여 추상메서드 구현한 BoardServiceImpl의 register클래스 호출
		
		System.out.println("새로운 게시물 번호 : " + no);
	}
}
