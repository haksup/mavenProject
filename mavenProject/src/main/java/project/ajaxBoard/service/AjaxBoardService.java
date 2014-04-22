package project.ajaxBoard.service;

import java.util.HashMap;
import java.util.List;

public interface AjaxBoardService {
	public int selectAjaxBoardCount(HashMap<String, String> paramMap);
	public List<?> selectAjaxBoard(HashMap<String, String> paramMap);
}
