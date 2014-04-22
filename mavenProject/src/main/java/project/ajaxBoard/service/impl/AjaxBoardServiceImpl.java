package project.ajaxBoard.service.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import project.ajaxBoard.dao.AjaxBoardDao;
import project.ajaxBoard.service.AjaxBoardService;

@Service
public class AjaxBoardServiceImpl implements AjaxBoardService {
	@Autowired
	private AjaxBoardDao ajaxBoardDao;
	
	@Override
	public int selectAjaxBoardCount(HashMap<String, String> paramMap) {
		return ajaxBoardDao.selectAjaxBoardCount(paramMap);
	}

	@Override
	public List<?> selectAjaxBoard(HashMap<String, String> paramMap) {
		return ajaxBoardDao.selectAjaxBoard(paramMap);
	}

}
