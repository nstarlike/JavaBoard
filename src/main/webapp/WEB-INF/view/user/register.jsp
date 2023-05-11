<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../common/header.jsp" %>
<h3>Sign up</h3>

<form id="register-form" method="POST" action="./registerProc">
	<div class="form-group">
		<label for="loing-id">Login ID</label>
		<input type="text" class="form-control" id="login-id" name="loginId" />
	</div>
	
	<div class="form-group">
		<label for="password">Password</label>
		<input type="password" class="form-control" id="password" name="password" />
	</div>
	
	<div class="form-group">
		<label for="name">Name</label>
		<input type="name" class="form-control" id="name" name="name" />
	</div>
	
	<div class="form-group">
		<label for="email">Email</label>
		<input type="email" class="form-control" id="email" name="email" />
	</div>
	
	<div class="form-group mt-3">
		<button type="submit" class="btn btn-primary">Submit</button>
	</div>
</form>

<script>
$(document).ready(function(){
	$("#register-form").submit(function(){
		if($("#login-id").val() == ""){
			alert("Enter login ID.");
			$("#login-id").focus();
			return false;
		}
		
		if($("#password").val() == ""){
			alert("Enter password.");
			$("#password").focus();
			return false;
		}
		
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
});
</script>

<%@ include file="../common/footer.jsp" %>