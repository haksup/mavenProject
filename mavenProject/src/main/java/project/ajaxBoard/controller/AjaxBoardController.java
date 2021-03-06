package project.ajaxBoard.controller;

import java.util.HashMap;
import java.util.List;

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
		//
		ModelAndView mav = new ModelAndView();
		mav.setViewName("board/ajaxBoard");

		return mav;
	}
	
	@RequestMapping("callAjaxBoard.do")
	@ResponseBody
	public HashMap<String, Object> callAjaxBoard(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HashMap<String, String> paramMap = new HashMap<String, String>();
		paramMap = mapBind(request); 
		
		// paging (S)
		int total = ajaxBoardService.selectAjaxBoardCount(paramMap);
		AjaxPaging ajaxPaging = new AjaxPaging(paramMap); 
		String pagingHtml = ajaxPaging.pagingHtml(Integer.parseInt(paramMap.get("currentPage")), total);
		// paging (E)
		
		List<?> list = ajaxBoardService.selectAjaxBoardList(paramMap);
		
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("boardList", list);
		resultMap.put("pagingHtml", pagingHtml);
		
		return resultMap;
	}
	
	@RequestMapping("insertAjaxBoard.do")
	@ResponseBody
	public HashMap<String, Object> insertAjaxBoard(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HashMap<String, String> paramMap = new HashMap<String, String>();
		paramMap = mapBind(request); 
		
		ajaxBoardService.insertAjaxBoard(paramMap);
		
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		return resultMap;
	}
	
	@RequestMapping("boardAjaxDetail.do")
	@ResponseBody
	public HashMap<String, Object> boardAjaxDetail(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HashMap<String, String> paramMap = new HashMap<String, String>();
		paramMap = mapBind(request); 
		
		HashMap<String, Object> resultMap = ajaxBoardService.selectAjaxBoard(paramMap);
		
		return resultMap;
	}
	
	@RequestMapping("modifyAjaxBoard.do")
	public void modifyAjaxBoard(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HashMap<String, String> paramMap = new HashMap<String, String>();
		paramMap = mapBind(request); 
		
		ajaxBoardService.updateAjaxBoard(paramMap);
		
	}

	@RequestMapping("deleteAjaxBoard.do")
	public void deleteAjaxBoard(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HashMap<String, String> paramMap = new HashMap<String, String>();
		paramMap = mapBind(request); 
		
		ajaxBoardService.deleteAjaxBoard(paramMap);
		
	}
	
	
	
	
}
