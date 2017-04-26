<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<p>
    <a href="${pageContext.request.contextPath}/">Go to home page</a>
    <c:if test="${!isAuthorized}">
        or <a href="${pageContext.request.contextPath}/login">login</a>
    </c:if>
</p>