package project.fileUpload.dao.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import project.fileUpload.dao.FileUploadDao;
import project.mapperDao.MapperDao;

@Repository
public class FileUploadDaoImpl extends MapperDao implements FileUploadDao{
	/** NAMESPACE */
	private static final String NAMESPACE = "fileUpload.";
	
	public int fileNo(){
		return getSqlSession().selectOne(NAMESPACE + "fileNo");
	}
	
	public int insertFile(HashMap hm){
		return getSqlSession().insert(NAMESPACE + "insertFile", hm);
	}

	public List selectFileList(HashMap hm){
		return getSqlSession().selectList(NAMESPACE + "selectFileList", hm);
	}

	public HashMap selectFileOne(HashMap hm){
		return getSqlSession().selectOne(NAMESPACE + "selectFileOne", hm);
	}
	
	public int deleteFile(HashMap hm){
		return getSqlSession().delete(NAMESPACE + "deleteFile", hm);
	}
}
