<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Wine Details</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">

<link href="https://use.fontawesome.com/releases/v5.3.1/css/all.css"
	rel="stylesheet">
</head>
<body>
<div class="navbar fixed-top navbar-expand-lg navbar-dark bg-dark">
		<a class="navbar-brand nav-header"
			href="/WineConnoisseur/">Wine Connoisseur </a>

	</div>
	<div class="container-fluid mt-5 pt-3">
		<h2>Wine Information</h2>
		<form>
			<div class="form-group row">
				<label for="winename" class="col-sm-2 col-form-label"><b>Name</b></label>
				<div class="col-sm-3">
					<input type="text" readonly class="form-control-plaintext"
						id="winename" value="${wine.getName()}">
				</div>
			</div>
			<div class="form-group row">
				<label for="description" class="col-sm-2 col-form-label"><b>Description</b></label>
				<div class="col-sm-3">
					<textarea cols="50" rows="5" style="border: none" type="text" readonly class="disabled"
						id="description">${wine.getDescription()}</textarea>
				</div>
			</div>
			<div class="form-group row">
				<label for="price" class="col-sm-2 col-form-label"><b>Price</b></label>
				<div class="col-sm-3">
					<input type="text" readonly class="form-control-plaintext"
						id="price" value="${wine.getPrice()}">
				</div>
			</div>
			<div class="form-group row">
				<label for="variety" class="col-sm-2 col-form-label"><b>Variety</b></label>
				<div class="col-sm-3">
					<input type="text" readonly class="form-control-plaintext"
						id="variety" value="${wine.getVariety()}">
				</div>
			</div>
			<div class="form-group row">
				<label for="vineyard" class="col-sm-2 col-form-label"><b>Vineyard</b></label>
				<div class="col-sm-3">
					<input type="text" readonly class="form-control-plaintext"
						id="vineyard" value="${wine.getVineyard()}">
				</div>
			</div>
			<div class="form-group row">
				<label for="wineryname" class="col-sm-2 col-form-label"><b>Winery Name</b></label>
				<div class="col-sm-3 pt-1" style="font-size:20px">
					<a href="winery?wineryname=<c:out value="${wine.getWineryName()}"/>"><c:out
								value="${wine.getWineryName()}" /></a>
				</div>
			</div>
		</form>
		
		
		<div class="row">
		<div class="col-5">
		<h2>Taster Ratings: Avg Rating - ${avgrating}</h2>
		<table class="table" border="1">
		<thead class="table-dark">
			<tr>
				<th scope="col">ID</th>
				<th scope="col">Taster Name</th>
				<th scope="col">Rating</th>
			</tr>
			</thead>
			<tbody>
			<c:forEach items="${ratings}" var="rating">
				<tr>
					<td><c:out value="${rating.getRatingId()}" /></td>
					<td><a
						href="tasterdetails?id=<c:out value="${rating.getTasterId()}"/>"><c:out
								value="${tasters.get(rating.getTasterId())}" /></a></td>
					<td><c:out value="${rating.getRating()}" /></td>
				
				</tr>
			</c:forEach>
			</tbody>
		</table>
		</div>
		<div class="col-2">
		</div>
		<div class="col-5">
			<h2>Customer Reviews</h2>
		<table class="table" border="1">
		<thead class="table-dark">
			<tr>
				<th scope="col">ID</th>
				<th scope="col">Customer Name</th>
				<th scope="col">Content</th>
				<th scope="col">Created</th>
			</tr>
			</thead>
			<tbody>
			<c:forEach items="${reviews}" var="review">
				<tr>
					<td><c:out value="${review.getReviewId()}" /></td>
					<td><a
						href="customerdetails?id=<c:out value="${review.getCustomerId()}"/>"><c:out
								value="${customers.get(review.getCustomerId())}" /></a></td>
					<td><c:out value="${review.getContent()}" /></td>
					<td><fmt:formatDate value="${review.getCreated()}" pattern="MM-dd-yyyy hh:mm a" /></td>
				
				</tr>
			</c:forEach>
			</tbody>
		</table>
		</div>
		</div>
		
		<div class="row">
		<div class="col-3"></div>
		<div class="col-6">
		<h2>Store Sales</h2>
		<table class="table" border="1">
		<thead class="table-dark">
			<tr>
				<th scope="col">ID</th>
				<th scope="col">Store Name</th>
				<th scope="col">Quantity</th>
				<th scope="col">Sale Date</th>
			</tr>
			</thead>
			<tbody>
			<c:forEach items="${sales}" var="sale">
				<tr>
					<td><c:out value="${sale.getSaleId()}" /></td>
					<td><a
						href="storedetails?id=<c:out value="${sale.getStoreId()}"/>"><c:out
								value="${stores.get(sale.getStoreId())}" /></a></td>
					
					
					<td><c:out value="${sale.getNumOfBottles()}" /></td>
					<td><fmt:formatDate value="${sale.getMadeDate()}" pattern="MM-dd-yyyy hh:mm a" /></td>				
				</tr>
			</c:forEach>
			</tbody>
		</table>
		</div>
		<div class="col-3"></div>
		</div>
		</div>
		
</body>
</html>