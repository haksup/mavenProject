package project.ajaxBoard.dao;

import java.util.HashMap;
import java.util.List;

public interface AjaxBoardDao {
	public int selectAjaxBoardCount(HashMap<String, String> paramMap);
	public List<?> selectAjaxBoard(HashMap<String, String> paramMap);
}
