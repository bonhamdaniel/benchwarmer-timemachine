<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<spring:url value="/resources/blue/css/style.css" var="benchwarmerCSS" />
<spring:url value="/resources/js/timemachine.js" var="timemachineJS" />
<link href="${benchwarmerCSS}" rel="stylesheet" />
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script src="${timemachineJS}"></script>
<title>Insert title here</title>
</head>
<body id="iframe">
<div id="stattable">
	<form id = "table" action="sortskater.html?" target="tableFrame" method="GET">
		<input id="type" type="hidden" value="skater" />
		<input id="pageNum" type="hidden" name="pageNum" value="${pageNum}" />
		<input id="count" type="hidden" value="${players.size()}" />
		<input id="sort" type="hidden" name="sort" value="${sort}" />
		<table border="1" cellpadding="5">
	    	<caption>Adjusted to ${targetS}</caption>
	        <tr>
	        	<th>Rank</th>
	        	<th class="player" onclick="sortSkaters('player')">Player</th>
	            <th>Season</th>
	            <th onclick="formatTable('gp')">GP</th>
	            <th onclick="formatTable('g')">G</th>
	            <th onclick="formatTable('a')">A</th>
	            <th onclick="formatTable('pts')">P</th>
	            <th onclick="formatTable('pim')">PIM</th>
	            <th onclick="formatTable('s')">S</th>
	            <th onclick="formatTable('ggp')">G/GP</th>
	            <th onclick="formatTable('agp')">A/GP</th>
	            <th onclick="formatTable('pgp')">P/GP</th>
	            <th onclick="formatTable('evg')">EVG</th>
	            <th onclick="formatTable('eva')">EVA</th>
	            <th onclick="formatTable('evp')">EVP</th>
	            <th onclick="formatTable('ppg')">PPG</th>
	            <th onclick="formatTable('ppa')">PPA</th>
	            <th onclick="formatTable('ppp')">PPP</th>
	            <th onclick="formatTable('shg')">SHG</th>
	            <th onclick="formatTable('sha')">SHA</th>
	            <th onclick="formatTable('shp')">SHP</th>
	        </tr>
	        <c:forEach var="player" items="${skaters}">
	            <tr>
	                <td><c:out value="${player.getRank()}" /></td>
	                <td><c:out value="${player.getPlayername()}" /></td>
	                <td><c:out value="${player.getSeasonid()}" /></td>
	                <td><c:out value="${player.getGp()}" /></td>
	                <td><c:out value="${player.getG()}" /></td>
	                <td><c:out value="${player.getA()}" /></td>
	                <td><c:out value="${player.getP()}" /></td>
	                <td><c:out value="${player.getPim()}" /></td>
	                <td><c:out value="${player.getS()}" /></td>  
	                <td><fmt:formatNumber type = "number" minFractionDigits = "3" value = "${player.getGGP()}" /></td>
	                <td><fmt:formatNumber type = "number" minFractionDigits = "3" value = "${player.getAGP()}" /></td>
	                <td><fmt:formatNumber type = "number" minFractionDigits = "3" value = "${player.getPGP()}" /></td>
	                <td><c:out value="${player.getEvg()}" /></td>
	                <td><c:out value="${player.getEva()}" /></td>
	                <td><c:out value="${player.getEvp()}" /></td>
	                <td><c:out value="${player.getPpg()}" /></td>
	                <td><c:out value="${player.getPpa()}" /></td>
	                <td><c:out value="${player.getPpp()}" /></td>
	                <td><c:out value="${player.getShg()}" /></td>
	                <td><c:out value="${player.getSha()}" /></td>
	                <td><c:out value="${player.getShp()}" /></td>
	            </tr>
	        </c:forEach>
	    </table>
	</form>
</div>
</body>
</html>