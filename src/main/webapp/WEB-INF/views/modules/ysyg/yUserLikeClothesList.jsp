<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>我的搭配管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			
		});
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
        	return false;
        }
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/ysyg/yUserLikeClothes/">我的搭配列表</a></li>
	<%--	<shiro:hasPermission name="ysyg:yUserLikeClothes:edit"><li><a href="${ctx}/ysyg/yUserLikeClothes/form">我的搭配添加</a></li></shiro:hasPermission>
--%>	</ul>
	<form:form id="searchForm" modelAttribute="yUserLikeClothes" action="${ctx}/ysyg/yUserLikeClothes/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>创建时间：</label>
				<input name="createDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${yUserLikeClothes.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>用户</th>
				<th>衣服</th>
				<th>天气</th>
				<th>创建时间</th>
				<shiro:hasPermission name="ysyg:yUserLikeClothes:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="yUserLikeClothes">
			<tr>
				<td> 	${yUserLikeClothes.user.name}
				 </td>
				<td>
					${yUserLikeClothes.cid.name}
				</td>
				<td>
						${yUserLikeClothes.note}
				</td>
				<td>
					<fmt:formatDate value="${yUserLikeClothes.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>

				<td> 	<a href="${ctx}/ysyg/yUserLikeClothes/delete?id=${yUserLikeClothes.id}" onclick="return confirmx('确认要删除该我的搭配吗？', this.href)">删除</a>
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>