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
<title>Insert title here</title>
</head>
<body id="iframe">
<br>
<br>
<div id="stattable">
	<form id = "table" action="sortskater.html?" target="tableFrame" method="GET">
		<input id="type" type="hidden" value="skater" />
		<input id="pageNum" type="hidden" name="pageNum" value="${pageNum}" />
		<input id="count" type="hidden" value="${players.size()}" />
		<input id="sort" type="hidden" name="sort" value="${sort}" />
		<table border="1">
			<c:if test="${baseS == targetS}">
				<caption>Raw ${baseS} Stats</caption>
			</c:if>
			<c:if test="${baseS != targetS}">
				<caption>Stats Adjusted to ${targetS} Rates</caption>
			</c:if>	    	
	        <tr>
	        	<th>Rank</th>
	        	<th class="player" onclick="sortSkaters('player')">Player</th>
	        	<th>Pos</th>
	            <th>Season</th>
	            <th class="tooltip" onclick="formatTable('gp')">GP<span class="tooltiptext">Games Played</span></th>
	            <th class="tooltip" onclick="formatTable('g')">G<span class="tooltiptext">Goals</span></th>
	            <th class="tooltip" onclick="formatTable('a')">A<span class="tooltiptext">Assists</span></th>
	            <th class="tooltip" onclick="formatTable('pts')">P<span class="tooltiptext">Points</span></th>
	            <th class="tooltip" onclick="formatTable('pim')">PIM<span class="tooltiptext">Penalty Minutes</span></th>
	            <th class="tooltip" onclick="formatTable('s')">S<span class="tooltiptext">Shots</span></th>
	            <th class="tooltip" onclick="formatTable('ggp')">G/GP<span class="tooltiptext">Goals Per Game</span></th>
	            <th class="tooltip" onclick="formatTable('agp')">A/GP<span class="tooltiptext">Assists Per Game</span></th>
	            <th class="tooltip" onclick="formatTable('pgp')">P/GP<span class="tooltiptext">Points Per Game</span></th>
	            <th class="tooltip" onclick="formatTable('evg')">EVG<span class="tooltiptext">Even Strength Goals</span></th>
	            <th class="tooltip" onclick="formatTable('eva')">EVA<span class="tooltiptext">Even Strength Assists</span></th>
	            <th class="tooltip" onclick="formatTable('evp')">EVP<span class="tooltiptext">Even Strength Points</span></th>
	            <th class="tooltip" onclick="formatTable('ppg')">PPG<span class="tooltiptext">Power-Play Goals</span></th>
	            <th class="tooltip" onclick="formatTable('ppa')">PPA<span class="tooltiptext">Power-Play Assists</span></th>
	            <th class="tooltip" onclick="formatTable('ppp')">PPP<span class="tooltiptext">Power-Play Points</span></th>
	            <th class="tooltip" onclick="formatTable('shg')">SHG<span class="tooltiptext">Short-Handed Goals</span></th>
	            <th class="tooltip" onclick="formatTable('sha')">SHA<span class="tooltiptext">Short-Handed Assists</span></th>
	            <th class="tooltip" onclick="formatTable('shp')">SHP<span class="tooltiptext">Short-Handed Points</span></th>
	            <th class="tooltip" onclick="formatTable('gdiff')">G(+/-)<span class="tooltiptext">Goal Change Via Era Adjustment</span></th>
	            <th class="tooltip" onclick="formatTable('adiff')">A(+/-)<span class="tooltiptext">Assist Change Via Era Adjustment</span></th>
	            <th class="tooltip" onclick="formatTable('pdiff')">P(+/-)<span class="tooltiptext">Point Change Via Era Adjustment</span></th>
	        </tr>
	        <c:forEach var="player" items="${skaters}">
	            <tr>
	                <td><c:out value="${player.getRank()}" /></td>
	                <td><c:out value="${player.getPlayername()}" /></td>
	                <td><c:out value="${player.getPosition()}" /></td>
	                <td class="leftBorder" ><c:out value="${player.getSeasonid()}" /></td>
	                <td><c:out value="${player.getGp()}" /></td>
	                <td><c:out value="${player.getG()}" /></td>
	                <td><c:out value="${player.getA()}" /></td>
	                <td><c:out value="${player.getP()}" /></td>
	                <td><c:out value="${player.getPim()}" /></td>
	                <td><c:out value="${player.getS()}" /></td>  
	                <td class="leftBorder" ><fmt:formatNumber type = "number" minFractionDigits = "3" value = "${player.getGGP()}" /></td>
	                <td><fmt:formatNumber type = "number" minFractionDigits = "3" value = "${player.getAGP()}" /></td>
	                <td><fmt:formatNumber type = "number" minFractionDigits = "3" value = "${player.getPGP()}" /></td>
	                <td class="leftBorder" ><c:out value="${player.getEvg()}" /></td>
	                <td><c:out value="${player.getEva()}" /></td>
	                <td><c:out value="${player.getEvp()}" /></td>
	                <td class="leftDashedBorder" ><c:out value="${player.getPpg()}" /></td>
	                <td><c:out value="${player.getPpa()}" /></td>
	                <td><c:out value="${player.getPpp()}" /></td>
	                <td class="leftDashedBorder" ><c:out value="${player.getShg()}" /></td>
	                <td><c:out value="${player.getSha()}" /></td>
	                <td><c:out value="${player.getShp()}" /></td>
	                <td class="leftBorder" ><c:out value="${player.getGDiff()}" /></td>
	                <td><c:out value="${player.getADiff()}" /></td>
	                <td><c:out value="${player.getPDiff()}" /></td>
	            </tr>
	        </c:forEach>
	    </table>
	</form>
	<br>
</div>
</body>
</html>