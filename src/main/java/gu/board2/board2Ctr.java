package gu.board2;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import gu.common.PageVO;

@Controller
public class board2Ctr {
	
	@Autowired
	private board2Svc boardSvc;
	
	// 리스트
	@RequestMapping(value = "/board2List")
	public String boardList(PageVO pageVO, ModelMap modelMap) throws Exception {
		pageVO.pageCalculate(boardSvc.selectBoardCount()); // startRow, endRow
				
		List<?> listview = boardSvc.selectBoardList(pageVO);
		
		modelMap.addAttribute("listview", listview);
		
		return "board2/boardList";
	}
	
	// 글 쓰기
	@RequestMapping(value = "/board1Form")
	public String boardForm() throws Exception {
		
		return "board1/boardForm";		
	}
	
	@RequestMapping(value = "/board1Save")
	public String boardSave(@ModelAttribute boardVO boardInfo) throws Exception {
		
		boardSvc.insertBoard(boardInfo);
		
		return "redirect:/board1List";
	}
	
	// 글 수정
	@RequestMapping(value = "/board1Update")
	public String boardUpdate(HttpServletRequest request, ModelMap modelMap) throws Exception {
		
		String brdno = request.getParameter("brdno");
		
		boardVO boardInfo = boardSvc.selectBoardOne(brdno);
		
		modelMap.addAttribute("boardInfo", boardInfo);
		
		return "board1/boardUpdate";
	}
	
	@RequestMapping(value = "/board1UpdateSave")
	public String board1UpdateSave(@ModelAttribute boardVO boardInfo) throws Exception {
		
		boardSvc.updateBoard(boardInfo);
		
		return "redirect:/board1List";
	}
	
	// 글 읽기
	@RequestMapping(value = "/board1Read")
	public String boardRead(HttpServletRequest request, ModelMap modelMap) throws Exception {
		
		String brdno = request.getParameter("brdno");
		
		boardVO boardInfo = boardSvc.selectBoardOne(brdno);
		
		modelMap.addAttribute("boardInfo", boardInfo);
		
		return "board1/boardRead";
	}
	
	// 글 삭제
	@RequestMapping(value = "/board1Delete")
	public String boardDelete(HttpServletRequest request) throws Exception {
		
		String brdno = request.getParameter("brdno");
		
		boardSvc.deleteBoardOne(brdno);
		
		return "redirect:/board1List";
	}
}
