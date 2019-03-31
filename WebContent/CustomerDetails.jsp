<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Customer Details</title>
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
		<h2>Customer Information <a style="color: black" title="Edit"
				href="editcustomer?id=<c:out value="${customer.getUserId()}"/>"><c:out
					value="" /><i class="fas fa-edit"></i></a>
					</h2>
		<form>
			<div class="form-group row">
				<label for="customername" class="col-sm-2 col-form-label"><b>Customer Name</b></label>
				<div class="col-sm-3">
					<input type="text" readonly class="form-control-plaintext"
						id="customername" value="${customer.getUserName()}">
				</div>
			</div>
			<div class="form-group row">
				<label for="firstname" class="col-sm-2 col-form-label"><b>First Name</b></label>
				<div class="col-sm-3">
					<input type="text" readonly class="form-control-plaintext"
						id="firstname" value="${customer.getFirstName()}">
				</div>
			</div>
			<div class="form-group row">
				<label for="lastname" class="col-sm-2 col-form-label"><b>Last Name</b></label>
				<div class="col-sm-3">
					<input type="text" readonly class="form-control-plaintext"
						id="lastname" value="${customer.getLastName()}">
				</div>
			</div>
			<div class="form-group row">
				<label for="about" class="col-sm-2 col-form-label"><b>About</b></label>
				<div class="col-sm-3">
					<input type="text" readonly class="form-control-plaintext"
						id="about" value="${customer.getAbout()}">
				</div>
			</div>
			
		</form>
		<div class="row">
		<div class="col-5">
		<div class="row">
		<div class="col-9">
		<h2>Wines Reviewed</h2>
		</div>
		
		<a class="mt-2 ml-4" href="createreview?id=<c:out value="${customer.getUserId()}"/>"><c:out
								value="Create Review" /></a>
					</div>
		<table class="table" border="1">
		<thead class="table-dark">
			<tr>
				<th scope="col">ID</th>
				<th scope="col">Wine Name</th>
				<th scope="col">Content</th>
				<th scope="col">Created</th>
				<th scope="col"> </th>
			</tr>
			</thead>
			<tbody>
			<c:forEach items="${reviews}" var="review">
				<tr>
					<td><c:out value="${review.getReviewId()}" /></td>
					<td><a
						href="winedetails?id=<c:out value="${review.getWineId()}"/>"><c:out
								value="${wines.get(review.getWineId())}" /></a></td>
					<td><c:out value="${review.getContent()}" /></td>
					<td><fmt:formatDate value="${review.getCreated()}" pattern="MM-dd-yyyy hh:mm a" /></td>
					<td><a style="color:black"
						href="deletereview?id=<c:out value="${review.getReviewId()}"/>"><c:out
								value=""/><i class="far fa-trash-alt"></i></a></td>
				</tr>
			</c:forEach>
			</tbody>
		</table>
		</div>
		<div class="col-2">
		</div>
		<div class="col-5">
		<div class="row">
		<div class="col-sm-9">
			<h2>Following: Count = ${followingCount}</h2>
			</div>
			<a class="mt-2 ml-4" href="createfollow?id=<c:out value="${customer.getUserId()}"/>"><c:out
								value="Create Follow" /></a>
								</div>
		<table class="table" border="1">
		<thead class="table-dark">
			<tr>
				<th scope="col">ID</th>
				<th scope="col">Taster Name</th>
				
			</tr>
			</thead>
			<tbody>
			<c:forEach items="${following}" var="follow">
				<tr>
					<td><c:out value="${follow.getUserId()}" /></td>
					<td><a
						href="tasterdetails?id=<c:out value="${follow.getUserId()}"/>"><c:out
								value="${follow.getUserName()}" /></a></td>
					
				
				</tr>
			</c:forEach>
			</tbody>
		</table>
		</div>
 </div>
		</div>
		
</body>
</html>