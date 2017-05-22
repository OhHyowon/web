package paging.controller;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import paging.service.ItemService;
import paging.service.impl.ItemServiceImpl;

public class ListController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			//1. 요청파라미터 조회 + 검증
			int page = 1; //기본페이지 1
			try {
				page = Integer.parseInt(request.getParameter("page")); //보려는 페이지 번호 조회
			}catch(Exception e) {}
			
			//2. 비즈니스 로직 - Model 호출
			ItemService service = ItemServiceImpl.getInstance();
			Map<String, Object> map = service.getItemList(page);
			
			//3. 결과 응답 - View 호출
			request.setAttribute("list", map.get("list"));
			request.setAttribute("pageBean", map.get("pageBean"));
			
			request.getRequestDispatcher("/item_list.jsp").forward(request, response);
			
			
		} catch(Exception ex) {
			//에러페이지로 이동
			ex.printStackTrace();
		}
	}
}







