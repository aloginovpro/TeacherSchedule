<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<p>
    <a href="${pageContext.request.contextPath}/">Go to home page</a> or
    <c:if test="${!isAuthorized}">
        <a href="${pageContext.request.contextPath}/login">login</a>
    </c:if>
    <c:if test="${isAuthorized}">
        <a href="${pageContext.request.contextPath}/login?logout">logout</a>
    </c:if>
</p>