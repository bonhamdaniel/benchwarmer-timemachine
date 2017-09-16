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
<title>Benchwarmer Time Machine - Goalie Stats</title>
</head>
<body>
<div id="statOptions">
	<form action="goalietable.html?" target="tableFrame" method="get">
		<label class="title">Adjust </label>
		<select id="season1" class="season" name="baseSeason">
			<c:forEach items="${seasons}" varStatus="season">
				<c:set var="seasonid" value="${seasons[season.index].seasonid}" />
				<c:if test="${seasonid != baseS}">
					<option value="${seasonid}">${seasonid}</option>
				</c:if>
				<c:if test="${seasonid == baseS}">
					<option value="${seasonid}" selected>${seasonid}</option>
				</c:if>
			</c:forEach>
				<option value="0">All</option>
				<c:if test="${seasonid == 0}">
					<option value="0" selected>--All--</option>
				</c:if>
		</select>
		<label class="title"> goalie stats to </label>
		<select class="season" name="targetSeason">
			<c:forEach items="${seasons}" varStatus="season">
				<c:set var="seasonid" value="${seasons[season.index].seasonid}" />
				<c:if test="${seasonid != targetS}">
					<option value="${seasonid}">${seasonid}</option>
				</c:if>
				
				<c:if test="${seasonid == targetS}">
					<option value="${seasonid}" selected>${seasonid}</option>
				</c:if>
			</c:forEach>
		</select>
		<label class="title">production rates</label>
		<br>
		<label>Min GP: </label><input type="number" name="min" min="0" max="82" value="20" width="3">
		<br>
		<input id="include" type="checkbox" name="include" disabled />Include goalies from target season
		<br>
		<input type="hidden" name="sort" value="svpct" />
		<input type="submit" value="Convert"/>
	</form>
</div>
</body>
</html>