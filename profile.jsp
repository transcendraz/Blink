<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
	<head>

		<meta charset="UTF-8">
		<title>Profile Page</title>
		
		<link rel="stylesheet" type="text/css" href="nav.css" />
		<!-- <script src="nav.js"></script> -->
	</head>
	<body>
		<!-- Header -->
		<div id = "navbar">
			<a href="home.jsp">BLINK</a>
			<a href="profile.jsp">Profile</a>
			<a href="survey.html">Survey</a>
		</div>
		
		<!-- Content -->
		<div class="content">
			<form name="myProfile">
				
				<input type ="text" id ="lname" value ="lname"><span id="errormsg1"></span>
				<br/>
				<input type ="text" id ="fname" value ="fname"><span id="errormsg2"></span>
				<br/>
				<input type="button" name ="Submit" value="Submit" onclick="insert_profile()"></input>
					
			</form>
		</div>
		
		<!-- Footer -->
		<footer>
		</footer>
	</body>
	
	<script>
	
		window.onload = displayData;
		
		function displayData(){
 			$.ajax({
				url:"displayDataServlet",
				data:{
				},
				type: 'post',
 				success: function(response){
					
					var data = response.split(',',3);
					
					document.getElementById("lname").value = data[0];
					document.getElementById("fname").value = data[1];
				}
			});
		}
	
		function insert_profile(){
			
			var username = sessionStorage.getItem("username");
			
			var lname = document.getElementById("lname").value;
			var error = false;
			if(lname.length==0){
				document.getElementById("errormsg1").innerHTML="Please enter a lname";
				error = true;
			}else{
				document.getElementById("errormsg1").innerHTML="";
			}
			
			var fname = document.getElementById("fname").value;
			if(fname.length==0){
				document.getElementById("errormsg2").innerHTML="Please enter a fname";
				error = true;
			}else{
				document.getElementById("errormsg2").innerHTML="";
			}
			
			if(error==true){
				return;
			}
			
			
			console.log(lname);
			console.log(fname);

 			$.ajax({
				url:"profileServlet",
				data:{
					username: 1,
					lname: lname,
					fname: fname
				},
				type: 'post',
				cache: false
			});
			window.location.replace("survey.html");
			
		}
		
	
	</script>
</html>