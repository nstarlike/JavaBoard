<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="../common/header.jsp" %>

<h3>Update Post</h3>

<form id="update-form" method="POST" action="./updateProc">
	<input type="hidden" name="id" value="<c:out value="${ post.id }" />" />
	<div class="form-group">
		<label for="title">Title</label>
		<input type="text" id="title" class="form-control" name="title" value="<c:out value="${ post.title }" />"/>
	</div>
	<div class="form-group">
		<label for="content">Content</label>
		<textarea id="content" class="form-control" name="content"><c:out value="${ post.content }" /></textarea>
	</div>
	<div class="mt-3">
		<button type="submit" class="btn btn-primary">Submit</button>
		<a href="./view<c:out value="${ queryString }" />" class="btn">View</a>
	</div>
</form>

<%@ include file="../common/footer.jsp" %>