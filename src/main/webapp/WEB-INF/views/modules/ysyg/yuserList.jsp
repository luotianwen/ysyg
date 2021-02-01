<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>用户管理管理</title>
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
		<li class="active"><a href="${ctx}/ysyg/yuser/">用户管理列表</a></li>
		<shiro:hasPermission name="ysyg:yuser:edit"><li><a href="${ctx}/ysyg/yuser/form">用户管理添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="yuser" action="${ctx}/ysyg/yuser/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>姓名：</label>
				<form:input path="name" htmlEscape="false" maxlength="255" class="input-medium"/>
			</li>
			<li><label>账号：</label>
				<form:input path="account" htmlEscape="false" maxlength="255" class="input-medium"/>
			</li>
			<li><label>创建日期：</label>
				<input name="beginCreateDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${yuser.beginCreateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/> - 
				<input name="endCreateDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${yuser.endCreateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</li>
			<li><label>昵称：</label>
				<form:input path="nk" htmlEscape="false" maxlength="255" class="input-medium"/>
			</li>
			<li><label>性别：</label>
				<form:select path="sex" class="input-medium">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('sex')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>姓名</th>
				<th>账号</th>
				<th>创建日期</th>
				<th>昵称</th>
				<th>性别</th>
				<th>出生日期</th>
				<th>头像</th>
				<th>邮箱</th>
				<th>备注</th>
				<shiro:hasPermission name="ysyg:yuser:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="yuser">
			<tr>
				<td><a href="${ctx}/ysyg/yuser/form?id=${yuser.id}">
					${yuser.name}
				</a></td>
				<td>
					${yuser.account}
				</td>
				<td>
					<fmt:formatDate value="${yuser.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${yuser.nk}
				</td>
				<td>
					${fns:getDictLabel(yuser.sex, 'sex', '')}
				</td>
				<td>
					<fmt:formatDate value="${yuser.br}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${yuser.pto}
				</td>
				<td>
					${yuser.email}
				</td>
				<td>
					${yuser.remarks}
				</td>
				<shiro:hasPermission name="ysyg:yuser:edit"><td>
    				<a href="${ctx}/ysyg/yuser/form?id=${yuser.id}">修改</a>
					<a href="${ctx}/ysyg/yuser/delete?id=${yuser.id}" onclick="return confirmx('确认要删除该用户管理吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>