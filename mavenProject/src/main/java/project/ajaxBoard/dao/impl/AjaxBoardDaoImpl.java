package project.ajaxBoard.dao.impl;

import java.util.HashMap;

import org.springframework.stereotype.Repository;

import project.ajaxBoard.dao.AjaxBoardDao;
import project.mapperDao.MapperDao;

@Repository
public class AjaxBoardDaoImpl extends MapperDao implements AjaxBoardDao{
	/** NAMESPACE */
	private static final String NAMESPACE = "ajaxBoard.";
	
	@Override
	public int selectAjaxBoardCount(HashMap<String, String> paramMap) {
		return getSqlSession().selectOne(NAMESPACE + "selectBoardCount", paramMap);
	}

}
