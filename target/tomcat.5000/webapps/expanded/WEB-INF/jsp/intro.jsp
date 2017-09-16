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
<body id="iframe">
	<div id="introduction" class="intro">
		<h4>What would Gretzky's stats look like in today's NHL?
		How many points could Crosby have put up in the high-flying 80's?
		Where does Price rank among the great goalies of the recent past?</h4>
		<h4><em>Use the NHL Time Machine to find out!</em></h4>
		<p><b>Option #1</b> - click 'skaters' or 'goalies' to adjust any individual season since
		   1987/88 to the production rates of any other season in that timeframe.  Alternatively, choose "All" to translate every
		   available season and see how players from the different eras measure up.</p>
		<div class="list">	
			<ol>
				<li>Select the season(s) you wish to adjust
				<li>Select the season to use as a baseline (production rates)
				<li>Set the minimum games played filter
				<li>Check 'include' if you wish to display player stats from the baseline season
				<li>Click 'Convert'
			</ol>
		</div>
		<p><b>Option #2</b> - click 'player comparator' to compare player's era-adjusted historical performances head-to-head.  
		<div class="list">
			<ol>
				<li>Select the season to use as a baseline (production rates)
				<li>Select the two players you wish to compare
				<li>Check 'include' if you wish to display each player's individual seasons instead of their career totals
				<li>Click 'Convert'
			</ol>
		</div>
		<p>*All table columns can be sorted by clicking on their respective headers.</p>
	</div>
</body>
</html>