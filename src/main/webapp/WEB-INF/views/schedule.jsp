<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html>
<head>
    <title>Schedule</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css" />
</head>
<body>
    <p>
        <a href="${pageContext.request.contextPath}/">Go to index</a>
        <c:if test="${!isAdmin}">
         or <a href="${pageContext.request.contextPath}/login">login</a>
        </c:if>
    </p>
    <p>${description}</p>
</body>
</html>
