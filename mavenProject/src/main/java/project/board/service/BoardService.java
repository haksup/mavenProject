package project.board.service;

import java.util.HashMap;
import java.util.List;

public interface BoardService {
	public List<?> selectBoard(HashMap<?, ?> hm);
	public int selectBoardCount(HashMap<?, ?> hm);
}
