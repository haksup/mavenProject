package project.board.dao;

import java.util.HashMap;
import java.util.List;

public interface BoardDao {
	public List<?> selectBoard(HashMap<?, ?> hm);
	public int selectBoardCount(HashMap<?, ?> hm);
}
