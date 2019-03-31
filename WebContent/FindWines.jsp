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
	<div class="container-fluid">

		<form action="findwines" method="post">

			<div class="form-group pt-5 mt-5 row">
				<label class="mr-5 pl-3 pt-2" for="winename">Wine Name</label> <input
					id="winename" class="form-control w-25 mr-4" name="winename"
					value="${fn:escapeXml(param.winename)}" />
				<button type="submit" class="btn btn-primary">Search</button>
				<button type="submit" class="btn btn-danger ml-1">Create
					Wine</button>
			</div>
		</form>
		<br /> <span id="successMessage"><h1>
				<b>${messages.success}</b>
			</h1></span>

		<table border="1">
			<tr>
				<th>ID</th>
				<th>Name</th>
				<th>Description</th>
				<th>Price</th>
				<th>Variety</th>
				<th>Vineyard</th>
				<th>Winery Name</th>

			</tr>
			<c:forEach items="${wines}" var="wine">
				<tr>
					<td><c:out value="${wine.getWineId()}" /></td>
					<td><a
						href="winedetails?id=<c:out value="${wine.getWineId()}"/>"><c:out
								value="${wine.getName()}" /></a></td>
					<td><c:out value="${wine.getDescription()}" /></td>
					<td><c:out value="${wine.getPrice()}" /></td>
					<td><c:out value="${wine.getVariety()}" /></td>
					<td><c:out value="${wine.getVineyard()}" /></td>
					<td><a
						href="winery?wineryname=<c:out value="${wine.getWineryName()}"/>"><c:out
								value="${wine.getWineryName()}" /></a></td>
				</tr>
			</c:forEach>
		</table>
	</div>
</body>
</html>
