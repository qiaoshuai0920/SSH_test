<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/WEB-INF/Pagination.tld" prefix="p"%>
<%@ taglib uri="/WEB-INF/myTag.tld" prefix="m"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>

		<title>My JSP 'userAdd.jsp' starting page</title>

		<script src="http://cdn.bootcss.com/jquery/1.11.3/jquery.min.js"></script>
		<script type="text/javascript">
	function addUser() {
		$("#UserForm").submit();
	}
</script>
	</head>

	<body>
		<form id="UserForm"
			action="${pageContext.request.contextPath}/user/addUser.action"
			method="post">
			名字：
			<input type="text" name="user.userName" /></br>
			密码：
			<input type="text" name="user.userPass" /></br>
			年龄：
			<input type="text" name="user.userAge" /></br>
			<input type="button" value="提交" onclick="addUser();" />
		</form>
	</body>
</html>
