package project.ajaxBoard.controller;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import project.ajaxBoard.service.AjaxBoardService;
import project.util.AjaxPaging;
import project.util.CommonUtil;

@Controller
public class AjaxBoardController extends CommonUtil{
	@Autowired
	private AjaxBoardService ajaxBoardService;
	
	@RequestMapping("ajaxBoard.do")
	public ModelAndView ajaxBoard(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HashMap<String, String> hm = new HashMap<String, String>();
		hm = mapBind(request); 
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("board/ajaxBoard");

		return mav;
	}
	
	@RequestMapping("callBoard.do")
	@ResponseBody
	public HashMap<String, String> callBoard(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HashMap<String, String> paramMap = new HashMap<String, String>();
		paramMap = mapBind(request); 
		
		// paging (S)
		int total = ajaxBoardService.selectAjaxBoardCount(paramMap);
		AjaxPaging ajaxPaging = new AjaxPaging(); 
		ajaxPaging.setBoardName(paramMap);		// 게시판명
		ajaxPaging.setActionname("callBoard");	// 액션명
		ajaxPaging.setListNumber(paramMap);
		String pagingHtml = ajaxPaging.pagingHatml(Integer.parseInt(paramMap.get("currentPage")), total);
		// paging (E)
		
		HashMap<String, String> resultMap = new HashMap<String, String>();
		resultMap.put("title", "제목입니다.");
		resultMap.put("pagingHtml", pagingHtml);
		
		return resultMap;
	}
}
