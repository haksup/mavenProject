package project.fileUpload.service.impl;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Enumeration;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.io.Resources;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import project.fileUpload.dao.impl.FileUploadDaoImpl;
import project.fileUpload.service.FileUploadService;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

@Service
public class FileUploadServiceImpl implements FileUploadService{
	@Autowired
	private FileUploadDaoImpl fileUploadDao;
	
	@Override
	public void upload(HttpServletRequest request) {
		fileUpload(request);	// 파일을 업로드 한다.
	}

	@Override
	public List selectFileList(HashMap hm) {
		return fileUploadDao.selectFileList(hm);
	}

	@Override
	public HashMap selectFileOne(HashMap hm) {
		return fileUploadDao.selectFileOne(hm);
	}
	
	/**
	 * 파일을 업로드 한다.
	 * @param request
	 * @return
	 */
	public void fileUpload(HttpServletRequest request){
		File file = null;
        String savePath = "";
        int f_group = 0;
        
        String count = "";
        int maxSize = 5 * 1024 * 1024; // 최대 업로드 파일 크기 5MB(메가)로 제한
 
        try {
        	GregorianCalendar calendar = new GregorianCalendar();
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
			String getDate = dateFormat.format(calendar.getTime());
       	
			Properties config = new Properties();
			config.load(Resources.getResourceAsStream("config/filepath/filePath.properties"));
			savePath = config.getProperty("SAVE_PATH") + getDate;	// 저장 경로 년월일 단위로
			
			File dir = new File(savePath);

		   	if(!dir.exists())	// 해당 경로가 없다면
		   	{
		   		dir.mkdirs();	// 폴더를 만든다.
		   	}
		} catch (IOException e) {
		}
    	   
       try {
    	   /*
    	   * 전송 담당할 것을 생성하고 파일을 전송할 준비를 한다.
    	   * 전송할 파일 명을 가지고 있는 객체, 서버상의 절대 경로, 최대 업로드될 파일 크기, 문자 코드, 기본 보안 적용.
    	   * new DefaultFileRenamePolicy() : 똑같은 파일 명 이 업로드 되었을 때 파일 명이 1 로 변경 됨.(ex) 
    	      첫 번째 업로드 된 파일이 f.jpg 이면 두번째 업로드 된 파일의 이름은 f1.jpg가 된다.
    	   */
    	   MultipartRequest multi = new MultipartRequest(request,savePath, maxSize, "utf-8", new DefaultFileRenamePolicy());

    	   if(!"".equals(multi.getParameter("f_group").trim())){
    		   f_group = Integer.parseInt(multi.getParameter("f_group"));
    	   }

    	   if(f_group == 0){	// 첨부파일 번호가 없다면
    		   f_group = fileUploadDao.fileNo();	// 첨부파일 번호를 생성한다.
    	   }
  		
    	   count = multi.getParameter("count");
    	   
    	   Enumeration efiles = multi.getFileNames();
//    	   List list = Collections.list(efiles);
//    	   Collections.sort(list);
//    	   for (int i = 0 ; i <list.size(); i++) {
//               System.out.println("number = " + list.get(i));
//           }

    	   while(efiles.hasMoreElements()){
    		   String name = (String)efiles.nextElement();
    		   String saveFileName = multi.getFilesystemName(name); // 파일의 이름 얻기
    		   String orgFileName = multi.getOriginalFileName(name);  // 원래이름 얻기(하나일때는 fileName으로 검색)
    		   
    		   if(name == null)continue;

    		   file = multi.getFile(name);
    		   if(file == null) continue;	// 등록 파일이 없다면 다음 등록 파일로 넘어간다.
    		   
    		   int lidx = orgFileName.lastIndexOf('.');
    		   String filetype = orgFileName.substring(lidx + 1, orgFileName.length());	// 파일 확장자
    		   
    		   float size = ((int)(((float)file.length()/(float)1024) * 10 +0.5f))/10f;	// 단위 Kb
    		   
    		   HashMap hm = new HashMap();
    		   hm.put("f_group",f_group);
    		   hm.put("f_path",savePath);
    		   hm.put("f_name_org", orgFileName);
    		   hm.put("f_name_sav", saveFileName);
    		   hm.put("f_size", size);
    		   hm.put("f_extension", filetype);
    		   fileUploadDao.insertFile(hm);
    	   }
    	   
    	   deleteFile(multi, f_group);	// 파일 삭제
       }catch (Exception e) {
    	   System.out.print("예외 발생 : " + e);
       }

	}
	
	/**
	 * 파일 삭제
	 * @param multi
	 * @param f_group
	 */
	public void deleteFile(MultipartRequest multi, int f_group){
		if(multi.getParameterValues("delbox") != null){
			String[] delBox = multi.getParameterValues("delbox");
			HashMap hm = new HashMap();
			hm.put("f_group",f_group);
		
			for(int i = 0; i < delBox.length; i++){
				hm.put("f_order",delBox[i]);
				fileUploadDao.deleteFile(hm);
			}
 	   }
	}
	
}
