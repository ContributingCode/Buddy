<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<link href="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8.18/themes/base/jquery-ui.css" rel="stylesheet"
	type="text/css" />
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.2/jquery.min.js"></script>
<script src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8.18/jquery-ui.min.js"></script>
<title>VMware Hackathon 2012 - Team Buddy - CharityMatch</title>
<!-- The below script Makes IE understand the new html5 tags are there and applies our CSS to it -->
<!--[if IE]>
  <script src="http://html5shiv.googlecode.com/svn/trunk/html5.js"></script>
<![endif]-->
<link rel="stylesheet" href="resources/css/style.css" />
<script src="resources/js/main.js"></script>
</head>

<body>

	<header>
		<hgroup>
			<h1>CharityMatch</h1>
			<h2>VMware Hackathon 2012 - Team Buddy</h2>
		</hgroup>
		<nav>
			<ul>
				<li><a href="">Home</a></li>
				<li><a href="login">Facebook Login</a></li>
				<li><a href="logout">Logout</a></li>
				<li><a href="#">Help</a></li>
			</ul>
		</nav>
	</header>

	<section id="MyGame">
		<h1>
			My Game
			<button id="refreshMyGame">Refresh</button>
		</h1>
		<ul id="myGameUL">
		</ul>
		<h1>
			Find Game
			<button id="refreshFindGame">Refresh</button>
		</h1>
		<ul id="findGameUL">
		</ul>
	</section>

	<article id="gb">Select a game to start</article>

	<section id="JoinGame"></section>

	<footer>
		<p>&copy; 2012 Team Buddy</p>
	</footer>

	<div class="hidden" id="registerDiv">
		<form action="Register" method="post" id="registerForm" class="registerForm">
			<table cellpadding="4px" cellspacing="4px">
				<tr>
					<td>User Name:</td>
					<td><input id="UserName" name="UserName"></td>
				</tr>
				<tr>
					<td>User Password:</td>
					<td><input id="UserPasswd" name="UserPasswd" type="password"></td>
				</tr>
				<tr>
					<td>Confirm Password:</td>
					<td><input id="UserPasswd2" type="password"></td>
				</tr>
				<tr>
					<td>&nbsp;</td>
					<td><input id="Submit" name="Submit" type="Submit" value="Submit"></td>
				</tr>
			</table>
		</form>
		<div id="message" />
	</div>
	<div class="hidden" id="notification">
		<div id="messageNotification" />
	</div>
</body>
</html>
