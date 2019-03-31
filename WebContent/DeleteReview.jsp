<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Delete User</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">

<link href="https://use.fontawesome.com/releases/v5.3.1/css/all.css"
	rel="stylesheet">
</head>
<body>
	
	<div class="navbar fixed-top navbar-expand-lg navbar-dark bg-dark">
		<a class="navbar-brand nav-header" href="/WineConnoisseur/">Wine
			Connoisseur </a>

	</div>
	<div class="fluid-container pt-5 mt-4 ml-4">
	<h2>${messages.title}</h2>
		<form action="deletereview" method="post">
		
			<div class="row mt-4" <c:if test="${messages.disableSubmit}">style="display:none"</c:if>>
				<div class="col-2">
					<label for="reviewid"><b>Review ID</b></label>
				</div>
				<div class="col-4">
					<input class="form-control" id="reviewid" name="reviewid"
						value="${reviewId}">
				</div>
			</div>
			<div class="row">
			<div class="col-2"></div>
				<span id="submitButton"
					<c:if test="${messages.disableSubmit}">style="display:none"</c:if>>
					<button type="submit" class="btn btn-danger mt-4 ml-3">Delete</button>
				</span>
			</div>
		</form>
		<br /> <br />
	</div>
</body>
</html>