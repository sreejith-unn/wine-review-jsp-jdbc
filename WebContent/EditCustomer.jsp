<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>


<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Edit Customer</title>
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
	<div class="fluid-container pt-4 mt-5 ml-4">
		<h2>
			<u>Edit Customer</u>
		</h2>
		<form
			action="editcustomer?id=<c:out value="${customer.getUserId()}"/>"
			method="post">
			<div class="form-group row">
				<label for="customerid" class="col-sm-2 col-form-label"><b>Customer
						ID</b></label>
				<div class="col-sm-3">
					<input type="text" readonly class="form-control-plaintext ml-3"
						id="customerid" name="customerid" value="${customer.getUserId()}">
				</div>
			</div>
			<div class="form-group row">
				<label for="customername" class="col-sm-2 col-form-label"><b>Customer
						Name</b></label>
				<div class="col-sm-3">
					<input type="text" class="form-control-plaintext" id="customername"
						readonly name="customername" value="${customer.getUserName()}">
				</div>
			</div>
			<div class="form-group row">
				<label for="password" class="col-sm-2 col-form-label"><b>Password
				</b></label>
				<div class="col-sm-3">
					<input type="text" class="form-control" id="password"
						name="password" value="${customer.getPassword()}" required>
				</div>
			</div>
			<div class="form-group row">
				<label for="firstname" class="col-sm-2 col-form-label"><b>First
						Name</b></label>
				<div class="col-sm-3">
					<input type="text" class="form-control" id="firstname"
						name="firstname" value="${customer.getFirstName()}" required>
				</div>
			</div>
			<div class="form-group row">
				<label for="lastname" class="col-sm-2 col-form-label"><b>Last
						Name</b></label>
				<div class="col-sm-3">
					<input type="text" class="form-control" id="lastname"
						name="lastname" value="${customer.getLastName()}" required>
				</div>
			</div>
			<div class="form-group row">
				<label for="about" class="col-sm-2 col-form-label"><b>About</b></label>
				<div class="col-sm-3">
					<input type="text" class="form-control" id="about" name="about"
						value="${customer.getAbout()}" required>
				</div>
			</div>
			<div class="form-group row ml-1">
				<div class="col-sm-2"></div>
				<div class="col-sm-5">
					<button type="submit" class="btn btn-primary">Update</button>
					<a class="ml-2"
						href="customerdetails?id=<c:out value="${customer.getUserId()}"/>"><c:out
							value="Back" /></a>
				</div>
				<div class="col-sm-5"></div>
		</form>

		<br /> <br />
		<p>
			<span id="successMessage"><b>${messages.success}</b></span>
		</p>
	</div>

</body>
</html>