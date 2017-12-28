package cn.et.controller;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.et.model.MyDesk;
import cn.et.model.PageTools;

/**
 * Servlet implementation class FoodTypeDesk
 */
public class FoodTypeDesk extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FoodTypeDesk() {
        super();
        // TODO Auto-generated constructor stub
    }

    MyDesk myDesk = new MyDesk();
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			//获取查询的参数
			String name = request.getParameter("dname");
			String curPage = request.getParameter("curPage");
			//request.getParameter("curPage")
			Integer curPageInt = 1;
			if(curPage != null) {
				curPageInt = Integer.parseInt(curPage);
			}
			System.out.println(name);
			System.out.println(curPage);
			
			//查询数据
			PageTools tableList = myDesk.getTableListPage(name, curPageInt);
			//设置到request作用域中
			request.setAttribute("tableList", tableList);
			//将数据传到页面 
			//请求转发
			request.getRequestDispatcher("/detail/boardList.jsp").forward(request, response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	
}
