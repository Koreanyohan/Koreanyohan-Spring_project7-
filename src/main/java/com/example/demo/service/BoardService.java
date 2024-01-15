// 7장 p.16 
package com.example.demo.service;

import com.example.demo.dto.BoardDTO;
import com.example.demo.entity.Board;

public interface BoardService {

	// 게시물 등록
	int register (BoardDTO dto); // DTO받아서 int로 반환하는 추상메서드. 

	//	default키워드 이용해서 일반메서드 인-페에 생성 가능. (java8부터) 
	// -> 즉, 인페 상속받는 클래스가 공통으로 상용할 것은 default메서드로, 자손 각자가 구현해서 사용할것은 추상메서드로 인페에 선언.
	default Board dtoToEntity(BoardDTO dto) {
//			ㄴ Entity	 <=		ㄴ DTO
		Board entity = Board.builder()
				.no(dto.getNo())
				.title(dto.getTitle())
				.content(dto.getContent())
				.writer(dto.getWriter()) // 날짜 생략
				.build();

		return entity;
		// dto데이터 받아서 entity로 옮기기
	}
}
