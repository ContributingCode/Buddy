$(document).ready(function() {
	$('#register').click(function() {
		$("#registerDiv").dialog( {
			title: 'User Registration',
			modal: true,
			resizable: false,
			width: 425,
			height: 300,
			show: 'fade'
    	});

		$("#registerForm").show();
		
		// prevent the default action, e.g., following a link
		return false;
	});

	$("#Submit").click(function() {
		if ($("form.registerForm #UserPasswd").val()!=$("form.registerForm #UserPasswd2").val()) {
			$("#message").html("<p class='error'>Password don't match</p>");
			return false;
		}
		
		var action = $("#registerForm").attr('action');
		var form_data = {
			UserName: $("form.registerForm #UserName").val(),
			UserPasswd: $("form.registerForm #UserPasswd").val(),
			is_ajax: 1
		};

		$.ajax({
			type: "POST",
			url: action,
			data: form_data,
			success: function(response)
			{
				if(response == 'success')
					$("#registerForm").slideUp('slow', function() {
						$("#message").html("<p class='success'>Registration successfully! Login to play!!</p>");
					});
				else
					$("#message").html("<p class='error'>" + response + "</p>");
			}
		});

		return false;
	});

	$('#login').click(function() {
		$("#loginDiv").dialog( {
			title: 'User Login',
			modal: true,
			resizable: false,
			width: 425,
			height: 250,
			show: 'fade'
    	});

		$("#loginForm").show();    	
		return false;
	});

	$("#Login").click(function() {

		var action = $("#loginForm").attr('action');
		var form_data = {
			UserName: $("form.loginForm #UserName").val(),
			UserPasswd: $("form.loginForm #UserPasswd").val(),
			is_ajax: 1
		};

		$.ajax({
			type: "POST",
			url: action,
			data: form_data,
			success: function(response)
			{
				if(response != 'error')
					$("#loginForm").slideUp('slow', function() {
						$("#messageLogin").html("<p class='success'>Login successfull!!</p>");
					});
				else
					$("#messageLogin").html("<p class='error'>Login failed</p>");
			}
		});

		return false;
	});
	
	$('#create').click(createGame);
	
	$('#refreshMyGame').on("click", GetMyGame);

	$('#refreshFindGame').on("click", FindGame);
	
	//register link events
	$('ul#myGameUL').on('click', 'a.MyGameLink', clickMyGameLink);
	
	$('article#gb').on({
		click: clickGameTileDiv,
		mouseenter: function(){
			$(this).addClass("inside");
		},
		mouseleave: function(){
			$(this).removeClass("inside");
		}
	}, 'div.gbTileE');
	
	$('article#gb').on('click', 'button', clickMyGameLink);
	
	$('ul#findGameUL').on('click', 'a.FindGameLink', clickFindGameLink);
});

function createGame() {
	var action = "CreateGame";
	var form_data = {
		is_ajax: 1
	};

	$.ajax({
		type: "GET",
		url: action,
		data: form_data,
		success: function(response)
		{
			if(response != 'error') {
				$("#messageNotification").html("<p class='success'>Game #" + response + " created successfull!!</p>");
				GetMyGame();
			} else {
				$("#messageNotification").html("<p class='error'>Create game failed</p>");
			}
		}
	});
	
	$("#notification").dialog( {
		title: 'Notification',
		modal: true,
		resizable: false,
		width: 425,
		height: 250,
		show: 'fade'
	});

	return false;
}

function FindGame() {
	var action = "FindGame";
	var form_data = {
		is_ajax: 1
	};

	$.ajax({
		type: "GET",
		url: action,
		data: form_data,
		success: function(response)
		{
			if(response != 'error') {
				$("#findGameUL").html(response);
			} else {
				$("#messageNotification").html("<p class='error'>Find game failed</p>");
				$("#notification").dialog( {
					title: 'Notification',
					modal: true,
					resizable: false,
					width: 425,
					height: 250,
					show: 'fade'
		    	});
			}
		}
	});
	
	return false;
}

function GetMyGame() {
	var action = "MyGame";
	var form_data = {
		is_ajax: 1
	};

	$.ajax({
		type: "GET",
		url: action,
		data: form_data,
		success: function(response)
		{
			if(response != 'error') {
				$("#myGameUL").html(response);
			} else {
				$("#messageNotification").html("<p class='error'>Get game failed</p>");
				$("#notification").dialog( {
					title: 'Notification',
					modal: true,
					resizable: false,
					width: 425,
					height: 250,
					show: 'fade'
		    	});
			}
		}
	});
	
	return false;
}

function clickMyGameLink() {
	var action = "GetGame";
	var form_data = {
		id: this.id.substring(4),
		is_ajax: 1
	};

	$.ajax({
		type: "GET",
		url: action,
		data: form_data,
		success: function(response)
		{
			if(response != 'error') {
				$("#gb").html(response);
			} else {
				$("#messageNotification").html("<p class='error'>Get game failed</p>");
				$("#notification").dialog( {
					title: 'Notification',
					modal: true,
					resizable: false,
					width: 425,
					height: 250,
					show: 'fade'
		    	});
			}
		}
	});

	return false;
}

function clickFindGameLink() {
	var action = "JoinGame";
	var gameID = this.id.substring(4);
	var form_data = {
		id: gameID,
		is_ajax: 1
	};

	$.ajax({
		type: "GET",
		url: action,
		data: form_data,
		success: function(response)
		{
			if(response != 'error') {
				$("#messageNotification").html("<p class='error'>Joined game: " + response + "</p>");
				$("#notification").dialog( {
					title: 'Notification',
					modal: true,
					resizable: false,
					width: 425,
					height: 250,
					show: 'fade'
		    	});
				
				refreshGame(gameID);
			} else {
				$("#messageNotification").html("<p class='error'>Join game failed</p>");
				$("#notification").dialog( {
					title: 'Notification',
					modal: true,
					resizable: false,
					width: 425,
					height: 250,
					show: 'fade'
		    	});
			}
			
			FindGame();
			GetMyGame();
		}
	});

	return false;
}

function clickGameTileDiv() {
	var action = "AddMove";
	var gameID = $('div.gbMain').get(0).id.substring(4);
	var form_data = {
		id: gameID,
		tile: this.id.substring(6),
		is_ajax: 1
	}; // querystring: id=50&tile=3&is_ajax=1

	$.ajax({
		type: "GET",
		url: action,
		data: form_data,
		success: function(response)
		{
			if(response == 'error') {
				$("#messageNotification").html("<p class='error'>Invalid move</p>");
				$("#notification").dialog( {
					title: 'Notification',
					modal: true,
					resizable: false,
					width: 425,
					height: 250,
					show: 'fade'
		    	});
			} else {
				refreshGame(gameID);
			}
		}
	});

	return false;
}

function refreshGame(gameID) {
	var action = "GetGame";
	var form_data = {
		id: gameID,
		is_ajax: 1
	};

	$.ajax({
		type: "GET",
		url: action,
		data: form_data,
		success: function(response)
		{
			if(response != 'error') {
				$("#gb").html(response);
			} else {
				$("#messageNotification").html("<p class='error'>Get game failed</p>");
				$("#notification").dialog( {
					title: 'Notification',
					modal: true,
					resizable: false,
					width: 425,
					height: 250,
					show: 'fade'
		    	});
			}
		}
	});

	return false;
}