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
	<form id = "table" action="sortgoalie.html?" target="tableFrame" method="GET">
		<input id="type" type="hidden" value="goalie" />
		<input id="pageNum" type="hidden" name="pageNum" value="${pageNum}" />
		<input id="count" type="hidden" value="${goalies.size()}" />
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
        	<th class="player" onclick="sortGoalies('player')">Player</th>
            <th>Season</th>
            <th class="tooltip" onclick="formatTable('gp')">GP<span class="tooltiptext">Games Played</span></th>
            <th class="tooltip" onclick="formatTable('toi')">TOI<span class="tooltiptext">Time On Ice</span></th>
            <th class="tooltip" onclick="formatTable('w')">W<span class="tooltiptext">Wins</span></th>
            <th class="tooltip" onclick="formatTable('l')">L<span class="tooltiptext">Losses</span></th>
            <th class="tooltip" onclick="formatTable('sa')">SA<span class="tooltiptext">Shots Against</span></th>
            <th class="tooltip" onclick="formatTable('sv')">SV<span class="tooltiptext">Saves</span></th>
            <th class="tooltip" onclick="formatTable('sa60')">SA/60<span class="tooltiptext">Shots Against Per 60 Minutes</span></th>
            <th class="tooltip" onclick="formatTable('gaa')">GAA<span class="tooltiptext">Goals Against Average</span></th>
            <th class="tooltip" onclick="formatTable('svpct')">SVPCT<span class="tooltiptext">Save Percentage</span></th>
            <th class="tooltip" onclick="formatTable('sa60diff')">SA/60(+/-)<span class="tooltiptext">Shots Against Change Via Era Adjustment</span></th>
            <th class="tooltip" onclick="formatTable('gaadiff')">GAA(+/-)<span class="tooltiptext">Goals Against Average Change Via Era Adjustment</span></th>
            <th class="tooltip" onclick="formatTable('svpctdiff')">SVPCT(+/-)<span class="tooltiptext">Save Percentage Change Via Era Adjustment</span></th>
        </tr>
        <c:forEach var="goalie" items="${current}">
            <tr>
            	<td><c:out value="${goalie.getRank()}" /></td>
                <td><c:out value="${goalie.getPlayername()}" /></td>
                <td class="leftBorder" ><c:out value="${goalie.getSeasonid()}" /></td>
                <td><c:out value="${goalie.getGp()}" /></td>
                <td><fmt:formatNumber type = "number" minFractionDigits = "1" value = "${goalie.getToi()}" /></td>
                <td><c:out value="${goalie.getW()}" /></td>
                <td><c:out value="${goalie.getL()}" /></td>
                <td><c:out value="${goalie.getSa()}" /></td>
                <td><c:out value="${goalie.getSv()}" /></td>
                <td><fmt:formatNumber type = "number" minFractionDigits = "1" value = "${goalie.getSa60()}" /></td> 
                <td><fmt:formatNumber type = "number" minFractionDigits = "2" value = "${goalie.getGaa()}" /></td>
                <td><fmt:formatNumber type = "number" minFractionDigits = "3" value = "${goalie.getSvpct()}" /></td>
                <td class="leftBorder" ><fmt:formatNumber type = "number" minFractionDigits = "1" value = "${goalie.getSA60Diff()}" /></td> 
                <td><fmt:formatNumber type = "number" minFractionDigits = "2" value = "${goalie.getGAADiff()}" /></td>
                <td><fmt:formatNumber type = "number" minFractionDigits = "3" value = "${goalie.getSVPCTDiff()}" /></td>
            </tr>
        </c:forEach>
    </table>
   </form>
   <br>
</div>
</body>
</html>