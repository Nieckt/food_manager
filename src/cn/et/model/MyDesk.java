package cn.et.model;

import java.util.List;
import java.util.Map;

public class MyDesk {
	/**
	 * 封装get方法
	 * @return
	 * @throws Exception 
	 */
	public Integer getTableListCount(String name) throws Exception {
		if(name == null) {
			name = "";
		}
		String sql = "select count(rowid) as cr from DESK where dname like '%"+name+"%'";
		List<Map> result = DbUtils.query(sql);
		//总记录数
		return Integer.parseInt(result.get(0).get("CR").toString());
	}
	/**
	 * 把数据封装成一个list集合
	 * @return
	 * @throws Exception
	 */
	public PageTools getTableListPage(String name, Integer curPage) throws Exception {
		if(name == null) {
			name = "";
		}
		Integer totalCount = getTableListCount(name);
		PageTools pt = new PageTools(curPage, totalCount, null);
		StringBuffer sb = new StringBuffer();
		String sql = "select * from (select t.*,rownum rn from DESK t where t.dname like '%"+name+"%')" + "where rn>="+pt.getStartIndex()+" and rn<="+pt.getEndIndex();
		List<Map> result = DbUtils.query(sql);
		pt.setData(result);
		return pt;
	}
	
	public void saveDesk(String deskName) throws Exception {
		//添加的orcale语句
		String sql = "insert into desk values((select max(deskid)+1 from desk),'"+deskName+"',0,'')";
		DbUtils.execute(sql);
	}
	
	public void deleteDesk(String deskId) throws Exception {
		//删除的orcale语句
		String sql = "delete from desk where deskId="+deskId;
		DbUtils.execute(sql);
	}
}
