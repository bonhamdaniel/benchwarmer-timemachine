<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" type="text/css" href="" />
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<spring:url value="/resources/blue/css/style.css" var="benchwarmerCSS" />
<spring:url value="/resources/js/timemachine.js" var="timemachineJS" />
<link href="${benchwarmerCSS}" rel="stylesheet" />
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script src="${timemachineJS}"></script>
<title>Benchwarmer Time Machine</title>
</head>
<body>
	<div class="welcome">
		<h4>Ever wonder what Gretzky's stats would look like in today's NHL?</h4>
		<h4>Or how many points Crosby could have put up in the high-flying 80's?</h4>
		<p>Use the NHL Time Machine to find out! Click on the "skaters" or "goalies" tab above to adjust any individual season since
		   1987/88 to the production rates of any other season in the same timeframe.  Or, choose "All" to adjust all seasons at once
		   and see who comes out on top when stats are adjusted for their era.</p>
		
	</div>
</body>
</html>