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
			//��ȡ��ѯ�Ĳ���
			String name = request.getParameter("dname");
			String curPage = request.getParameter("curPage");
			//request.getParameter("curPage")
			Integer curPageInt = 1;
			if(curPage != null) {
				curPageInt = Integer.parseInt(curPage);
			}
			System.out.println(name);
			System.out.println(curPage);
			
			//��ѯ����
			PageTools tableList = myDesk.getTableListPage(name, curPageInt);
			//���õ�request��������
			request.setAttribute("tableList", tableList);
			//�����ݴ���ҳ�� 
			//����ת��
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
