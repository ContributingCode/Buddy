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
<!-- <link rel="stylesheet" href="resources/css/style.css" />
<script src="resources/js/main.js"></script>  -->
<link rel="stylesheet/less" href="resources/less/bootstrap.less">
<script src="resources/js/less.js"></script>
</head>

<body>
<div class="container">

		<!--  	<hgroup>
			<h1>CharityMatch</h1>
			<h2>VMware Hackathon 2012 - Team Buddy</h2>
		</hgroup> -->

		<div class="navbar">
			<div class="navbar-inner">
				<div class="container">
					<a class="brand" href="#"> CharityMatch </a>
					<ul class="nav">
						<li class="active"><a href="">Home</a></li>
						<li class="active"><a href="login">Facebook Login</a></li>
						<li class="active"><a href="analyze">Analyze</a></li>
						<li class="active"><a href="findCharity">Find Charity</a></li>
						<li class="active"><a href="logout">Logout</a></li>
						<li class="active"><a href="#">Help</a></li>
					</ul>
				</div>
			</div>
		</div>
	
		<div class="row-fluid">
		<div class="span4">4</div>
		<div class="span4">4</div>
		<div class="span4">4</div>
			<div class="span12">&copy; 2012 Team Buddy</div>
		</div>
	</div>


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
