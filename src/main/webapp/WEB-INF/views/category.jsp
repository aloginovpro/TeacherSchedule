<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<html>
<head>
    <sec:csrfMetaTags />
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css" />
    <script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.3.2/jquery.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/category.js"></script>
    <title>${title}</title>
</head>
<body>

    <p>
        <a href="${pageContext.request.contextPath}/">Go to index</a>
        <c:if test="${!isAdmin}">
         or <a href="${pageContext.request.contextPath}/login">login</a>
        </c:if>
    </p>
    <p>${description}</p>

    <table id="itemsTable">
        <tr>
            <th width="300px"><input type="text" id="filterInput" onkeyup="filterFunction()" placeholder="Name" title="Filter"></th>
            <th width="30px">View</th>
            <c:if test="${isAdmin}">
            <th width="30px">id</th>
            <th width="50px">
            </c:if>
            </th>
        </tr>
        <c:forEach var="entry" items="${items}">
            <tr>
                <td>${entry.value}</td>
                <td><a href="${pageContext.request.contextPath}/${prefix}/${entry.key}">View</a></td>
                <c:if test="${isAdmin}">
                <td>${entry.key}</td>
                <td><input type="button" value="delete" onclick="deleteRow(this, '${prefix}')"></td>
                </c:if>
            </tr>
        </c:forEach>
    </table>
    <br>



<c:if test="${isAdmin}">
    <p>Create new ${prefix}:</p>
    <table id="newInstanceTable">
    <c:forEach var="entry" items="${htmlInput}">
    <tr>
        <td width="100px">${entry.key}</td>
        <td width="300px"><input type="${entry.value}" name="${entry.key}"></td>
    </tr>
    </c:forEach>
    </table>
    <input type="button" value="create" onclick="create('${prefix}', ${categoryId})">
</c:if>

</body>
</html>
