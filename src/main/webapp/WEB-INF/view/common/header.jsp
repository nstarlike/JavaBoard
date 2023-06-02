<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<c:set var="requestURI" value="${ pageContext.request.requestURI }" />

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Community</title>
<link rel="stylesheet" href="<c:url value="/resources/css/bootstrap-5.3.0.min.css" />" />
<link rel="stylesheet" href="<c:url value="/resources/css/jquery-ui-1.13.2.min.css" />" />
<link rel="stylesheet" href="<c:url value="/resources/css/blog.css" />" />
<script src="<c:url value="/resources/js/jquery-3.6.4.min.js" />"></script>
<script src="<c:url value="/resources/js/jquery-ui-1.13.2.min.js" />"></script>
<script src="<c:url value="/resources/js/bootstrap-5.3.0.bundle.min.js" />"></script>
</head>
<body>
<div class="container">
	<header class="blog-header lh-1 py-3">
		<div
			class="row flex-nowrap justify-content-between align-items-center">
			<div class="col-4"></div>
			<div class="col-4 text-center">
				<a class="blog-header-logo text-body-emphasis" href="<c:url value="/" />">Community</a>
			</div>
			<div class="col-4 d-flex justify-content-end align-items-center">
				<a class="link-secondary" href="<c:url value="/post/list" />" aria-label="Search"> <svg 
						xmlns="http://www.w3.org/2000/svg" width="20" height="20"
						fill="none" stroke="currentColor" stroke-linecap="round"
						stroke-linejoin="round" stroke-width="2" class="mx-3" role="img"
						viewBox="0 0 24 24">
						<title>Search</title><circle cx="10.5" cy="10.5" r="7.5" />
						<path d="M21 21l-5.2-5.2" /></svg>
				</a> 
				<sec:authorize access="!isAuthenticated()">
					<a class="btn btn-sm btn-outline-secondary me-1" href="<c:url value="/user/register" />">Sign up</a>
					<a class="btn btn-sm btn-outline-secondary" href="<c:url value="/login" />">Sign in</a>
				</sec:authorize>
				<sec:authorize access="isAuthenticated()">
					<a class="btn btn-sm btn-outline-secondary me-1" href="<c:url value="/user/mypage" />">My Page</a>
					<a class="btn btn-sm btn-outline-secondary" href="<c:url value="/logout" />">Logout</a>
				</sec:authorize>
			</div>
		</div>
	</header>
	
	<nav class="navbar navbar-expand-lg bg-body-tertiary">
		<div class="container-fluid">
			<button class="navbar-toggler" type="button"
				data-bs-toggle="collapse" data-bs-target="#navbarNavDropdown"
				aria-controls="navbarNavDropdown" aria-expanded="false"
				aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>
			<div class="collapse navbar-collapse" id="navbarNavDropdown">
				<ul class="navbar-nav">
					<li class="nav-item dropdown">
						<a class="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown"  aria-expanded="false">Posts</a>
						<ul class="dropdown-menu">
							<li><a class="dropdown-item" href="<c:url value="/post/list" />">list</a></li>
						</ul>
					</li>
				</ul>
			</div>
		</div>
	</nav>
</div>

<main class="container">
	<c:if test="${ fn:contains(requestURI, 'home.jsp') }">
	<div class="p-4 p-md-5 mb-4 rounded text-bg-dark">
		<div class="col-md-6 px-0">
			<h1 class="display-4 fst-italic">Title of a latest post</h1>
			<p class="lead my-3">The post’s contents.</p>
			<p class="lead mb-0">
				<a href="<c:url value="post/list" />" class="text-white fw-bold">Continue reading...</a>
			</p>
		</div>
	</div>
	</c:if>
	
	<div class="row mt-3 mb-3">
		<c:if test="${ fn:contains(requestURI, '/home.jsp') }">
			<div>
		</c:if>
		
		<c:if test="${ not fn:contains(requestURI, '/home.jsp') }">
			<%@ include file="aside.jsp" %>
			
			<div class="col-md-8">
		</c:if>