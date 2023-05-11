<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../common/header.jsp" %>

<form id="mypage-form" method="POST" action="./updateProc">
	<div class="form-group">
		<label for="password">Password</label>
		<input type="password" class="form-control" id="password" name="password" />
	</div>
	
	<div class="form-group">
		<label for="name">Name</label>
		<input type="name" class="form-control" id="name" name="name" value="<c:out value="${user.name }" />" />
	</div>
	
	<div class="form-group">
		<label for="email">Email</label>
		<input type="email" class="form-control" id="email" name="email" value="<c:out value="${user.email }" />" />
	</div>
	
	<div class="form-group mt-3">
		<button type="submit" class="btn btn-primary">Submit</button>
		<button type="button" class="btn btn-danger" id="unregister">Unregister</button>
	</div>
</form>

<form id="unregister-form" class="d-none" method="POST" action="./unregisterProc"></form>

<script>
$(document).ready(function(){
	$("#mypage-form").submit(function(){
		if($("#name").val() == ""){
			alert("Enter your name.");
			$("#name").focus();
			return false;
		}
		
		if($("#email").val() == ""){
			alert("Enter your email.");
			$("#email").focus();
			return false;
		}
	});
	
	$("#unregister").click(function(){
		if(confirm("Are you sure to unregister.")){
			$("#unregister-form").submit();
		}
	});
});
</script>

<%@ include file="../common/footer.jsp" %>