<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Net-Income calculator</title>  
		<link href="<c:url value='/static/css/app.css' />" rel="stylesheet"></link>
	</head>

	<body>
		<div class="formcontainer">
			<form  name="myForm" class="form-horizontal" method="POST">
				<input type="text" id="dailyGrossIncome" pattern="^[0-9]+(.[0-9]{2}){0,1}$"
						title="The gross daily income. The expected pattern is 00.00"
						name="netIncome" class="form-control-input-sm"
						value="${dailyGross}"
						placeholder="Daily gross income"/>

				<select name="country">
					<c:forEach items="${countryList}" var="country">
						<option value="${country}" <c:if test="${country == inputCountry}">selected</c:if>>${country}</option>
					</c:forEach>
				</select>
				<input type="submit" value="Check" class="btn btn-primary btn-sm" id="submitButton"/>
			</form>
		</div>
		<div class="tablecontainer">
			<c:if test="${not empty dailyGross}">
				<table class="table table-hover">
				<thead>
					<tr>
						<th>Description</th>
						<th>Value</th>
					</tr>
				</thead>
					<tr>
						<td>Entered Daily gross income:</td><td align="right"> ${dailyGross} ${inputCurrency}</td>
					</tr>
					<tr>
	 					<td>Country-specific tax rate:</td><td align="right"> ${taxRate} </td>
					</tr>
					<tr>
	 					<td>Country-specific fixed costs:</td><td align="right">  ${fixedCosts} ${inputCurrency}</td>
					</tr>
					<tr>
		 				<td>Monthly net income:</td><td align="right">  ${result} PLN </td>
					</tr>
				</table>
			</c:if>
		</div>
	</body>
</html>