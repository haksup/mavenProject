package project.ajaxBoard.service;

import java.util.HashMap;
import java.util.List;

public interface AjaxBoardService {
	public int selectAjaxBoardCount(HashMap<String, String> paramMap);
	public List<?> selectAjaxBoardList(HashMap<String, String> paramMap);
	public int insertAjaxBoard(HashMap<String, String> paramMap);
	public HashMap<String, Object> selectAjaxBoard(HashMap<String, String> paramMap);
	public int updateAjaxBoard(HashMap<String, String> paramMap);
	public int deleteAjaxBoard(HashMap<String, String> paramMap);
}
