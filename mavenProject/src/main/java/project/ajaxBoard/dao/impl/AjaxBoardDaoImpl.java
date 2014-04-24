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
	public List<?> selectAjaxBoardList(HashMap<String, String> paramMap) {
		return getSqlSession().selectList(NAMESPACE + "selectAjaxBoardList", paramMap);
	}

	@Override
	public int insertAjaxBoard(HashMap<String, String> paramMap) {
		return getSqlSession().insert(NAMESPACE + "insertAjaxBoard", paramMap);
	}

	@Override
	public HashMap<String, Object> selectAjaxBoard(HashMap<String, String> paramMap) {
		return getSqlSession().selectOne(NAMESPACE + "selectAjaxBoard", paramMap);
	}

	@Override
	public int updateAjaxBoard(HashMap<String, String> paramMap) {
		return getSqlSession().update(NAMESPACE + "updateAjaxBoard", paramMap);
	}

	@Override
	public int deleteAjaxBoard(HashMap<String, String> paramMap) {
		return getSqlSession().delete(NAMESPACE + "deleteAjaxBoard", paramMap);
	}

}
