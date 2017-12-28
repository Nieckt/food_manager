package cn.et.model;

import java.util.List;

public class PageTools {
	/**
	 * 构造参数
	 * @param curPage 页面传入的当前页
	 * @param totalCount 数据库查询的总记录数
	 * @param pageCount	每页显示的条数
	 */
	public PageTools(Integer curPage,Integer totalCount,Integer pageCount) {
		//当前页
		this.curPage=curPage;
		//System.out.println("当前页："+curPage);
		this.totalPage=totalPage;
		this.pageCount=pageCount==null?this.pageCount:pageCount;
		//System.out.println("当前显示的条数"+pageCount);
		//上一页
		this.prePage=(curPage==1?1:curPage-1);
		//最后页
		this.totalPage = (totalCount%this.pageCount==0?totalCount/this.pageCount:totalCount/this.pageCount+1);
		//下一页
		this.nextPage=(curPage==totalPage?totalPage:curPage+1);
		//System.out.println("最后一页："+totalPage);
		//开始索引
		this.startIndex=(curPage-1)*this.pageCount+1;
		//结束索引
		this.endIndex=curPage*this.pageCount;
	}
	/**
	 * 当前页（动态的 由页面传递的）
	 */
	private Integer curPage;
	/**
	 * 每页显示的条数
	 * 默认每页显示10条
	 */
	private Integer pageCount=10;
	/**
	 * 上一页
	 * prePage = (curPage==1?1:curPage-1);
	 * 
	 * 举例 
	 * 	上一页		当前页 		下一页
	 * 	  1		  1		 2
	 * 	  1		  2	     3
	 * 
	 */
	private Integer prePage;
	/**
	 * 下一页
	 * next：下一个
	 * 如果当前页不是最后一页，下一页就是当前页+1，如果是最后    
	 * nextPage = (curPage==totalPage?totalPage:curPage+1)
	 * 
	 */
	private Integer nextPage;
	/**
	 * 总页数
	 * total:全部的意思
	 * pageCount(每页显示的条数)	totalCount（总记录数）  totalPage（总页数）
	 * 			10				20				2
	 * 			10				21				3
	 * 
	 * totalPage = (totalCount%pageCount==0?totalCount/pageCount:totalCount/pageCount+1)
	 * 
	 */
	private Integer totalPage;
	/**
	 * 总记录数（从数据库查询得来的）
	 */
	private Integer totalCount;
	/**
	 * 开始索引
	 * curPage pageCount start-end
	 * 	  1		   10      1-10
	 *    2        10 	   11-21
	 *    				(curPage-1)*pageCount+1;
	 */
	private Integer startIndex;
	/**
	 * 结束索引
	 */
	private Integer endIndex;
	public Integer getStartIndex() {
		return startIndex;
	}
	public void setStartIndex(Integer startIndex) {
		this.startIndex = startIndex;
	}
	public Integer getEndIndex() {
		return endIndex;
	}
	public void setEndIndex(Integer endIndex) {
		this.endIndex = endIndex;
	}
	/**
	 * 存储最终查询的数据
	 */
	private List data;
	public Integer getCurPage() {
		return curPage;
	}
	public void setCurPage(Integer curPage) {
		this.curPage = curPage;
	}
	public Integer getPageCount() {
		return pageCount;
	}
	public void setPageCount(Integer pageCount) {
		this.pageCount = pageCount;
	}
	public Integer getPrePage() {
		return prePage;
	}
	public void setPrePage(Integer prePage) {
		this.prePage = prePage;
	}
	public Integer getNextPage() {
		return nextPage;
	}
	public void setNextPage(Integer nextPage) {
		this.nextPage = nextPage;
	}
	public Integer getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(Integer totalPage) {
		this.totalPage = totalPage;
	}
	public Integer getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(Integer totalCount) {
		this.totalCount = totalCount;
	}
	//暴露一个get set方法
	public List getData() {
		return data;
	}
	public void setData(List data) {
		this.data = data;
	}
	
	public static void main(String[] args) {
		/**
		 * 从数据库里取出数据
		 * 
		 */
		int curPage = 2;
		int totalCount = 26;
		int pageCount = 5;
		PageTools pt = new PageTools(curPage, totalCount, pageCount);
		//上一页
		System.out.println("上一页："+pt.getPrePage());
		//下一页
		System.out.println("下一页："+pt.getNextPage());
		//总页数
		System.out.println("总页数："+pt.getTotalPage());
		//开始索引
		System.out.println("开始索引："+pt.getStartIndex());
		//结束索引
		System.out.println("结束索引："+pt.getEndIndex());
		System.out.println(Integer.parseInt("3"));
	}
	
}
