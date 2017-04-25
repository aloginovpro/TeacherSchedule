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

    <p>Teacher: ${teacherName}</p>

    <c:if test="${isAdmin}">
        <a href="${pageContext.request.contextPath}/admin/subjects/${id}">Edit subjects</a>
    </c:if>

    <table>
        <tr bgcolor="#faebd7">
            <th width="50px">start</th>
            <th width="100px">Mon</th>
            <th width="100px">Tue</th>
            <th width="100px">Wed</th>
            <th width="100px">Thu</th>
            <th width="100px">Fri</th>
            <th width="100px">Sat</th>
            <th width="100px">Sun</th>
        </tr>
        <c:forEach begin="0" end="23" var="hour">
        <tr>
            <td bgcolor="#faebd7" align="center"><c:if test="${hour < 10}">0</c:if>${hour}</td>
        <c:forEach begin="0" end="6" var="day">
            <c:set var="value" value="${schedule[hour][day]}"/>
            <td align="${value == null ? 'center' : 'left'}">
                ${value == null ? '-' : value}
            </td>
        </c:forEach>
        </tr>
        </c:forEach>
    </table>

</body>
</html>
