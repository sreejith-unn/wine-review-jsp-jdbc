<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">
		<link href="https://use.fontawesome.com/releases/v5.3.1/css/all.css" rel="stylesheet">
		<title>Create a Wine</title>
	</head>
	<body>
		<div class="container-fluid">
			<h1>Create Wine</h1>
			<div 
				class="alert alert-success col-sm-6" 
				role="alert" 
				<c:if test="${messages.action == 'init' || messages.action == 'error'}">style="display:none"</c:if>>
				${messages.info}
			</div>
			<div 
				class="alert alert-warning col-sm-6" 
				role="alert" 
				<c:if test="${messages.action == 'init' || messages.action == 'success'}">style="display:none"</c:if>>
				${messages.info}
			</div>
			<form action="winecreate" method="post">
				<div class="form-group row mt-3">
					<label for="nameFld" class="col-sm-2 col-form-label">Name</label>
					<div class="col-sm-4">
						<input class="form-control" id="nameFld" name="nameFld">
					</div>
				</div>
				<div class="form-group row">
					<label for="descriptionFld" class="col-sm-2 col-form-label">Description</label>
					<div class="col-sm-4">
						<input class="form-control" id="descriptionFld" name="descriptionFld">
					</div>
				</div>
				<div class="form-group row">
					<label for="priceFld" class="col-sm-2 col-form-label">Price</label>
					<div class="col-sm-4">
						<input class="form-control" id="priceFld" name="priceFld">
					</div>
				</div>
				<div class="form-group row">
					<label for="varietyFld" class="col-sm-2 col-form-label">Variety</label>
					<div class="col-sm-4">
						<input class="form-control" id="varietyFld" name="varietyFld">
					</div>
				</div>
				<div class="form-group row">
					<label for="vineyardFld" class="col-sm-2 col-form-label">Vineyard</label>
					<div class="col-sm-4">
						<input class="form-control" id="vineyardFld" name="vineyardFld">
					</div>
				</div>
				<div class="form-group row">
					<label for="wineryNameFld" class="col-sm-2 col-form-label">Name of Winery</label>
					<div class="col-sm-4">
						<input class="form-control" id="wineryNameFld" name="wineryNameFld">
					</div>
				</div>
				<div class="form-group row">
					<div class="col-sm-3">
						<input type="submit" value="Create" class="btn btn-success btn-block"/>
					</div>
					<div class="col-sm-3">
						<a role="button" class="btn btn-warning btn-block" href="/WineConnoisseur">Go Back</a>
					</div>
				</div>
			</form>
		</div>
	</body>
</html>