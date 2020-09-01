<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page isELIgnored="false" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    
    
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<title>Spring Secure Login</title>
<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto|Varela+Round">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>
<style>
body {
	font-family: 'Varela Round', sans-serif;
}
.modal-login {
	color: #636363;
	width: 350px;
}
.modal-login .modal-content {
	padding: 20px;
	border-radius: 5px;
	border: none;
}
.modal-login .modal-header {
	border-bottom: none;
	position: relative;
	justify-content: center;
}
.modal-login h4 {
	text-align: center;
	font-size: 26px;
}
.modal-login  .form-group {
	position: relative;
}
.modal-login i {
	position: absolute;
	left: 13px;
	top: 11px;
	font-size: 18px;
}
.modal-login .form-control {
	padding-left: 40px;
}
.modal-login .form-control:focus {
	border-color: #00ce81;
}
.modal-login .form-control, .modal-login .btn {
	min-height: 40px;
	border-radius: 3px; 
}
.modal-login .hint-text {
	text-align: center;
	padding-top: 10px;
}
.modal-login .close {
	position: absolute;
	top: -5px;
	right: -5px;
}
.modal-login .btn, .modal-login .btn:active {	
	border: none;
	background: #00ce81 !important;
	line-height: normal;
}
.modal-login .btn:hover, .modal-login .btn:focus {
	background: #00bf78 !important;
}
.modal-login .modal-footer {
	background: #ecf0f1;
	border-color: #dee4e7;
	text-align: center;
	margin: 0 -20px -20px;
	border-radius: 5px;
	font-size: 13px;
	justify-content: center;
}
.modal-login .modal-footer a {
	color: #999;
}
.trigger-btn {
	display: inline-block;
	margin: 100px auto;
}
</style>
</head>
<body>

<%-- <c:forEach items="${posts}" var="user">
	<h1> ${user.userName}</h1> 
	<c:forEach items="${user.postsList}" var="post">
		<h5>${post.description}</h5>
	</c:forEach>
	<h5></h5>
</c:forEach> --%>
<div class="container">
	<div class="row p-2">
		<div class="col-md-3"></div>
		<div class="col-md-6">
			<fieldset style="margin: 0 auto; text-align: center;">
			<span class="badge badge-danger" style="padding: 15px;">HOME PAGE</span> 
			<a href="/user" class="badge badge-primary" style="padding: 15px;">USER PAGE</a>
			<a href="/admin" class="badge badge-primary" style="padding: 15px;">ADMIN PAGE</a>
			<a href="#myModal" class="badge badge-primary" data-toggle="modal" style="padding: 15px;">SIGN IN</a>
		</fieldset>
		</div>
		<div class="col-md-3"></div>
	</div>
</div>

<c:forEach items="${messages}" var="message">
	<div class="alert alert-primary" role="alert">
  		Dear ${currentUser.fullName} , ${message}
	</div>
</c:forEach>

<c:forEach items="${errorMessage}" var="error">
	<div class="alert alert-danger" role="alert">
  		 ${error}
	</div>
</c:forEach>

<c:forEach items="${warningMessage}" var="warning">
	<div class="alert alert-warning" role="alert">
  		 ${warning}
	</div>
</c:forEach>

<div class="container">
	<c:forEach items="${approvedPosts}" var="post">
	<div class="row mb-2">
		<div class="col-md-3"></div>
		<div class="col-md-6">
		<div class="card" style="width: 30rem;">
		  <i class="fa fa-twitter" style="font-size: 5rem; text-align: center"></i>
		  <div class="card-body">
		    <span class="card-title" style="font-weight: bold;">User: ${post.userName}</span>
		    <p class="card-text" style="font-size: 25px;">Post: ${post.description}</p>
		    <c:forEach items="${post.commentsList}" var="comments">
				<p>Comments: ${comments.comments}</p>
			</c:forEach>
		    <div style="text-align: center; margin-bottom: 10px;">
		    	 <a href="#" class="btn btn-success"><i class="fa fa-thumbs-up" style="text-align: center">${post.likes}</i></a>
			    <a href="#" class="btn btn-danger"><i class="fa fa-thumbs-down" style="text-align: center">${post.dislikes}</i></a>
			    <a href="#" class="btn btn-warning"><i class="fa fa-comment" style="text-align: center">${post.comments}</i></a>
		    </div>
		  </div>
		</div>
		</div>
		<div class="col-md-3"></div>
	</div>
	</c:forEach>
</div>


<!--LOGIN Modal HTML -->
<div id="myModal" class="modal fade">
	<div class="modal-dialog modal-login">
		<div class="modal-content">
			<div class="modal-header">				
				<h4 class="modal-title">Member Login</h4>
				<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
			</div>
			<div class="modal-body">
				<form action="/login" method="post">
					<div class="form-group">
						<i class="fa fa-user"></i>
						<input type="text" class="form-control" placeholder="Username" required="required" name="username">
					</div>
					<div class="form-group">
						<i class="fa fa-lock"></i>
						<input type="password" class="form-control" placeholder="Password" required="required" name="password">					
					</div>
					<div class="form-group">
						<input type="submit" class="btn badge-primary btn-block btn-lg" value="Login">
					</div>
				</form>
			</div>
			<div class="modal-footer">
			<a href="#myModal2" data-toggle="modal" style="padding: 15px;" onClick="closeDialog()">Not a member?</a>
			</div>
		</div>
	</div>
</div>

<!--SIGN IN Modal HTML -->
<div id="myModal2" class="modal fade">
	<div class="modal-dialog modal-login">
		<div class="modal-content">
			<div class="modal-header">				
				<h4 class="modal-title">Register</h4>
				<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
			</div>
			<div class="modal-body">
				<form action="/user/add" method="post">
					<div class="form-group">
						<i class="fa fa-user"></i>
						<input type="text" class="form-control" placeholder="Username" name="userName" required="required" >
					</div>
					<div class="form-group">
						<i class="fa fa-user"></i>
						<input type="text" class="form-control" placeholder="Full Name" name="fullName" required="required" >
					</div>
					<div class="form-group">
						<i class="fa fa-lock"></i>
						<input type="password" class="form-control" placeholder="Password" name="password" required="required" pattern="{10,12}" required title="10 to 12 characters">					
					</div>
					<div class="form-group">
						<input type="submit" class="btn badge-primary btn-block btn-lg" value="SUBMIT">
					</div>
				</form>
			</div>
		</div>
	</div>
</div>

<script>
var x = document.getElementById("myModal"); 
function closeDialog() { 
	x.style.display = "none";
} 
</script>


     
</body>
</html>