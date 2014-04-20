package project.board.controller;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import project.board.service.BoardService;
import project.util.CommonUtil;
import project.util.Paging;

@Controller
public class BoardController extends CommonUtil {
	@Autowired
	private BoardService boardService;
	
	@RequestMapping("board.do")
	public ModelAndView board(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HashMap<String, String> hm = new HashMap<String, String>();
		hm = mapBind(request);
		hm.put("boardName", "NOTICE");
		
		// 페이징(S)
		int total = boardService.selectBoardCount(hm);
		Paging paging = new Paging(); 
		paging.setBoardName("NOTICE");		// 게시판명
		paging.setActionname("board");	// 액션명
		paging.setListNumber(hm);
		String pagingHtml = paging.pagingHatml(Integer.parseInt(hm.get("currentPage")), total);
		// 페이징(E)
		
		//hm.put("pam01", "1");
		hm.put("pam02", "N");
		
		List<?> list = boardService.selectBoard(hm);

		ModelAndView mav = new ModelAndView();
		mav.setViewName("board/board");
		mav.addObject("BOARD_LIST", list);
		mav.addObject("pagingHtml", pagingHtml);

		return mav;
	}
}
