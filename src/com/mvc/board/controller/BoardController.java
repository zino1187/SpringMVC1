package com.mvc.board.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.mvc.board.model.Board;
import com.mvc.board.model.BoardDAOMybatis;

@Controller
@RequestMapping("/board")
public class BoardController {
	private BoardDAOMybatis boardDAO;
	
	//일반 메서드에 의한 주입!! (Injection)
	public void setBoardDAO(BoardDAOMybatis boardDAO) {
		this.boardDAO = boardDAO;
	}
	
	//목록요청을 처리
	@RequestMapping("/list.do")
	public ModelAndView getList(){
		List list=boardDAO.selectAll(); //3단계 
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("list",list); //request.setAttribute(); 와 동일
		mav.setViewName("board/list");		
		return mav;
	}
	
	//글쓰기 요청처리 
	@RequestMapping("/write.do")
	public String insert(Board board){
		
		boardDAO.insert(board);
		
		return "redirect:/board/list.do";
	}
	
	//상세보기 요청 처리 
	@RequestMapping("/detail.do")
	public ModelAndView select(int board_id){
		Board board=boardDAO.select(board_id); //3단계..
		ModelAndView mav = new ModelAndView("board/detail");
		mav.addObject("board",board); //4단계..
		return mav;
	}
	
	//삭제요청 처리 
	@RequestMapping("/delete.do")
	public String delete(int board_id){		
		boardDAO.delete(board_id);
		return "redirect:/board/list.do";
	}
	
	//수정요청 처리 
	@RequestMapping("/edit.do")
	public ModelAndView edit(Board board){
		boardDAO.update(board);
		
		ModelAndView mav = new ModelAndView("board/detail");
		mav.addObject("board", board);
		return mav;
	}
}





