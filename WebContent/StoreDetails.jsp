<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Store Details</title>
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
		<h2>Store Information</h2>
		<form>
			<div class="form-group row">
				<label for="storename" class="col-sm-2 col-form-label"><b>Store Name</b></label>
				<div class="col-sm-3">
					<input type="text" readonly class="form-control-plaintext"
						id="storename" value="${store.getName()}">
				</div>
			</div>
			<div class="form-group row">
				<label for="street1" class="col-sm-2 col-form-label"><b>Street 1</b></label>
				<div class="col-sm-3">
					<input type="text" readonly class="form-control-plaintext"
						id="street1" value="${store.getStreet1()}">
				</div>
			</div>
			<div class="form-group row">
				<label for="street2" class="col-sm-2 col-form-label"><b>Street 2</b></label>
				<div class="col-sm-3">
					<input type="text" readonly class="form-control-plaintext"
						id="street2" value="${store.getStreet2()}">
				</div>
			</div>
			<div class="form-group row">
				<label for="city" class="col-sm-2 col-form-label"><b>City</b></label>
				<div class="col-sm-3">
					<input type="text" readonly class="form-control-plaintext"
						id="city" value="${store.getCity()}">
				</div>
			</div>
			<div class="form-group row">
				<label for="state" class="col-sm-2 col-form-label"><b>State</b></label>
				<div class="col-sm-3">
					<input type="text" readonly class="form-control-plaintext"
						id="state" value="${store.getState()}">
				</div>
			</div>
			<div class="form-group row">
				<label for="zip" class="col-sm-2 col-form-label"><b>Zip</b></label>
				<div class="col-sm-3">
					<input type="text" readonly class="form-control-plaintext"
						id="zip" value="${store.getZip()}">
				</div>
			</div>
			
		</form>
		<div class="row">
		<div class="col-1 ml-3"></div>
			<h2>Store Sales</h2>
			<a class="mt-2 ml-5 " style="font-size:20px" href="createsale?id=<c:out value="${store.getStoreId()}"/>"><c:out
								value="Create Sale" /></a>
		</div>
		<div class="row">
		<div class="col-1"></div>
		<div class="col-10">
		<table class="table" border="1">
		<thead class="table-dark">
			<tr>
				<th scope="col">ID</th>
				<th scope="col">Wine Name</th>
				<th scope="col">Count</th>
				<th scope="col">Sale Date</th>
				<th scope="col"> </th>
			</tr>
			</thead>
			<tbody>
			<c:forEach items="${sales}" var="sale">
				<tr>
					<td><c:out value="${sale.getSaleId()}" /></td>
					<td><a
						href="winedetails?id=<c:out value="${sale.getWineId()}"/>"><c:out
								value="${wines.get(sale.getWineId())}" /></a></td>
					<td><c:out value="${sale.getNumOfBottles()}" /></td>
					<td><fmt:formatDate value="${sale.getMadeDate()}" pattern="MM-dd-yyyy hh:mm a" /></td>
					<td><a style="color:black"
						href="deletesale?id=<c:out value="${sale.getSaleId()}"/>"><c:out
								value=""/><i class="far fa-trash-alt"></i></a></td>
				</tr>
			</c:forEach>
			</tbody>
		</table>
		</div>
		<div class="col-1"></div>
		</div>

</div>
</body>
</html>