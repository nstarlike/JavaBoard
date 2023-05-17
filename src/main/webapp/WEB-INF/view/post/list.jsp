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

<c:if test="${ pagination.totalPage > 0 }">
<nav>
	<ul class="pagination justify-content-center">
		<c:if test="${ pagination.startPage != 1 }">
		<li class="page-item"><a class="page-link" href="./list?pageNo=${ pagination.firstPage }">&laquo;</a></li>
		<li class="page-item"><a class="page-link" href="./list?pageNo=${ pagination.prevPage }">Prev</a></li>
		</c:if>
		
		<c:forEach var="pageNo" begin="${ pagination.startPage }" end="${ pagination.endPage }">
		<li class="page-item"><a class="page-link" href="./list?pageNo=${ pageNo }">${ pageNo }</a></li>
		</c:forEach>
		
		<c:if test="${ pagination.endPage < pagination.lastPage }">
		<li class="page-item"><a class="page-link" href="./list?pageNo=${ pagination.nextPage }">Next</a></li>
		<li class="page-item"><a class="page-link" href="./list?pageNo=${ pagination.lastPage }">&raquo;</a></li>
		</c:if>
	</ul>
</nav>
</c:if>

<div>
	<a href="./write" class="btn">Write</a>
</div>

<%@ include file="../common/footer.jsp" %>