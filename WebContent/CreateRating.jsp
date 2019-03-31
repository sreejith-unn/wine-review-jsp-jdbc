<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>


<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Create Rating</title>
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
	<div class="fluid-container pt-4 mt-5">
		<form action="createrating?id=<c:out value="${tasterId}"/>" method="post">
			<div class="form-group row ml-1">
				<label for="tasterid" class="col-sm-2 col-form-label"><b>Taster
						ID</b></label>
				<div class="col-sm-5">
					<input type="text" readonly class="form-control-plaintext ml-3"
						id="tasterid" name="tasterid" value="${tasterId}">
				</div>
			</div>
			<div class="form-group row ml-1">
				<label for="wineid" class="col-sm-2 col-form-label"><b>Wine</b></label>
				<div class="col-sm-5">
					<select class="custom-select" id="wineid" name="wineid" value="">
						<c:forEach items="${wines}" var="wine">
							<option value="${wine.getWineId()}">
								<c:out value="${wine.getName()}" />
							</option>
						</c:forEach>
					</select>
				</div>
			</div>

			<div class="form-group row ml-1">
				<label for="rating" class="col-sm-2 col-form-label"><b>Rating</b></label>
				<div class="col-sm-5">
					<input class="form-control" id="rating"
						placeholder="Rating" name="rating" value="" required>
				</div>
			</div>
			<div class="form-group row ml-1">
			<div class="col-sm-2"></div>
			<div class="col-sm-5">
				<button type="submit" class="btn btn-primary">Create</button>
				<a class="ml-2" href="tasterdetails?id=<c:out value="${tasterId}"/>"><c:out
								value="Back" /></a>
			</div>
			<div class="col-sm-5"></div>
		</form>
			
				
			
			
			
				
		<br/><br/>
	<p>
		<span id="successMessage"><b>${messages.success}</b></span>
	</p>
	</div>
</body>
</html>