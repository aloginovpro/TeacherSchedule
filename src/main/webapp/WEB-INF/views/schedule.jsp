<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html>
<head>
    <sec:csrfMetaTags />
    <title>Schedule</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css" />
    <script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.3.2/jquery.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/common.js"></script>
</head>
<body>

    <jsp:include page="template/links.jsp"/>

    <p>Teacher: ${teacherName}</p>

    <c:if test="${isAdmin}">
        <a href="${pageContext.request.contextPath}/admin/subjects/${id}">Edit subjects</a>
    </c:if>

    <table id="scheduleTable">
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
            <c:if test="${!isAdmin}">
            <td align="${value == null ? 'center' : 'left'}">
                ${value == null ? '-' : value}
            </td>
            </c:if>
            <c:if test="${isAdmin}">
            <td>
                <select>
                    <option value="-" ${value == null ? 'selected' : ''}>-</option>
                    <c:forEach var="subject" items="${subjects}">
                    <option value="${subject}" ${subject == value ? 'selected' : ''}>${subject}</option>
                    </c:forEach>
                </select>
            </td>
            </c:if>
        </c:forEach>
        </tr>
        </c:forEach>
    </table>

    <c:if test="${isAdmin}">
    <input type="button" value="update" onclick="updateSchedule('${id}')">
    </c:if>

</body>
</html>
