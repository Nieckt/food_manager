package cn.et.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import oracle.net.aso.p;

public class MyFoodType {
	/**
	 * 封装get方法
	 * 
	 * @return
	 * @throws Exception
	 */
	public Integer getTableListCount(String name) throws Exception {
		if (name == null) {
			name = "";
		}
		String sql = "select count(rowid) as cr from FOODTYPE where typename like '%" + name + "%'";
		List<Map> result = DbUtils.query(sql);
		// 总记录数
		return Integer.parseInt(result.get(0).get("CR").toString());
	}
	static Properties p = new Properties();
	/**
	 * 获取连接
	 * 	通过路径url
	 * 	驱动程序
	 * 	数据库账号
	 * 	数据库密码
	 */
	public static Connection getConnection()throws Exception {
		String url = p.getProperty("url");
		String driverClass = p.getProperty("driverClass");
		String name = p.getProperty("username");
		String password = p.getProperty("password");
		
		Class.forName(driverClass);
		//获取数据的链接
		Connection conn = DriverManager.getConnection(url, name, password);
		return conn;
	}

	/**
	 * 把数据封装成一个list集合
	 * 
	 * @return
	 * @throws Exception
	 */
	public PageTools getTableListPage(String name, Integer curPage) throws Exception {
		if (name == null) {
			name = "";
		}
		Integer totalCount = getTableListCount(name);
		PageTools pt = new PageTools(curPage, totalCount, null);
		StringBuffer sb = new StringBuffer();
		String sql = "select * from (select t.*,rownum rn from FOODTYPE t where t.typename like '%" + name + "%')"
				+ "where rn>=" + pt.getStartIndex() + " and rn<=" + pt.getEndIndex();
		List<Map> result = DbUtils.query(sql);
		pt.setData(result);
		return pt;
	}
	
	// 传入sql语句
	public static int execute(String sql) throws Exception {
		// 连接数据库 
		Connection conn = getConnection();
		PreparedStatement pst = conn.prepareStatement(sql);
		int i = pst.executeUpdate();
		conn.close();
		pst.cancel();
		return i;
	}
	
	public void saveFood(String foodName,String typeid,String price, String imagePath) throws Exception {
		String sql = "insert into food values((select max(FOODID)+1 from food),'"+typeid+"','"+foodName+"','"+price+"','"+imagePath+"')";
		DbUtils.execute(sql);
	}
	
	public void UpdateFoodType(String typeid,String typename) throws Exception {
		String sql="update foodtype set typename='"+typename+"' where typeid='"+typeid+"'";
		DbUtils.execute(sql);
	}
	
	public void saveFoodType(String typeName) throws Exception {
		// 添加的orcale语句
		String sql = "insert into FOODTYPE values((select max(typeid)+1 from FOODTYPE),'" + typeName + "')";
		DbUtils.execute(sql);
	}

	public void deleteDesk(String typeId) throws Exception {
		// 删除的orcale语句
		String sql = "delete from FOODTYPE where TYPEID=" + typeId;
		DbUtils.execute(sql);
	}
	
	public List<Map> getAllFoodtype() throws Exception{
		String sql = "select * from FOODTYPE";
		return DbUtils.query(sql);
	}
}
