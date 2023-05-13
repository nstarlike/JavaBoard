<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="common/header.jsp" %>

<h3>Search ID</h3>

<form id="search-id-form" method="POST" action="./searchIdProc">
	<div class="form-group">
		<label for="name">Name</label>
		<input type="text" id="name" class="form-control" name="name" />
	</div>
	<div class="form-group">
		<label for="email">Email</label>
		<input type="email" id="email" class="form-control" name="email" />
	</div>
	<div class="form-group mt-3">
		<button class="btn btn-primary">Search ID</button>
	</div>
</form>

<script>
$(document).ready(function(){
	$("#search-id-form").submit(function(){
		if($("#name").val() == ""){
			alert("Enter your name.");
			$("#name").focus();
		}
		
		if($("#email").val() == ""){
			alert("Enter your email.");
			$("#email").focus();
		}
	});
});
</script>
<%@ include file="common/footer.jsp" %>