package project.fileUpload.dao;

import java.util.HashMap;
import java.util.List;

public interface FileUploadDao{
	public int fileNo();
	public int insertFile(HashMap hm);
	public List selectFileList(HashMap hm);
	public HashMap selectFileOne(HashMap hm);
	public int deleteFile(HashMap hm);
}
