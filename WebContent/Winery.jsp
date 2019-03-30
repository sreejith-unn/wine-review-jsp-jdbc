<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">
<!-- <link href="course-list.style.client.css" rel="stylesheet" /> -->
<link href="https://use.fontawesome.com/releases/v5.3.1/css/all.css"
	rel="stylesheet">
</head>
<body>
	<div class="navbar fixed-top navbar-expand-lg navbar-dark bg-dark">
		<a class="navbar-brand nav-header"
			href="course-list.template.client.html">Wine Connoisseur </a>

	</div>
	<div class="container-fluid mt-5 pt-3">
		<h2>Winery Information</h2>
		<form>
			<div class="form-group row">
				<label for="wineryname" class="col-sm-2 col-form-label">Name</label>
				<div class="col-sm-3">
					<input type="text" readonly class="form-control-plaintext"
						id="wineryname" value="${winery.getWineryName()}">
				</div>
			</div>
			<div class="form-group row">
				<label for="region" class="col-sm-2 col-form-label">Region</label>
				<div class="col-sm-3">
					<input type="text" readonly class="form-control-plaintext"
						id="region" value="${winery.getRegion()}">
				</div>
			</div>
			<div class="form-group row">
				<label for="province" class="col-sm-2 col-form-label">Province</label>
				<div class="col-sm-3">
					<input type="text" readonly class="form-control-plaintext"
						id="province" value="${winery.getProvince()}">
				</div>
			</div>
			<div class="form-group row">
				<label for="country" class="col-sm-2 col-form-label">Country</label>
				<div class="col-sm-3">
					<input type="text" readonly class="form-control-plaintext"
						id="country" value="${winery.getCountry()}">
				</div>
			</div>
			
		</form>
		<h2>Wine Produced</h2>
		<table border="1">
			<tr>
				<th>ID</th>
				<th>Name</th>
				<th>Description</th>
				<th>Price</th>
				<th>Variety</th>
				<th>Vineyard</th>
				

			</tr>
			<c:forEach items="${wines}" var="wine">
				<tr>
					<td><c:out value="${wine.getWineId()}" /></td>
					<td><a href="wines?id=<c:out value="${wine.getWineId()}"/>"><c:out value="${wine.getName()}" /></a></td>
					<td><c:out value="${wine.getDescription()}" /></td>
					<td><c:out value="${wine.getPrice()}" /></td>
					<td><c:out value="${wine.getVariety()}" /></td>
					<td><c:out value="${wine.getVineyard()}" /></td>
				
				</tr>
			</c:forEach>
		</table>
	</div>
</body>
</html>