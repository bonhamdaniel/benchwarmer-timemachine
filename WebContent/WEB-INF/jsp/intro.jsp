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
	<div id="introduction" class="welcome">
		<h4>Ever wonder what Gretzky's stats would look like in today's NHL?</h4>
		<h4>Or how many points Crosby could have put up in the high-flying 80's?</h4>
		<p>Use the NHL Time Machine to find out!</p>
		<p>Option #1 - click on either the "skaters" or "goalies" tab above to adjust any individual season since
		   1987/88 to the production rates of any other season in that timeframe.  Alternatively, choose "All" to translate every
		   season and see how players from the different eras truly measure up.</p>
		<ol>
			<li>Select the season(s) you wish to adjust
			<li>Select the season to use as a baseline (production rates)
			<li>Set the minimum games played filter
			<li>Check 'include' to display player stats from the baseline season
			<li>Press 'Convert'
		</ol>
		<p>Option #2 - click 'player comparator' to compare players head-to-head.
		<ol>
			<li>Select the season to use as a baseline (production rates)
			<li>Select the two players you wish to compare
			<li>Check 'include' to display each of the players individual seasons instead of career totals
			<li>Press 'Convert'
		</ol>
		<p>All columns can be sorted by clicking on their respective headers</p>
	</div>
</body>
</html>