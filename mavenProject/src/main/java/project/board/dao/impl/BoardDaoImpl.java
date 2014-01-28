package project.board.dao.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import project.board.dao.BoardDao;
import project.mapperDao.MapperDao;


@Repository
public class BoardDaoImpl extends MapperDao implements BoardDao{
	
	/** NAMESPACE */
	private static final String NAMESPACE = "board.";
	
	public List selectBoard(HashMap hm){
		//RowBounds rowBounds = new RowBounds(0, 10);
		return getSqlSession().selectList(NAMESPACE + "selectBoard", hm);
	}

	public int selectBoardCount(HashMap hm){
		return getSqlSession().selectOne(NAMESPACE + "selectBoardCount", hm);
	}
}
