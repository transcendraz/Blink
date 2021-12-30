<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Home Page</title>
		<link rel="stylesheet" type="text/css" href="nav.css" />
		<script src="nav.js"></script>
		<script src="login.js"></script>
	</head>
	<style>
		#leftColumn{
			width: 50%;
			height: 100%;
			float: left;
		}
		#rightColumn{
			width: 33%;
			height: 100%;
			float:right;
			font-family: Arial, Helvetica, sans-serif;
		}
		#middleColumn{
			width: 16%;
			height: 100%;
			float: right;
			padding-top: 0;
		}
		#statsTitle {
			font-family: Arial, Helvetica, sans-serif;
			font-size: 30px;
		}
		.statsSmall {
			font-family: Arial, Helvetica, sans-serif;
			font-size: 20px;
		}
		.question {
			font-family: Arial, Helvetica, sans-serif;
			font-size: 20px;
		}
		.answer {
			font-family: Arial, Helvetica, sans-serif;
			font-size: 10px;
		}
		.line {
			border-top: 10px solid #dd661c;
			border-radius: 3px;
		}
		.loginTitle {
			font-size: 25px;
		}
		
		#username1{
			border-radius: 3px;
			padding: 7px;
			margin-bottom: 5px;
			width: 300px;
		}
		#password1{
			border-radius: 3px;
			padding: 7px;
			margin-bottom: 5px;
			width: 300px;
			
		}
		#button1{
			padding: 3px;
			border-radius: 3px;
		    background-color: #ff9999;
		    color: white;
			border: none;	
			font-size: 20px;
		}
		
		#username2{
			border-radius: 3px;
			padding: 7px;
			margin-bottom: 5px;
			width: 300px;
		}
		
		#password2{
			border-radius: 3px;
			padding: 7px;
			margin-bottom: 5px;
			width: 300px;
		}
		
		#button2{
			padding: 3px;
			border-radius: 3px;
		    background-color: #ff9999;
		    color: white;
			border: none;	
			font-size: 20px;
		}
	</style>
	<body>
		<!-- Header -->
		<div id = "navbar">
			<img src="https://i.imgur.com/wFPSJRj.gif" id="logo" >
			<a href="home.jsp">BLINK</a>
		</div>

		<!-- Content -->
		<div class="content">
			<div id = "leftColumn">
				<div id = "introBox">
				<b>	<p class="question" style = "font-size: 200%">What is BLINK?</p></b>
					<p class="answer" style = "font-size:150%">
						BLINK is an anonymous speed dating website that matches users based on personality instead of appearance.
					</p>
					</br>
					<hr class="line">
					</br>
				<b> <p class="question" style = "font-size:200%">STEP 1: Tell us about yourself</p> </b>
					<p class="answer" style = "font-size:150%">
						Fill out your profile and answer a survey about yourself.
					</p>
					</br>
				<b>	<p class="question" style = "font-size:200%">STEP 2: Find a match</p></b>
					<p class="answer" style = "font-size:150%" >
						Hit "Match" to start your first "Date"!
						Based on your survey results, you will be put into a one-on-one anonymous chat.
					</p>
					</br>
				<b><p class="question" style = "font-size:200%">STEP 3: Speed date</p></b>
					<pre class="answer" style = "font-size:150%">
You will have exactly 2 minutes to get to know this person.
When time is up, you can choose "Reveal" or "Reject".
					</pre> 
	
<b><p class= "question" style = "font-size:200%">STEP 4: Decide </p></b>
<pre class = "answer" style = "font-size: 150%">
Choosing "Reveal" will reveal your information to the other user.
If at least one of you chose "Reject", the date is over.
Hit "Match" again to move on to your next date!
					</pre>
				</div>
				<br>
<!-- 				<p id="statsTitle">Today's Statistics</p>
				<p class="statsSmall">Online Users: </p><p id = "numOnlineUsers"></p>
        		<p class="statsSmall">Success Rate: </p><p id = "successRate"></p>	-->
			</div>
	
			<div id = "rightColumn">
				<!-- Continuing User Login -->
				<hr class="line">
				<p class="loginTitle">Continuing User</p>
				<form id="loginForm1" method="POST" onsubmit="loginContinuingUser();">
					<input id="username1" type="text" placeholder="Username">
					<br><input id="password1" type="password" placeholder="Password">
					<span id="errormsg1"></span>
					<br><button id= "button1" type="button" onclick="loginContinuingUser()" value="Login">Login</button>
				</form>
				<br>
				<!-- New User Login -->
				<hr class="line">
				<p class="loginTitle">New User</p>
				<form id="loginForm2" method="POST" onsubmit="loginNewUser();">
					<input id="username2" type="text" placeholder="Username">
					<br><input id="password2" type="password" placeholder="Password">
					<span id="errormsg2"></span>
					<br><button id="button2" type="button" onclick="loginNewUser()">Create Account</button>
				</form>
				<br>
				<!-- Guest Login -->
				<hr class="line">
				<p class="loginTitle">Guest</p>
				<form id="loginForm3" method="POST" onsubmit="loginGuest();">
					<br><button id = "button1" type="button" onclick="loginGuest()" value="Login">Guest Login</button>
				</form>
				<img src = "https://media.giphy.com/media/YKcWT7DvtwVVu/giphy.gif">
			</div>
			<div id = "middleColumn">
				<img src="https://i.imgur.com/CeCSU4v.png" height="580">
			</div>
		</div>

		<!-- Footer -->
		<footer>
		</footer>
		<script>
		
		window.onload = start;
		
		function start() {
			getNumOnlineUsers();
			getSuccessRate();
		}
		
		// Display the number of users currently online
		function getNumOnlineUsers() {
			$.ajax({
				url:"numOnlineUsersServlet",
				data:{
					username: 1
				},
				type:'post',
				success: function(response) {
					console.log("response of getNumOnlineUsers: " + response);
					document.getElementById("numOnlineUsers").innerHTML = response;
				}
			});
		}
		// Display today's successful match rate
		function getSuccessRate() {
			$.ajax({
				url:"successRateServlet",
				data:{
					username: 1
				},
				type:'post',
				success: function(response) {
					console.log("test");
					console.log(response);
					document.getElementById("successRate").innerHTML = response;
				}
			});
		}
		</script>
	</body>
</html>