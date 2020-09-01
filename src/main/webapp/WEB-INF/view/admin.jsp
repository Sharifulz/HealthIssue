<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page isELIgnored="false" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<title>Bootstrap Elegant Modal Login Modal Form with Icons</title>
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

<div class="container">
	<div class="row p-2">
		<div class="col-md-3"></div>
		<div class="col-md-6">
			<fieldset style="margin: 0 auto; text-align: center;">
			<span class="badge badge-danger" style="padding: 15px;">ADMIN PAGE</span> 
			<a href="/" class=" badge badge-warning" style="padding: 15px;">HOME</a>
			<a href="/user" class=" badge badge-warning" style="padding: 15px;">USER</a>
			<a href="/admin" class="badge badge-primary" style="padding: 15px;">ADMIN</a>
			<a href="/logout" class="badge badge-dark" style="padding: 15px;">LOGOUT</a>
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

<h3 style="color:blue;">Privilege 1 : Pending User</h3>
<table class="table">
  <thead>
    <tr>
      <th scope="col">Username</th>
      <th scope="col">Full Name</th>
      <th scope="col">Request Date</th>
      <th scope="col">Activate</th>
       <th scope="col">Delete</th>
    </tr>
  </thead>
  <tbody>
      <c:forEach items="${pendingUsers}" var="pendingUser">
               <tr>
                <th scope="row">${pendingUser.userName}</th>
                <td>${pendingUser.fullName}</td>
                <td>${pendingUser.signupDate}</td>
                <td><a href="/user/approve/${pendingUser.id}" class="badge badge-dark" style="padding: 15px;">Activate</a></td>
                <td><a href="/user/delete/${pendingUser.id}" class="badge badge-danger" style="padding: 15px;">Delete</a></td>
              </tr>
      </c:forEach>
  </tbody>
</table>

<h3 style="color:blue;">Privilege 1 : Approved User</h3>
<table class="table">
  <thead>
    <tr>
      <th scope="col">Username</th>
      <th scope="col">Full Name</th>
      <th scope="col">Request Date</th>
      <th scope="col">Activate</th>
       <th scope="col">Delete</th>
    </tr>
  </thead>
  <tbody>
      <c:forEach items="${approvedUsers}" var="approvedUser">
               <tr>
                <th scope="row">${approvedUser.userName}</th>
                <td>${approvedUser.fullName}</td>
                <td>${approvedUser.signupDate}</td>
                <td><a href="/user/block/${approvedUser.id}" class="badge badge-dark" style="padding: 15px;">Block</a></td>
                <td><a href="/user/delete/${approvedUser.id}" class="badge badge-danger" style="padding: 15px;">Delete</a></td>
              </tr>
      </c:forEach>
  </tbody>
</table>

<h3 style="color:blue;">Privilege 2 : Pending Posts</h3>
<table class="table">
  <thead>
    <tr>
      <th scope="col">Username</th>
      <th scope="col">Post Description</th>
      <th scope="col">Post Date</th>
      <th scope="col">Action</th>
    </tr>
  </thead>
  <tbody>    
      <c:forEach items="${pendingBlogs}" var="pendingBlog">
               <tr>
                <th scope="row">${pendingBlog.userName}</th>
                <td>${pendingBlog.description}</td>
                <td>${pendingBlog.postDate}</td>
                <td><a href="/blog/approve/${pendingBlog.id}" class="badge badge-dark" style="padding: 15px;">Approve</a></td>
              </tr>
      </c:forEach>
  </tbody>
</table>

<h3 style="color:blue;">Privilege 3 : Approved Posts</h3>
<table class="table">
  <thead>
    <tr>
      <th scope="col">Username</th>
      <th scope="col">Post Description</th>
      <th scope="col">Post Date</th>
      <th scope="col">Action</th>
    </tr>
  </thead>
  <tbody>    
      <c:forEach items="${approvedPosts}" var="approvedPost">
               <tr>
                <th scope="row">${approvedPost.userName}</th>
                <td>${approvedPost.description}</td>
                <td>${approvedPost.postDate}</td>
                <td><a href="/blog/remove/${approvedPost.id}" class="badge badge-danger" style="padding: 15px;">Remove</a></td>
              </tr>
      </c:forEach>
  </tbody>
</table>

<h3 style="color:blue;">Privilege 4 : Create Blog</h3>

<form action="/blog/create_by_admin" method="post">
  <div class="form-group">
    <label for="exampleInputEmail1">Status</label>
    <input type="text" class="form-control" placeholder="Username" name="description">
  </div>
  <button type="submit" class="btn btn-primary">Submit</button>
</form>


<h3 style="color:blue;">Privilege 4 : Create Admin</h3>

<form action="/admin/create" method="post">
  <div class="form-group">
    <label for="exampleInputEmail1">Username</label>
    <input type="text" class="form-control" placeholder="Username" name="userName">
  </div>
  <button type="submit" class="btn btn-primary">Submit</button>
</form>
    
</body>
</html>