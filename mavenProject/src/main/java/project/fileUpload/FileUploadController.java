package project.fileUpload;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import project.fileUpload.service.FileUploadService;
import project.util.CommonUtil;

@Controller
public class FileUploadController extends CommonUtil {
	@Autowired
	private FileUploadService fileUploadService;
	
	/**
	 * 파일 리스트
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("uploadView.do")
	public ModelAndView uploadView(HttpServletRequest request) throws Exception {
		HashMap hm = new HashMap();
		hm = mapBind(request);
		List fileList = fileUploadService.selectFileList(hm);
		   
		ModelAndView mav = new ModelAndView();
		mav.setViewName("fileUpload/fileUpload");
		mav.addObject("fileList", fileList);
		return mav;
	} 

	/**
	 * 파일 저장 
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("upload.do")
	public ModelAndView upload(HttpServletRequest request) throws Exception {
		fileUploadService.upload(request);
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("fileUpload/fileUpload");
		return mav;
    }
	
	/**
	 * 파일 다운로드
	 * @param request
	 * @throws Exception
	 */
	@RequestMapping("fileDownload.do")
	public void fileDownload(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HashMap hm = new HashMap();
		hm = mapBind(request);
		
		HashMap downMap =  fileUploadService.selectFileOne(hm);
		/** 경로 및 원본파일 이름 */
		String fullPath = (String) downMap.get("F_PATH") + "/" + downMap.get("F_NAME_SAV");
		String orgName = (String) downMap.get("F_NAME_ORG");
		
		InputStream is = null;
		ServletOutputStream sos = null;
		
		try{
			/** 파일 객체를 생성 */
			File file  = new File(fullPath);
	
			response.reset();
			String strClient = request.getHeader("User-Agent");
			
			/** 파일이 존재하지 않을 경우 */
			if(file.exists() == false){
				response.setHeader("Content-type", "text/html; charset=UTF-8");
				response.getWriter().print("<script>alert('선택하신 파일이 저장소에 존재 하지 않습니다.');</script>");
				return;
			}
			
			/** 한글명 다운로드 처리 */
			orgName = new String(orgName.getBytes("KSC5601"),"8859_1");
			
			/** 브라우저 처리 */
			if(strClient.indexOf("MSIE 5.5") != -1){
				response.setHeader("Content-Disposition","filename="+orgName+";");
			}
			else{
				response.setHeader("Content-Disposition","attachment; filename=" + orgName + ";");
				response.setHeader("Content-Type", "application/octet-stream; charset=UTF-8");
			}
	
			/** 기타 헤더 셋팅 */
			response.setHeader("Content-Length",""+file.length() );
			response.setHeader("Content-Transfer-Encoding","binary;");
			response.setHeader("pragma","no-cache;" );
			response.setHeader("Expires", "-1"); 
	 
			/** 파일데이터 response에 전송 */
			is = new FileInputStream(file);
			sos = response.getOutputStream();
			
			byte buffer[] = new byte[1024];
	 	 	int len = 0;
	 	 	while((len = is.read(buffer)) > 0 ){
	 	 		sos.write(buffer, 0, len);
	 	 		sos.flush();
	 	 	}	 	
			
		}
		catch(Exception e){
			System.out.println("file down load failed! ");
			System.out.println("error = "+e.getStackTrace());  
			throw e;
		}
		finally{
			if(is != null) try{is.close(); } catch(Exception e){}
			if(sos!= null) try{sos.close();} catch(Exception e){}
		}
		
	}
	
}
