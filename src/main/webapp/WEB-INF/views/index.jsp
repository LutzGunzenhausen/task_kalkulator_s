<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Net-Income calculator</title>  
</head>
 <body ng-app="myApp" class="ng-cloak">
  	<div class="formcontainer">
	  	<form  name="myForm" class="form-horizontal" method="POST">
	  		<input type="text" id="dailyGrossIncome" name="netIncome"
	  			class="form-control-input-sm" placeholder="Daily gross income"/>
	  		<select name="country">
	  			<c:forEach items="${countryList}" var="country">
	  				<option value="${country}">${country}</option>
	  			</c:forEach>
	  		</select>
	  		<input type="submit" value="Check" />
	  	</form>
  	</div>
</body>
</html>