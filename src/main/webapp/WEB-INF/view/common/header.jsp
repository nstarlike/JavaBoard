<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Community Website</title>
<link rel="stylesheet" href="<c:url value="/resources/css/bootstrap-5.3.0.min.css" />" />
<link rel="stylesheet" href="<c:url value="/resources/css/jquery-ui-1.13.2.min.css" />" />
<link rel="stylesheet" href="<c:url value="/resources/css/blog.css" />" />
<script src="<c:url value="/resources/js/jquery-3.6.4.min.js" />"></script>
<script src="<c:url value="/resources/js/jquery-ui-1.13.2.min.js" />"></script>
<script src="<c:url value="/resources/js/bootstrap-5.3.0.min.js" />"></script>
</head>
<body>
<div class="container">
	<header class="blog-header lh-1 py-3">
		<div
			class="row flex-nowrap justify-content-between align-items-center">
			<div class="col-4"></div>
			<div class="col-4 text-center">
				<a class="blog-header-logo text-body-emphasis" href="<c:url value="/" />">Large</a>
			</div>
			<div class="col-4 d-flex justify-content-end align-items-center">
				<a class="link-secondary" href="<c:url value="/post/list" />" aria-label="Search"> <svg 
						xmlns="http://www.w3.org/2000/svg" width="20" height="20"
						fill="none" stroke="currentColor" stroke-linecap="round"
						stroke-linejoin="round" stroke-width="2" class="mx-3" role="img"
						viewBox="0 0 24 24">
						<title>Search</title><circle cx="10.5" cy="10.5" r="7.5" />
						<path d="M21 21l-5.2-5.2" /></svg>
				</a> <a class="btn btn-sm btn-outline-secondary" href="<c:url value="/user/register" />">Sign up</a>
			</div>
		</div>
	</header>
</div>

<main class="container">
	<div class="p-4 p-md-5 mb-4 rounded text-bg-dark">
		<div class="col-md-6 px-0">
			<h1 class="display-4 fst-italic">Title of a longer featured blog
				post</h1>
			<p class="lead my-3">Multiple lines of text that form the lede,
				informing new readers quickly and efficiently about what’s most
				interesting in this post’s contents.</p>
			<p class="lead mb-0">
				<a href="#" class="text-white fw-bold">Continue reading...</a>
			</p>
		</div>
	</div>
	
	<div class="row g-5">
		<%@ include file="aside.jsp" %>
	
		<div class="col-md-8">