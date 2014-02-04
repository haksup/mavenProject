package project.login.controller;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import project.login.service.LoginService;
import project.util.CommonUtil;
import project.util.SessionUtil;

@Controller
public class LoginController extends CommonUtil{

	@Autowired
	LoginService loginService;
	
	@RequestMapping("login.do")
	public ModelAndView login(HttpServletRequest request){
		ModelAndView mav = new ModelAndView();
		mav.setViewName("login/login");
		
		return mav;
	}
	
	@RequestMapping("loginCheck.do")
	@ResponseBody
	public HashMap<String, String> loginCheck(HttpServletRequest request){
		HashMap<String, String> hm = new HashMap<String, String>();
		hm = mapBind(request);
		
		HashMap<String, String> hashMap = new HashMap<String, String>();
		hashMap = loginService.selectUserCheck(hm);
		
		if(hashMap == null){
			hashMap = new HashMap<String, String>();
			hashMap.put("ret", "false");
		}
		else{
			SessionUtil sessionUtil = new SessionUtil(request);
			sessionUtil.setAttribute("userId", hashMap.get("USER_ID"));
			sessionUtil.setAttribute("name", hashMap.get("NAME"));

			hashMap.put("ret", "true");
		}
		
		return hashMap;
	}
	
	@RequestMapping("btnLoginConfirm.do")
	@ResponseBody
	public HashMap<String, Object> btnLoginConfirm(HttpServletRequest request){
		HashMap<String, Object> hashMap = new HashMap<String, Object>();
		SessionUtil sessionUtil = new SessionUtil(request);
		if(sessionUtil.getAttribute("userId") == null){
			hashMap.put("loginYn", "N");
		}
		else{
			hashMap.put("loginYn", "Y");
		}
		
		return hashMap;
	}
	
}
