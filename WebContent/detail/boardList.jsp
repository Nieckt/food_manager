<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/loose.dtd">
<html>
<head>
 	<!-- 包含公共的JSP代码片段 -->
<title>无线点餐平台</title>


<!-- 修改路径 -->
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<script type="text/javascript" src="${pageContext.request.contextPath}/detail/style/js/jquery.js"></script>
<script type="text/javascript" src="style/js/page_common.js"></script>
<link href="${pageContext.request.contextPath }/detail/style/css/common_style_blue.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/detail/style/css/index_1.css" />
<script type="text/javascript">
	function confirmDelete(did) {
		//点是就是true 点否就是fale
		if(confirm("是否删除当前数据")) {
			//调到后台去
			//这里不需要../ 因为直接跳到当前路径
			window.location="deleteDesk?did="+did;
		}
	}
</script>
</head>
<body>
<!-- 页面标题 -->

<div id="TitleArea">
	<div id="TitleArea_Head"></div>
	<div id="TitleArea_Title">
		<div id="TitleArea_Title_Content">
			<img border="0" width="13" height="13" src="style/images/title_arrow.gif"/> 餐桌列表
		</div>
    </div>
	<div id="TitleArea_End"></div>
</div>

<!-- 过滤条件 -->
<div id="QueryArea">
	<!-- ${pageContext.request.contextPath}上下文路径 跳转到listDesk（跳去查询的servlet） -->
	<form action="${pageContext.request.contextPath}/listDesk" method="get">
		<input type="hidden" name="method" value="search">
		<input type="text" name="dname" title="请输入餐桌名称">
		<input type="submit" value="搜索">
	</form>
</div>
<!-- 主内容区域（数据列表或表单显示） -->
<div id="MainArea">
    <table class="MainArea_Content" cellspacing="0" cellpadding="0">
        <!-- 表头-->
        <thead>
            <tr align="center" valign="middle" id="TableTitle">
				<td>编号</td>
				<td>桌名</td>
				<td>状态</td>
				<td>预定时间</td>
				<td>操作</td>
			</tr>
		</thead>	
		<!--显示数据列表 -->
        <tbody id="TableData">
        	<!-- 用循环从数据库中查找数据  用foreach遍历查到每个数据的行（这里不懂要经常看） -->
			<c:forEach var="tmp" items="${requestScope.tableList.data}">
			<tr class="TableDetail1">
				<td align="center">${pageScope.tmp.DESKID }</td>
				<td align="center"> ${pageScope.tmp.DNAME }</td>
				<td align="center">
					${pageScope.tmp.DSTATE==0?'空闲':'预定' }
				</td>
				<td align="center">${pageScope.tmp.DTIME }</td>
				<td>
					<a href="/wirelessplatform/board.html?method=update&id=1&isBook=0" class="FunctionButton">退桌</a>				
					<a href="javascript:confirmDelete(${pageScope.tmp.DESKID})" class="FunctionButton">删除</a>				
				</td>
			</tr>
			</c:forEach>
			<tr><td colspan="3">
				<a href="${pageContext.request.contextPath}/listDesk?curPage=1 ">首页</a>
				<a href="${pageContext.request.contextPath}/listDesk?curPage=${requestScope.tableList.prePage} ">上一页</a>
			<c:forEach var="i" begin="1" end="${requestScope.tableList.totalPage}" step="1">
				<a href="${pageContext.request.contextPath }/listDesk?curPage=${pageScope.i}">${pageScope.i}</a>
			</c:forEach>
				<a href="${pageContext.request.contextPath}/listDesk?curPage=${requestScope.tableList.nextPage} ">下一页</a>
				<a href="${pageContext.request.contextPath}/listDesk?curPage=${requestScope.tableList.totalPage} ">尾页</a>
			</td></tr>
		</tbody>
	</table>
   <!-- 其他功能超链接 -->
	<div id="TableTail" align="center">
		<div class="FunctionButton"><a href="${pageContext.request.contextPath }/detail/saveBoard.html">添加</a></div>
    </div> 
</div>
</body>
</html>
