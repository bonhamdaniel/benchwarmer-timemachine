<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<spring:url value="/resources/blue/css/style.css" var="benchwarmerCSS" />
<spring:url value="/resources/js/timemachine.js" var="timemachineJS" />
<link href="${benchwarmerCSS}" rel="stylesheet" />
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script src="${timemachineJS}"></script>
	<title>Benchwarmer Time Machine</title>
	<meta charset="utf-8">
	<meta name="description" content="Era adjustable hockey stats">
	<meta name="keywords" content="hockey, stats, NHL, OHL, QMJHL, WHL">
	<meta name="author" content="Dan Bonham">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
<spring:url value="/resources/blue/css/style.css" var="benchwarmerCSS" />
<link href="${benchwarmerCSS}" rel="stylesheet" />
</head>
<body id="main">
	<header>
		<h2>benchwarmer</h2>
		<div class="menu">time machine</div>
	</header>
	<div class="tab">
		  	<a href="skaters.html" target="statsview">
				<button class="tablinks" onclick="loadSkaterTable()">Skaters</button>
			</a>
		  	<a href="goalies.html" target="statsview">
		  		<button class="tablinks" onclick="loadGoalieTable()">Goalies</button>
		  	</a>
		  	<a href="comparator.html" target="statsview">
		  		<button class="tablinks" onclick="loadCompTable()">Player Comparator</button>
		  	</a>
	</div>
	<iframe id="statsview"
			name="statsview"
			width="100%"
			height="70px">
			<h1>NHL Time Machine</h1>
	</iframe>
	<iframe id="tableFrame"
				name="tableFrame"
				width="100%" 
				height="750px"
				src="skatertable.html?baseSeason=20162017&targetSeason=20162017&sort=pts&min=20">
	</iframe>
	<div id="footer">
		<address><a href="mailto:bonhamdaniel@gmail.com">e-mail benchwarmer</a></address>
		<p>&#169;2017 Dan Bonham</p>
	</div>
</body>
</html>