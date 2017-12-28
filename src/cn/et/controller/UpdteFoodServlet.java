package cn.et.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.et.model.MyFoodType;
import cn.et.model.PageTools;

/**
 * Servlet implementation class UpdteFoodServlet
 */
public class UpdteFoodServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdteFoodServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    public void UpdteServlet() {
    	//orcale修改语句update FOODTYPE set typename='' where typeid='';
    }

    MyFoodType mft = new MyFoodType();
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//设置字符集
		request.setCharacterEncoding("UTF-8");
		//获取查询的参数
		String dname = request.getParameter("dname");
		String typeid = request.getParameter("did");
		try {
			mft.UpdateFoodType(typeid, dname);
			//请求转发
			request.getRequestDispatcher("/ftc").forward(request, response);
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
