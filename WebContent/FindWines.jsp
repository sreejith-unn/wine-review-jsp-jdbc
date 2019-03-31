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
		<title>Find Wines</title>
		<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">
		<link href="https://use.fontawesome.com/releases/v5.3.1/css/all.css" rel="stylesheet">
		<style>
			#successMessage label {
				font-weight: 900;
			}
			#result-table {
				font-size: 14px;
			}
			.fa-2x.fa-times-circle {
				color: red;
				font-size: 22px;
				text-decoration: none;
			}
			.fa-2x.fa-times-circle:hover {
				color: #D50000;
			}
			.fa-2x.fa-pencil-alt {
				color: #5D1049;
				font-size: 22px;
				text-decoration: none;
			}
			.fa-2x.fa-pencil-alt:hover {
				color: #AA00FF;
			}
			.fa {
				cursor: pointer;
			}
		</style>
	</head>
	<body>
		<div class="navbar fixed-top navbar-expand-lg navbar-dark bg-dark">
			<a class="navbar-brand nav-header" 
			   href="course-list.template.client.html">
				Wine Connoisseur
			</a>
		</div>
		<div class="container-fluid">
			<form action="findwines" method="post">
				<div class="form-group pt-5 mt-5 row">
					<label class="mr-5 pl-3 pt-2" for="winename">Wine Name:</label> 
					<input
						id="winename" 
						class="form-control w-25 mr-3" name="winename"
						value="${fn:escapeXml(param.winename)}" />
					<button id="search-btn" type="submit" class="btn btn-primary">Search</button>
					<a class="btn btn-success ml-2" role="button" href="winecreate">Create Wine</a>
				</div>
			</form>
			<span id="successMessage">
				<label>${messages.success}</label>
			</span>
			<table 
				id="result-table"
				class="table table-bordered table-striped mt-3"
				<c:if test="${fn:length(wines) eq 0}">style="display:none"</c:if>>
				<thead class="thead-dark">
					<tr>
						<th scope="col">ID</th>
						<th scope="col">Name</th>
						<th scope="col">Description</th>
						<th scope="col">Price</th>
						<th scope="col">Variety</th>
						<th scope="col">Vineyard</th>
						<th scope="col">Winery Name</th>
						<th scope="col">Actions</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${wines}" var="wine">
						<tr>
							<td><c:out value="${wine.getWineId()}" /></td>
							<td>
								<a href="winedetails?id=<c:out value="${wine.getWineId()}"/>">
									<c:out value="${wine.getName()}" />
								</a>
							</td>
							<td><c:out value="${wine.getDescription()}" /></td>
							<td><c:out value="${wine.getPrice()}" /></td>
							<td><c:out value="${wine.getVariety()}" /></td>
							<td><c:out value="${wine.getVineyard()}" /></td>
							<td>
								<a href="winery?wineryname=<c:out value="${wine.getWineryName()}"/>">
									<c:out value="${wine.getWineryName()}" />
								</a>
							</td>
							<td>
								<span> 
									<a class="fa-2x fa fa-times-circle wine-remove" 
									   title="Delete"
									   href="winedelete?wineId=<c:out value="${wine.getWineId()}"/>">
									</a> 
									<a class="fa-2x fa fa-pencil-alt wine-edit pl-1" 
									   title="Edit"
									   href="wineupdate?wineId=<c:out value="${wine.getWineId()}"/>">
									</a>
								</span>
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</body>
</html>