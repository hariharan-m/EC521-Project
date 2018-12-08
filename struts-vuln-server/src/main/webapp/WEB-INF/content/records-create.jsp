<%--
  Created by IntelliJ IDEA.
  User: sahko
  Date: 12/4/2018
  Time: 1:11 AM
  To change this template use File | Settings | File Templates.
--%>
<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="s" uri="/struts-tags" %>

<html>
<head>
    <title>Upload complete</title>
</head>
<body>
    <h2>Successfully uploaded <s:property value="fileUploadName"/>!</h2>
    <a href="${pageContext.request.contextPath}/records/new">Create new order</a>
</body>
</html>
