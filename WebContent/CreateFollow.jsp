<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Create Follow</title>
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
		<form action="createfollow?id=<c:out value="${customerId}"/>" method="post">
			<div class="form-group row ml-1">
				<label for="customerid" class="col-sm-2 col-form-label"><b>Customer
						ID</b></label>
				<div class="col-sm-5">
					<input type="text" readonly class="form-control-plaintext ml-3"
						id="customerid" name="customerid" value="${customerId}">
				</div>
			</div>
			<div class="form-group row ml-1">
				<label for="tasterid" class="col-sm-2 col-form-label"><b>Taster</b></label>
				<div class="col-sm-5">
					<select class="custom-select" id="tasterid" name="tasterid" value="">
						<c:forEach items="${tasters}" var="taster">
							<option value="${taster.getUserId()}">
								<c:out value="${taster.getUserName()}" />
							</option>
						</c:forEach>
					</select>
				</div>
			</div>

			
			<div class="form-group row ml-1">
			<div class="col-sm-2"></div>
			<div class="col-sm-5">
				<button type="submit" class="btn btn-primary">Create</button>
				<a class="ml-2" href="customerdetails?id=<c:out value="${customerId}"/>"><c:out
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