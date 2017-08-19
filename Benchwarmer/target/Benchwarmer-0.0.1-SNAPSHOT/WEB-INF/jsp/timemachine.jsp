<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Benchwarmer Time Machine</title>
	<meta charset="UTF-8">
	<meta name="description" content="Era adjustable hockey stats">
	<meta name="keywords" content="hockey, stats, NHL, OHL, QMJHL, WHL">
	<meta name="author" content="Dan Bonham">
	<meta name="viewport" content="width=device-width", inital-scale=1.0">
	<link rel="stylesheet" type="text/css" href="{% static 'timemachine/style.css' %}" />
</head>
<body id="main">
	<header>
		<h2>benchwarmer</h2>
		<div class="menu">time machine</div>
	</header>
	<div class="tab">
	  	<a href="skaters.html?baseSeason=20162017&targetSeason=20162017" target="statsview">
			<button class="tablinks">Skaters</button>
		</a>
	  	<a href="{% url 'timemachine:goaliestats' %}?baseSeason=20162017&targetSeason=20162017" target="statsview">
	  		<button class="tablinks">Goalies</button>
	  	</a>
	  	<a href="{% url 'timemachine:comparator' %}?baseSeason=20162017&player1=8448782&player2=8448208" target="statsview">
	  		<button class="tablinks">Player Comparator</button>
	  	</a>
	</div>
	<iframe id="statsview"
			name="statsview"
			width="100%" 
			height="725px" 
			src="{% url 'timemachine:skaterstats' %}?baseSeason=20162017&targetSeason=20162017">
	</iframe>
</body>
</html>