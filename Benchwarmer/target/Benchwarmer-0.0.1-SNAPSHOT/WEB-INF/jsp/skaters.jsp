<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" type="text/css" href="" />
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<div id="statOptions">
	<form action="" method="get">
		<label>Convert:</label>
		<select name="baseSeason">
			<option id="${message}" 
			        value="${message}"
			        selected="selected">
			        ${message}
			</option>
		</select>
		<label>to</label>
		<input type="checkbox" name="include">Include players from target season
		<input type="submit" value="Convert"/>
	</form>
</div>
<div id="stattable">
</div>
	<div>
		<address><a href="mailto:bonhamdaniel@gmail.com">contact@benchwarmer.com</a></address>
		<p>copy2017 DCDesign</p>
	</div>
</body>
</html>