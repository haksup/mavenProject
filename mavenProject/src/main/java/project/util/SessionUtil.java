package project.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;

public class SessionUtil {
	private HttpSession session;
	
	public SessionUtil(HttpServletRequest request){
		this.session = request.getSession();
	}
	
	/**
	 * 세션 속성 추가
	 * @param arg0
	 * @param arg1
	 */
	public void setAttribute(String arg0, Object arg1){
		session.setAttribute(arg0, arg1);
	}
	
	/**
	 * 세션 속성 호출
	 * @param arg0
	 * @return
	 */
	public Object getAttribute(String arg0){
		return session.getAttribute(arg0);
	}

	/**
	 * 세션 속성 제거
	 * @param arg0
	 */
	public void removeAttribute(String arg0){
		session.removeAttribute(arg0);
	}
	
	/**
	 * 로그인 정보가 없다면 로그인 화면 이동
	 */
	public ModelAndView loginCheck() throws Throwable{
		ModelAndView mav = new ModelAndView();
		System.out.println("aaaa");
//		if(session.getAttribute("userId") == null){
//			mav.setViewName("login/login");
//		}
		return mav;
	}
}
