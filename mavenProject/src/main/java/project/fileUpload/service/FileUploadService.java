package project.fileUpload.service;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

public interface FileUploadService {
	public void upload(HttpServletRequest request);
	public List selectFileList(HashMap hm);
	public HashMap selectFileOne(HashMap hm);
}
