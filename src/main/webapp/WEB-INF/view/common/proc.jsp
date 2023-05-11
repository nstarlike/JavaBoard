<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Processing...</title>
<script>
<c:if test="${alert != null}">
alert("<c:out value="${alert}" />");
</c:if>

<c:if test="${replace != null}">
location.replace("<c:url value="${replace}" />");
</c:if>
</script>
</head>
<body>

</body>
</html>