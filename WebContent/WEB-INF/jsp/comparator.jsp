<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" type="text/css" href="" />
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<spring:url value="/resources/blue/css/style.css" var="benchwarmerCSS" />
<spring:url value="/resources/js/timemachine.js" var="timemachineJS" />
<link href="${benchwarmerCSS}" rel="stylesheet" />
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script src="${timemachineJS}"></script>
<title>Skater Comparator</title>
</head>
<body>
<div id="statOptions">
	<form action="comptable.html" target="tableFrame" method="get" onsubmit="return checkComparison()">
		<label>Compare players using </label>
		<select name="baseSeason">
			<c:forEach items="${seasons}" varStatus="season">
				<c:set var="seasonid" value="${seasons[season.index].seasonid}" />
				<c:if test="${seasonid != baseS}">
					<option value="${seasonid}">${seasonid}</option>
				</c:if>
				<c:if test="${seasonid == baseS}">
					<option value="${seasonid}" selected>${seasonid}</option>
				</c:if>
			</c:forEach>
		</select>
		<label> production rates</label>
		<br>
		<label>Player #1</label>
		<select id="player1" class="player" name="player1">
			<c:forEach items="${playerbios}" varStatus="player">
				<c:set var="playerid" value="${playerbios[player.index].playerid}" />
				<c:if test="${playerid != p1.playerid}">
					<option value="${playerid}">${playerbios[player.index].playerName}</option>
				</c:if>
				<c:if test="${playerid == p1.playerid}">
					<option value="${playerid}" selected>${playerbios[player.index].playerName}</option>
				</c:if>
			</c:forEach>
		</select>
		<label>Player #2</label>
		<select id="player2" class="player" name="player2">
			<option value="${p2.playerid}" selected>${p2.playerName}</option>
			<c:forEach items="${playerbios}" varStatus="player">
				<c:set var="playerid" value="${playerbios[player.index].playerid}" />
				<c:if test="${playerid != p2.playerid}">
					<option value="${playerid}">${playerbios[player.index].playerName}</option>
				</c:if>
				<c:if test="${playerid == p2.playerid}">
					<option value="${playerid}" selected>${playerbios[player.index].playerName}</option>
				</c:if>
			</c:forEach>
		</select>
		<br>
		<input id="compare" type="submit" value="Compare"/>
		<input id="include" type="checkbox" name="include">Display Individual Seasons
		<input type="hidden" name="sort" value="pts" />
	</form>
</div>
</body>
</html>