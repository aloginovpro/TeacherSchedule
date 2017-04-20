<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <title>${title}</title>
</head>
<body>
<table>
    <c:forEach var="entry" items="${items}">
        <tr>
            <td>${entry.value}</td>
            <td><a href="${pageContext.request.contextPath}/${prefix}/${entry.key}">Link</a></td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
