<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>


<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Edit Store</title>
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
	<h2><u>Edit Store</u></h2>
		<form action="editstore?id=<c:out value="${store.getStoreId()}"/>"
			method="post">
			<div class="form-group row">
				<label for="storeid" class="col-sm-2 col-form-label"><b>Store
						ID</b></label>
				<div class="col-sm-3">
					<input type="text" readonly class="form-control-plaintext ml-3"
						id="storeid" name="storeid" value="${store.getStoreId()}">
				</div>
			</div>
			<div class="form-group row">
				<label for="storename" class="col-sm-2 col-form-label"><b>Store
						Name</b></label>
				<div class="col-sm-3">
					<input type="text" class="form-control"
						id="storename" name="storename" value="${store.getName()}" required>
				</div>
			</div>
			<div class="form-group row">
				<label for="street1" class="col-sm-2 col-form-label"><b>Street 1
				</b></label>
				<div class="col-sm-3">
					<input type="text" class="form-control"
						id="street1" name="street1" value="${store.getStreet1()}" required>
				</div>
			</div>
			<div class="form-group row">
				<label for="street2" class="col-sm-2 col-form-label"><b>Street 2</b></label>
				<div class="col-sm-3">
					<input type="text" class="form-control"
						id="street2" name="street2" value="${store.getStreet2()}" required>
				</div>
			</div>
			<div class="form-group row">
				<label for="city" class="col-sm-2 col-form-label"><b>City</b></label>
				<div class="col-sm-3">
					<input type="text" class="form-control"
						id="city" name="city" value="${store.getCity()}" required>
				</div>
			</div>
			<div class="form-group row">
				<label for="state" class="col-sm-2 col-form-label"><b>State</b></label>
				<div class="col-sm-3">
					<input type="text" class="form-control"
						id="state" name="state" value="${store.getState()}" required>
				</div>
			</div>
			<div class="form-group row">
				<label for="zip" class="col-sm-2 col-form-label"><b>Zip</b></label>
				<div class="col-sm-3">
					<input type="text" class="form-control"
						id="zip" name="zip" value="${store.getZip()}" required>
				</div>
			</div>
			<div class="form-group row ml-1">
			<div class="col-sm-2"></div>
			<div class="col-sm-5">
				<button type="submit" class="btn btn-primary">Update</button>
				<a class="ml-2" href="storedetails?id=<c:out value="${store.getStoreId()}"/>"><c:out
								value="Back" /></a>
			</div>
			<div class="col-sm-5"></div>
		</form>

		<br />
		<br />
		<p>
			<span id="successMessage"><b>${messages.success}</b></span>
		</p>
	</div>

</body>
</html>