<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html>
<head>
    <title>Subjects</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css" />
</head>
<body>
    <jsp:include page="template/links.jsp"/>

    <p>Teacher: ${teacherName}</p>

    <p>Back to <a href="${pageContext.request.contextPath}/schedule/${id}">schedule</a></p>

    <form action="<c:url value='${pageContext.request.contextPath}/admin/subjects/${id}'/>" method='POST'>
        <p>Subjects:<br>
            <textarea name="subjects" cols="40" rows="5">${subjects}</textarea>
        </p>
        <input type="submit" value="update"/>
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
    </form>

</body>
</html>
