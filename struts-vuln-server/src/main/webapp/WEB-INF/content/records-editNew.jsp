<%@ taglib prefix="s" uri="/struts-tags" %>
<%--
  Created by IntelliJ IDEA.
  User: sahko
  Date: 12/5/2018
  Time: 1:06 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Records: Edit New</title>
</head>
<body>
    <h2>Create new record</h2>
    <s:form action="upload" enctype="multipart/form-data" method="post">
        <label for="fileUpload">Select a file to upload</label>
        <s:file name="fileUpload" size="30" accept=".xml" />
        <br />
        <s:submit value="submit" />
    </s:form>
</body>
</html>
