package project.board.service.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Service;

import project.board.dao.impl.BoardDaoImpl;
import project.board.service.BoardService;

@Service
public class BoardServiceImpl implements BoardService{
	private BoardDaoImpl boardDao = new BoardDaoImpl();
	
	public List<?> selectBoard(HashMap<?, ?> hm) {
		return boardDao.selectBoard(hm);
	}

	@Override
	public int selectBoardCount(HashMap<?, ?> hm) {
		return boardDao.selectBoardCount(hm);
	}

}
