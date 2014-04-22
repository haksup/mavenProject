package project.ajaxBoard.dao.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import project.ajaxBoard.dao.AjaxBoardDao;
import project.mapperDao.MapperDao;

@Repository
public class AjaxBoardDaoImpl extends MapperDao implements AjaxBoardDao{
	/** NAMESPACE */
	private static final String NAMESPACE = "ajaxBoard.";
	
	@Override
	public int selectAjaxBoardCount(HashMap<String, String> paramMap) {
		return getSqlSession().selectOne(NAMESPACE + "selectAjaxBoardCount", paramMap);
	}

	@Override
	public List<?> selectAjaxBoard(HashMap<String, String> paramMap) {
		return getSqlSession().selectList(NAMESPACE + "selectAjaxBoard", paramMap);
	}

}
