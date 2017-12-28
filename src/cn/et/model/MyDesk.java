package cn.et.model;

import java.util.List;
import java.util.Map;

public class MyDesk {
	/**
	 * ��װget����
	 * @return
	 * @throws Exception 
	 */
	public Integer getTableListCount(String name) throws Exception {
		if(name == null) {
			name = "";
		}
		String sql = "select count(rowid) as cr from DESK where dname like '%"+name+"%'";
		List<Map> result = DbUtils.query(sql);
		//�ܼ�¼��
		return Integer.parseInt(result.get(0).get("CR").toString());
	}
	/**
	 * �����ݷ�װ��һ��list����
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
		//��ӵ�orcale���
		String sql = "insert into desk values((select max(deskid)+1 from desk),'"+deskName+"',0,'')";
		DbUtils.execute(sql);
	}
	
	public void deleteDesk(String deskId) throws Exception {
		//ɾ����orcale���
		String sql = "delete from desk where deskId="+deskId;
		DbUtils.execute(sql);
	}
}
