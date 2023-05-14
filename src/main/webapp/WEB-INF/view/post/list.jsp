<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="../common/header.jsp" %>

<h3>Post List</h3>

<table class="table">
	<thead>
		<th>No.</th>
		<th>title</th>
		<th>Date</th>
	</thead>
	<tbody>
		<c:forEach var="post" items="${ list }">
			<c:if test="${ empty queryString }">
				<c:set var="qs" value="?id=${ post.id }" />
			</c:if>
			<c:if test="${ not empty queryString }">
				<c:set var="qs" value="${ queryString }&id=${ post.id }" />
			</c:if>
			<tr>
				<td><c:out value="${ post.id }" /></td>
				<td><a href="./view<c:out value="${ qs }" />"><c:out value="${ post.title }" /></a></td>
				<td><c:out value="${ post.written }" /></td>
			</tr>
		</c:forEach>
	</tbody>
</table>

<div>
	<a href="./write" class="btn">Write</a>
</div>

<%@ include file="../common/footer.jsp" %>