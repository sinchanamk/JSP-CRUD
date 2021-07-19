<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>

<head>
<title>student Management Application</title>
<link rel="stylesheet"href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
</head>

<body>

	<header>
		<nav class="navbar navbar-dark bg-primary"> 
        <a class="navbar-brand" href="#"> 
			<div>
				<a href="https://www.javaguides.net" class="navbar-brand"> student
					Management App </a>
			</div>

			<ul class="navbar-nav">
				<li><a href="<%=request.getContextPath()%>/list"class="nav-link">students</a></li>
			</ul>
		</nav>
	</header>
	<br>

	<div class="row">
		
		<div class="container">
			<h3 class="text-center">List of students</h3>
			<hr>
			<div class="container text-left">

				<a href="<%=request.getContextPath()%>/new" class="btn btn-success">Add
					New student</a>
			</div>
			<br>
			<table class="table table-bordered">
				<thead>
					<tr>
						<th>USN</th>
						<th>Name</th>
						<th>branch</th>
						<th>college</th>
						<th>Actions</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="student" items="${liststudent}">

						<tr>
							<td><c:out value="${student.usn}" /></td>
							<td><c:out value="${student.name}" /></td>
							<td><c:out value="${student.branch}" /></td>
							<td><c:out value="${student.college}" /></td>
							<td><a href="edit?id=<c:out value='${student.usn}' />">Edit</a>
								&nbsp;&nbsp;&nbsp;&nbsp; <a
								href="delete?id=<c:out value='${student.usn}' />">Delete</a></td>
						</tr>
					</c:forEach>
					<!-- } -->
				</tbody>

			</table>
		</div>
	</div>
</body>

</html>