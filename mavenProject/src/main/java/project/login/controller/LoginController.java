package project.login.controller;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LoginController {

	@RequestMapping("login.do")
	public ModelAndView login(HttpServletRequest request){
		ModelAndView mav = new ModelAndView();
		mav.setViewName("login/login");
		
		return mav;
	}
	
	@RequestMapping("loginCheck.do")
	@ResponseBody
	public HashMap<String, String> loginCheck(HttpServletRequest request){
		HashMap<String, String> result = new HashMap<String, String>();
		result.put("ret", "meg");
		return result;
	}
}
