package cn.et.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.et.model.MyFoodType;
import cn.et.model.PageTools;

/**
 * Servlet implementation class FoodTypeController
 */
public class FoodTypeController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FoodTypeController() {
        super();
        // TODO Auto-generated constructor stub
    }
    MyFoodType mft = new MyFoodType();
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
			//查询数据
			PageTools tableList = mft.getTableListPage(name, curPageInt);
			//设置到request作用域中
			request.setAttribute("tableList", tableList);
			//将数据传到页面 
			//请求转发
			request.getRequestDispatcher("/detail/cuisineList.jsp").forward(request, response);;
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
