// 7장 p.16 
package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.BoardDTO;
import com.example.demo.entity.Board;
import com.example.demo.repository.BoardRepository;

@Service // 서비스 클래스로 지정하기. 컨테이너에 들어감. BoardService 호출시, BoardServiceImpl호출됨. 
// 인-페인 BoardService 구현한 객체로서 BoardServiceImpl의 객체가 생성, 컨테이너 저장됨. BoardRepositoryTest 가봐라
public class BoardServiceImpl implements BoardService {

	@Autowired
	private BoardRepository repository;
	
	@Override 	// 1. 게시물 등록
	public int register(BoardDTO dto) {
			Board entity = dtoToEntity(dto); // dto받아서 Board엔터티 반환하는 dtoToEntity 메서드 사용.
			    	//ㄴ여기서는 게시물번호 no없음
			repository.save(entity); // dto -> Entity인 것을 repository에 저장 
		
			int newNo = entity.getNo(); // 게시물 번호 반환
						// ㄴ여기서는 게시물번호 no있음
			
			System.out.println(entity);
			
			return newNo;	 

	}

	@Override // 게시물 목록조회 (p.19-21)
	public List<BoardDTO> getList() {
		
		List<Board> result = repository.findAll(); //repository에서 Board 목록 가져와서 List에 저장
		
		List<BoardDTO> list = new ArrayList<>(); // BoardDTO 객체선언
		
		list = result.stream() //스트림 생성
				.map(entity ->entityToDTO(entity)) // 중간연산 entity 다량 생산
				.collect(Collectors.toList());	// 최종연산 entity들 => dto리스트
		
		return list;
	}
	

	@Override // 3. 게시물 상세조회 (p.38~)
	public BoardDTO read(int no) {
		
		Optional<Board> result = repository.findById(no); // Optional이용, repository에서 entity 추출
		
		if (result.isPresent()) {
			Board board = result.get(); // //result에서 Optional 껍데기 벗긴 후 board에 넣기
			
			BoardDTO boardDTO = entityToDTO(board);  // entity인 board-> dto로(boardDTO)
			
			return boardDTO;
		}else {
			return null;
		}
		
	}
	

	@Override // 4. 게시물 수정 (p.46~)	
	public void modify(BoardDTO dto) { // 수정페이지에서 수정한 dto 입력받아서 데이터 수정하는 추상메서드
		
		Optional<Board> result = repository.findById(dto.getNo()); 
		// ㄴ BoardDto의 pk받는 메서드(getNo)이용, 수정할 엔티티값 repository에서 꺼냄
		
		if (result.isPresent()) {
			
			Board entity  = result.get(); //result에서 Optional 껍데기 벗기기
			
			// 기존 엔티티에서 제목과 내용만 변경
			entity.setTitle(dto.getTitle());
			entity.setContent(dto.getContent());
			
			// 다시 저장
			repository.save(entity);
		}	
		
	} 	
}






















