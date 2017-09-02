/**
 * 
 */

function formatTable(column) {
	$("#sort").val(column);
	$("#pageNum").val(1);
	$("#table").submit();
}

function loadGoalieTable() {
	$("#tableFrame").attr("src", "goalietable.html?baseSeason=20162017&targetSeason=20162017&sort=svpct&min=20");
}

function loadSkaterTable() {
	$("#tableFrame").attr("src", "skatertable.html?baseSeason=20162017&targetSeason=20162017&sort=pts&min=20");
}

function loadCompTable() {
	$("#tableFrame").attr("src", "comptable.html?baseSeason=20162017&player1=8478550&player2=8476453&sort=pts");
}

function loadTimeMachine() {
	$("#statsview").attr("src", "welcome.html");
	$("#tableFrame").attr("src", "intro.html");
}

function checkComparison() {
	$.get("validatecomparator.html", { player1:$('#p1ayer1').val(), player2:$('#p1ayer2').val() }, function(data) {
		if(data) $("#tableFrame").attr("src", "comptable.html");
		else alert("Cannot compare a goalie with a skater");
	});
}

$('#tableFrame').ready(function() {
	var recordPerPage = 25;
	var totalRows = $('#count').val();
	var totalPages = Math.ceil(totalRows / recordPerPage);
	var $pages = $('<div id="pages"></div>');
	$('<span id="page">' + 'Page' + '&nbsp;</span>').appendTo($pages);
	for (i = 0; i < totalPages; i++) {
		$('<span class="clickable">' + (i + 1) + '&nbsp;</span>').appendTo($pages);
	}
    $($pages).appendTo('#stattable');
    var tr = $('table tbody tr:has(td)');
    $('#pages').find('span').hide();
    var nums = $('#pages span');
    var page = $('#pageNum').val();
    $(nums[0]).show();
    $(nums[1]).show();
    var nBegin = parseInt(page - 10, 10);
    //nEnd = parseInt(page + 10, 10);
    for (var i = nBegin; i <= nBegin+20; i++) {
    	$(nums[i]).show();
    }
    if (nBegin > 2) $(nums[1]).after($("<span>...&nbsp;</span>"));
    if (nBegin+20 < nums.length-2) $(nums[nums.length-1]).before($("<span>...&nbsp;</span>"));
    $(nums[nums.length-1]).show();

	$('span:contains(' + page + '&nbsp;)').attr('class', 'current');// adds focus to currently selected page number
    // Handles the table pagination
	$('.clickable').click(function(event) {// fired when the user clicks for a new page of the table
		var page = $(this).text();
		$('#pageNum').val(parseInt(page));
		$("#table").submit();
	});
});

$('#statsview').ready(function() {
	$(document).on('change', '.season', (function(event) {
		if(($('[name="baseSeason"] option:selected').text() != $('[name="targetSeason"] option:selected').text()) && ($('#season1 option:selected').text() != "All")) $("#include").prop("disabled", false);
		else $("#include").prop("disabled", true);
	}));
});

