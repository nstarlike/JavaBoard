<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="../common/header.jsp" %>

<h3>Update Post</h3>

<form id="update-form" method="POST" action="./updateProc" enctype="multipart/form-data">
	<input type="hidden" name="queryString" value="<c:out value="${ queryString }" />" />
	<input type="hidden" name="id" value="<c:out value="${ post.id }" />" />
	<input type="hidden" id="deleted-attachment-ids" name="deletedAttachmentIds" />
	<div class="form-group">
		<label for="title">Title</label>
		<input type="text" id="title" class="form-control" name="title" value="<c:out value="${ post.title }" />"/>
	</div>
	<div class="form-group">
		<label for="content">Content</label>
		<textarea id="content" class="form-control" name="content">${ post.content }</textarea>
	</div>
	<div class="form-group">
		<label for="attachment">Attachment</label>
		<div id="file-div">
		<c:if test="${ attachmentList != null }">
		<c:forEach var="attachment" items="${ attachmentList }">
			<div>
				<c:out value="${ attachment.filename }" />
				<button type="button" class="delete-file-btn btn btn-sm" data-id="${ attachment.id }">Delete</button>
			</div>
		</c:forEach>
		</c:if>
		</div>
		<div><button type="button" id="add-file-btn" class="btn btn-sm">Add</button></div>
	</div>
	<div class="mt-3">
		<button type="submit" class="btn btn-primary">Submit</button>
		<a href="./view<c:out value="${ queryString }" />" class="btn">View</a>
	</div>
</form>

<style>
.ck.ck-content {
	font-size: 1em;
	line-height: 1.6em;
	margin-bottom: 0.8em;
	min-height: 200px;
	padding: 1.5em 2em;
}
</style>
<script src="<c:url value="../resources/js/ckeditor5.js" />"></script>
<script>
$(document).ready(function(){
	ClassicEditor
	.create(document.querySelector("#content"))
	.catch(error => {console.log(error);});
	
	$(".delete-file-btn").parent().each(function(){
		deleteEvent($(this));
	});
	
	$("#add-file-btn").click(function(){
		var file = '<div>'
		+ '<input type="file" name="files" />'
		+ '<button type="button" class="delete-file-btn btn btn-sm">Delete</button>';
		+ '</div>';
		var $file = $(file);
		deleteEvent($file);
		$("#file-div").append($file);
		
	});
});

function deleteEvent(elem){
	elem.find(".delete-file-btn").click(function(){
		$(this).parent().remove();
		
		var id = $(this).data("id");
		if(id != ""){
			var ids = $("#deleted-attachment-ids").val();
			if(ids == ""){
				ids = id;
			}else{
				ids = ids + "," + id;
			}
			$("#deleted-attachment-ids").val(ids)
		}
	});
}
</script>

<%@ include file="../common/footer.jsp" %>