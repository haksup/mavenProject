package project.util;

import java.util.HashMap;

public class AjaxPaging {
	private int currentPage; // 현재페이지
	private int totalCount;	 // 전체 게시물 수
	private int totalPage;	 // 전체 페이지 수
	private final int blockCount = 10;	 // 한 페이지의  게시물의 수
	private final int blockPage = 5;	 // 한 화면에 보여줄 페이지 수
//	private int startCount;	 // 한 페이지에서 보여줄 게시글의 시작 번호
//	private int endCount;	 // 한 페이지에서 보여줄 게시글의 끝 번호
	private int startPage;	 // 시작 페이지
	private int endPage;	 // 마지막 페이지
	private String actionname; // 액션명
	private String innerBoardName; // 화면에 그릴 게시판ID
	private String boardName;  // 게시판명  
	private StringBuffer pagingHtml;
	
	public AjaxPaging(HashMap<String, String> paramMap){
		setListNumber(paramMap);	// 현재 페이지 계산
		setBoardName(paramMap);		// 게시판명
		setInnerBoardName(paramMap);	// 화면에 그릴 게시판ID
	}
	
	/**
	 * 현재 페이지 및 그에 따른 리스트 번호를 계산한다.
	 * @param hm
	 */
	public void setListNumber(HashMap<String, String> hm){
		if(hm.get("currentPage") == null)	// 현재페이지가  null이면 첫페이지로
			hm.put("currentPage", "1");
		
		if(hm.get("currentPage") == "1"){	// 리스트의 시작 숫자 계산
			hm.put("liststart", "1");
		}
		else{
			int currentPage = Integer.parseInt((String) hm.get("currentPage")) - 1; 
			hm.put("liststart", currentPage * blockCount + 1 + "");
		}
		
		hm.put("listend", Integer.parseInt((String) hm.get("currentPage")) * getBlockCount() + "");	// 리스트의 마지막 숫자 계산
	}
	
	/**
	 * 현재페이지를 찾는다.
	 * @param hm
	 * @return
	 */
//	public int getPage(HashMap hm){
//		int currentPage = 1;
//		if(hm.get("currentPage") != null)
//			currentPage = Integer.parseInt((String) hm.get("currentPage"));
//		return currentPage;
//	}

	/**
	 * 시작할 위치를 찾는다.
	 * @param hm
	 * @return
	 */
	public int getOffset(int currentPage){
		int offset = blockCount * (currentPage-1);
		return offset;
	}
	
	/**
	 * 페이징 html화면을 그린다.
	 * @param currentPage
	 * @param totalCount
	 * @return
	 */
	public String pagingHtml(int currentPage, int totalCount) {
		this.currentPage = currentPage;
		this.totalCount = totalCount;
		
		// 전체 페이지 수
		totalPage = (int) Math.ceil((double) totalCount / blockCount);
		if (totalPage == 0) {
			totalPage = 1;
		}
		// 현재 페이지가 전체 페이지 수보다 크면 전체 페이지 수로 설정
		if (currentPage > totalPage) {
			currentPage = totalPage;
		}
		// 현재 페이지의 처음과 마지막 글의 번호 가져오기.
//		startCount = (currentPage - 1) * blockCount;
//		endCount = startCount + blockCount - 1;
		// 시작 페이지와 마지막 페이지 값 구하기.
		startPage = (int) ((currentPage - 1) / blockPage) * blockPage + 1;
		endPage = startPage + blockPage - 1;
		// 마지막 페이지가 전체 페이지 수보다 크면 전체 페이지 수로 설정
		if (endPage > totalPage) {
			endPage = totalPage;
		}
		// 이전 block 페이지
		pagingHtml = new StringBuffer();
		
		if (currentPage > blockPage) {
			pagingHtml
			.append("&nbsp;<a href='#' onClick=\"fn_callBoard('" + boardName + "', '" + innerBoardName + "', ");
			pagingHtml.append(1);
			pagingHtml.append(") \">");
			pagingHtml.append("처음으로");
			pagingHtml.append("</a>");
			pagingHtml.append("&nbsp;");
			pagingHtml.append("<a href='#' onClick=\"fn_callBoard('" + boardName + "', '" + innerBoardName + "', "
					+ (startPage - 1) + ") \">");
			pagingHtml.append("이전");
			pagingHtml.append("</a>");
		}
		pagingHtml.append("&nbsp;");
		//페이지 번호.현재 페이지는 빨간색으로 강조하고 링크를 제거.
		for (int i = startPage; i <= endPage; i++) {
			if (i > totalPage) {
				break;
			}
			if (i == currentPage) {
				pagingHtml.append("&nbsp;<b> <span class='norfont'>");
				pagingHtml.append(i);
				pagingHtml.append("</span></b>");
			} else {
				pagingHtml
						.append("&nbsp;<a href='#' onClick=\"fn_callBoard('" + boardName + "', '" + innerBoardName + "', ");
				pagingHtml.append(i);
				pagingHtml.append(")\"  class='norfont'>");
				pagingHtml.append(i);
				pagingHtml.append("</a>");
			}
			pagingHtml.append("&nbsp;");
		}
		pagingHtml.append("&nbsp;");
		// 다음 block 페이지
		if (totalPage - startPage >= blockPage) {
			pagingHtml.append("<a href='#' onClick=\"fn_callBoard('" + boardName + "', '" + innerBoardName + "', "
					+ (endPage + 1) + ") \">");
			pagingHtml.append("다음");
			pagingHtml.append("</a>");
			pagingHtml.append("&nbsp;");
			pagingHtml
			.append("&nbsp;<a href='#' onClick=\"fn_callBoard('" + boardName + "', '" + innerBoardName + "', ");
			pagingHtml.append(totalPage);
			pagingHtml.append(") \">");
			pagingHtml.append("끝");
			pagingHtml.append("</a>");
		}
		
		return pagingHtml.toString();
	}
	
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	public int getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
	public StringBuffer getPagingHtml() {
		return pagingHtml;
	}
	public void setPagingHtml(StringBuffer pagingHtml) {
		this.pagingHtml = pagingHtml;
	}
	public int getBlockCount() {
		return blockCount;
	}
	public void setActionname(String actionname) {
		this.actionname = actionname;
	}
	public void setBoardName(HashMap<String, String> paramMap) {
		this.boardName = (String) paramMap.get("boardName");
	}
	public void setInnerBoardName(HashMap<String, String> paramMap) {
		this.innerBoardName = (String) paramMap.get("innerBoardName");
	}
	
	
}
