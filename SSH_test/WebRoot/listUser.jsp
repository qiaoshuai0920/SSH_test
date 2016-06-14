<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/WEB-INF/Pagination.tld" prefix="p"%>
<%@ taglib uri="/WEB-INF/myTag.tld" prefix="m"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>

		<title>My JSP 'listUser.jsp' starting page</title>
		<script src="http://cdn.bootcss.com/jquery/1.11.3/jquery.min.js"></script>
		<script type="text/javascript">
	function addUser() {
		window.location.href="${pageContext.request.contextPath}/userAdd.jsp";
	}
	function delUser(id){
		window.location.href="${pageContext.request.contextPath}/user/delUser.action?user.userId="+id;
		}
</script>

	</head>

	<body>
		<table style="width: 50%;">
			<tr>
				<td>
					ID
				</td>
				<td>
					用户名
				</td>
				<td>
					密码
				</td>
				<td>
					操作
				</td>
			</tr>
			<s:iterator value="listUser" status="sta" var="va">
				<tr>
					<td>
						${va.userId}
					</td>
					<td>
						${va.userName}
					</td>
					<td>
						${va.userPass}
					</td>
					<td>
						<input type="button" value="删除" onclick="delUser('${va.userId}');" />
					</td>
				</tr>
			</s:iterator>
		</table>
		<input type="button" value="添加" onclick="addUser();" />
	</body>
</html>
