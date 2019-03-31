<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Taster Details</title>
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
	<div class="container-fluid mt-5 pt-3">
		<h2>
			Taster Information <a style="color: black" title="Edit"
				href="edittaster?id=<c:out value="${taster.getUserId()}"/>"><c:out
					value="" /><i class="fas fa-edit"></i></a>
		</h2>
		
		<form>
			<div class="form-group row">
				<label for="tastername" class="col-sm-2 col-form-label"><b>Taster
						Name</b></label>
				<div class="col-sm-3">
					<input type="text" readonly class="form-control-plaintext"
						id="tastername" value="${taster.getUserName()}">
				</div>
			</div>
			<div class="form-group row">
				<label for="firstname" class="col-sm-2 col-form-label"><b>First
						Name</b></label>
				<div class="col-sm-3">
					<input type="text" readonly class="form-control-plaintext"
						id="firstname" value="${taster.getFirstName()}">
				</div>
			</div>
			<div class="form-group row">
				<label for="lastname" class="col-sm-2 col-form-label"><b>Last
						Name</b></label>
				<div class="col-sm-3">
					<input type="text" readonly class="form-control-plaintext"
						id="lastname" value="${taster.getLastName()}">
				</div>
			</div>
			<div class="form-group row">
				<label for="twitter" class="col-sm-2 col-form-label"><b>Twitter
						Handle</b></label>
				<div class="col-sm-3">
					<input type="text" readonly class="form-control-plaintext"
						id="twitter" value="${taster.getTwitterHandle()}">
				</div>
			</div>

		</form>
		<div class="row">
			<div class="col-5">
				<div class="row">
					<div class="col-9">
						<h2>Wines Rated</h2>
					</div>

					<a class="mt-2 ml-4"
						href="createrating?id=<c:out value="${taster.getUserId()}"/>"><c:out
							value="Create Rating" /></a>
				</div>
				<table class="table" border="1">
					<thead class="table-dark">
						<tr>
							<th scope="col">ID</th>
							<th scope="col">Wine Name</th>
							<th scope="col">Rating</th>
							<th scope="col"></th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${tasterRatings}" var="rating">
							<tr>
								<td><c:out value="${rating.getRatingId()}" /></td>
								<td><a
									href="winedetails?id=<c:out value="${rating.getWineId()}"/>"><c:out
											value="${wines.get(rating.getWineId())}" /></a></td>
								<td><c:out value="${rating.getRating()}" /></td>
								<td><a style="color: black"
									href="deleterating?id=<c:out value="${rating.getRatingId()}"/>"><c:out
											value="" /><i class="far fa-trash-alt"></i></a></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
			<div class="col-2"></div>
			<div class="col-5">
				<h2>Followers: Count = ${followerCount}</h2>
				<table class="table" border="1">
					<thead class="table-dark">
						<tr>
							<th scope="col">ID</th>
							<th scope="col">Customer Name</th>

						</tr>
					</thead>
					<tbody>
						<c:forEach items="${followers}" var="follower">
							<tr>
								<td><c:out value="${follower.getUserId()}" /></td>
								<td><a
									href="customerdetails?id=<c:out value="${follower.getUserId()}"/>"><c:out
											value="${follower.getUserName()}" /></a></td>


							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
	</div>

</body>
</html>