<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<html>
<head>
    <sec:csrfMetaTags />
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css" />
    <script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.3.2/jquery.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/common.js"></script>
    <title>${title}</title>
</head>
<body>

    <jsp:include page="template/links.jsp"/>
    <c:if test="${description != null}">
        <table>
        <c:forEach var="entry" items="${description}">
            <tr>
                <td width="100px"><b>${entry.key}</b></td>
                <td width="100px">${entry.value}</td>
            </tr>
        </c:forEach>
        </table>
    </c:if>

    <c:if test="${items != null}">
    <p><input type="text" id="filterInput" onkeyup="filterFunction()" placeholder="${prefix} filter" title="Filter" size="49"></p>
    <table id="itemsTable">
        <tr bgcolor="#faebd7">
            <th width="300px">${prefix}</th>
            <th width="30px">view</th>
            <c:if test="${isAdmin}">
            <th width="30px">id</th>
            <th width="50px">
            </c:if>
            </th>
        </tr>
        <c:forEach var="entry" items="${items}">
            <tr>
                <td>${entry.value}</td>
                <td><a href="${pageContext.request.contextPath}/${prefix}/${entry.key}">view</a></td>
                <c:if test="${isAdmin}">
                <td>${entry.key}</td>
                <td><input type="button" value="delete" onclick="deleteRow(this, '${prefix}')"></td>
                </c:if>
            </tr>
        </c:forEach>
    </table>
    <br>
    </c:if>



<c:if test="${isAdmin && htmlInput != null}">
    <p>Create new ${prefix}:</p>
    <table id="newInstanceTable">
    <c:forEach var="entry" items="${htmlInput}">
    <tr>
        <td width="100px">${entry.key}</td>
        <td width="100px"><input type="${entry.value}" name="${entry.key}"></td>
    </tr>
    </c:forEach>
    </table>
    <input type="button" value="create" onclick="create('${prefix}', ${categoryId})">
</c:if>

<c:if test="${isCategoryTeacher}">
    <p>View schedule: <a href="${pageContext.request.contextPath}/schedule/${categoryId}">view</a></p>
</c:if>

</body>
</html>
