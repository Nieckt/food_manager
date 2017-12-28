package cn.et.controller;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import cn.et.model.MyFoodType;

/**
 * Servlet implementation class AddFoodController
 */
public class AddFoodController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddFoodController() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    MyFoodType mft = new MyFoodType();
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		ServletFileUpload.isMultipartContent(request);
		DiskFileItemFactory factory = new DiskFileItemFactory();
		//文件大小的最大值将被存储在内存中
		//factory.setSizeThreshold(maxMemSize);
		//设置文件的临时路径
		//factory.setRepository(new File("c:\\temp"));
		//创建一个新的文件处理程序
		ServletFileUpload upload = new ServletFileUpload(factory); //允许上传的文件大小的最大值upload.setSizeThreshold
		upload.setHeaderEncoding("UTF-8");
		//解析请求，获取文件项
		List fileItems;
		String foodName = null;
		String price = null;
		String typeId = null;
		ServletContext sc = this.getServletContext();
		//相对路径获取绝对路径,绝对路径是用来读和写用的
		String absPath = sc.getRealPath("/myImage");
		//相对路径
		String sPath = "/myImage";
		try {
			fileItems = upload.parseRequest(request);
			//处理上传的文件项
			Iterator i = fileItems.iterator();
			
			while(i.hasNext()) {
				FileItem fi = (FileItem)i.next();
				if(fi.isFormField()) {
					if(fi.getFieldName().equals("foodName")) {
						foodName = fi.getString("UTF-8");
					}
					if(fi.getFieldName().equals("price")) {
						price = fi.getString();
					}
					if(fi.getFieldName().equals("typeId")) {
						typeId = fi.getString();
					}
				}else {
					InputStream is = fi.getInputStream();
					String name = fi.getName();
					String destPath = absPath + "/" + name;
					sPath = sPath + name;
					FileOutputStream fis = new FileOutputStream(destPath);
					byte[] bys = new byte[1024];
					int n = -1;
					while((n=is.read(bys)) != -1) {
						fis.write(bys,0,n);
					}
					fis.close();
					is.close();
				}
			}
			mft.saveFood(foodName, typeId, price, sPath);
			//添加成功，跳转
			request.getRequestDispatcher("/FoodServlet").forward(request, response);
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
