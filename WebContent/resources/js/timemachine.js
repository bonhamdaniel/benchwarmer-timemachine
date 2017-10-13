/**
 * 
 */

function formatTable(column) {
	$("#sort").val(column);
	$("#pageNum").val(1);
	$("#table").submit();
}

function loadGoalieTable() {
	$("#tableFrame").attr("src", "goalietable.html?baseSeason=20162017&targetSeason=19871988&sort=svpct&min=20");
}

function loadSkaterTable() {
	$("#tableFrame").attr("src", "skatertable.html?baseSeason=20162017&targetSeason=19871988&sort=pts&min=20&positionFilter=F");
}

function loadCompTable() {
	$("#tableFrame").attr("src", "comptable.html?baseSeason=20162017&player1=8478550&player2=8476453&sort=pts&statsFormat=individual");
}

function loadTimeMachine() {
	$("#statsview").attr("src", "welcome.html");
	$("#tableFrame").attr("src", "intro.html");
}

function resizeIframe(obj) {
	obj.style.height = obj.contentWindow.document.body.scrollHeight + 'px';
}

function resizeTableFrame(obj) {
	obj.style.height = obj.contentWindow.document.body.scrollHeight + 30 + 'px';
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
	
	$(document).on('change', '[name="targetSeason"]', function() {
		var newTarget = $('[name="targetSeason"]').val();
		$('#skaterInclude').html("Include " + newTarget + " skater stats for comparison");
		$('#goalieInclude').html("Include " + newTarget + " goalie stats for comparison");
	});
});

$('#statsview').ready(function() {
	$(document).on('change', '.comparatorPositionFilter', (function() {
		var position = $('#comparatorPositionFilter').val();
		$.get("getplayers.json", { comparatorPositionFilter:position }, function(json) {
			$('#player1').empty();
			$('#player2').empty();
			$.each(json, function(idx, li) {
				$('#player1').append('<option value=' + li["playerid"] + '>' + li["playerName"] + '</option>');
				$('#player2').append('<option value=' + li["playerid"] + '>' + li["playerName"] + '</option>');
			});
			$('#player2 option[value=' + json[1]["playerid"] + ']').attr('selected', 'selected');
		}, "json");
	}));
});

