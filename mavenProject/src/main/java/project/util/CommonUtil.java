package project.util;

import java.util.Enumeration;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

public class CommonUtil {

	/**
	 * request를 HashMap에 binding한다
	 * @param request
	 * @return
	 */
	public HashMap<String, String> mapBind(HttpServletRequest request){
		HashMap<String, String> hm = new HashMap<String, String>();
		String key; 
		for(
				Enumeration<?> e = request.getParameterNames(); 
				e.hasMoreElements(); 
				hm.put(key, request.getParameter(key))) {
				key = (String)e.nextElement(); 
		}
		
		return hm;
	}
}
