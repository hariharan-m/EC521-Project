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
    <title>Index</title>
</head>
<body>
    <h2>Welcome to the internal customer registry</h2>
    <s:iterator value="allCustomers" var="cust">
        <ul>
            <li><a href="${pageContext.request.contextPath}/records/${cust}">See orders for ${cust}</a></li>
        </ul>
    </s:iterator>
    <a href="${pageContext.request.contextPath}/records/create">Create new order</a>
</body>
</html>
