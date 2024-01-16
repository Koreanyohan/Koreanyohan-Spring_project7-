// 7장 p.16 
package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;
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
	
	@Override
	public int register(BoardDTO dto) {
			Board entity = dtoToEntity(dto); // dto받아서 Board엔터티 반환하는 dtoToEntity 메서드 사용.
			    	//ㄴ여기서는 게시물번호 no없음
			repository.save(entity); // dto -> Entity인 것을 repository에 저장 
		
			int newNo = entity.getNo(); // 게시물 번호 반환
						// ㄴ여기서는 게시물번호 no있음
			
			System.out.println(entity);
			
			return newNo;	 

	}

	@Override
	public List<BoardDTO> getList() {
		
		List<Board> result = repository.findAll(); //repository에서 Board 목록 가져와서 List에 저장
		
		List<BoardDTO> list = new ArrayList<>(); // BoardDTO 객체선언
		
		list = result.stream() //스트림 생성
				.map(entity ->entityToDTO(entity)) // 중간연산 entity 다량 생산
				.collect(Collectors.toList());	// 최종연산 entity들 => dto리스트
		
		return list;
	} 

}












