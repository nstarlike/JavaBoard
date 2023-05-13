<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="common/header.jsp" %>
<h3>Login</h3>

<form id="login-form" method="POST" action="./login">
	<div class="form-group">
		<label for="login-id">Login ID</label>
		<input type="text" id="login-id" class="form-control" name="loginId" />
	</div>
	<div class="form-group">
		<label for="password">Password</label>
		<input type="password" id="password" class="form-control" name="password" />
	</div>
	<div class="form-check">
		<label for="remember-me">Remember me</label>
		<input type="checkbox" id="remember-me" class="form-check-input" name="rememeberMe" />
	</div>
	<div class="form-group mt-3">
		<button type="submit" class="btn btn-primary">Login</button>
	</div>
	<div class="form-group mt-3">
		<a href="<c:url value="/searchId" />" class="btn">Search ID</a>
		<a href="<c:url value="/resetPassword" />" class="btn">Reset Password</a>
	</div>
</form>

<%@ include file="common/footer.jsp" %>