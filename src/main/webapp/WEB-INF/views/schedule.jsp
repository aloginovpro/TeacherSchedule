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
            <th width="50px"></th>
            <c:forEach var="day" items="${weekDays}">
                <th width="100px">${day}</th>
            </c:forEach>
        </tr>
        <c:forEach begin="0" end="${pairIntervals.size() - 1}" var="pair">
        <tr>
            <td bgcolor="#faebd7" align="center">${pairIntervals.get(pair)}</td>
        <c:forEach begin="0" end="${weekDays.size() - 1}" var="day">
            <c:set var="value" value="${schedule[pair][day]}"/>
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
    <input type="button" value="update" onclick="updateSchedule('${id}', '${pairIntervals.size()}', '${weekDays.size()}')">
    </c:if>

</body>
</html>
