<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="../common/header.jsp" %>

<h3>View Post</h3>

<table class="table">
	<tbody>
	<tr>
		<th>Title</th>
		<td><c:out value="${ post.title }" /></td>
	</tr>
	<tr>
		<th>Content</th>
		<td><c:out value="${ post.content }" /></td>
	</tr>
	</tbody>
</table>

<div>
	<a href="./update<c:out value="${ queryString }" />" class="btn">Update</a>
	<a href="./list<c:out value="${ listQueryString }" />" class="btn">List</a>
</div>

<%@ include file="../common/footer.jsp" %>