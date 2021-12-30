// Login a continuing user
function loginContinuingUser() {
	var username = document.getElementById("username1").value;
	var password = document.getElementById("password1").value;
	console.log("username: " + username);
	console.log("password: " + password);
	$.ajax({
		url:"login",
		data:{
			username: username,
			password: password
		},
		type:'post',
		success: function(result) {
			result = result.trim();
			var n = result.localeCompare("");
			console.log(result);
			console.log(n);
			if (n==0) {
				window.location = 'profile.jsp';
			} else {
				$("#errormsg1").html(result);
			}
		}
	});  
}

// Create an account login
function loginNewUser() {
	var username = document.getElementById("username2").value;
	var password = document.getElementById("password2").value;
	console.log("username: " + username);
	console.log("password: " + password);
	$.ajax({
		url:"newLogin",
		data:{
			username: username,
			password: password
		},
		type:'post',
		success: function(result) {
			result = result.trim();
			console.log(result);
			if (result == "") {
				window.location = 'profile.jsp';
			} else {
				$("#errormsg2").html(result);
			}
		}
	});  
}

// Guest Login
function loginGuest() {
	var username = document.getElementById("username3").value;
	console.log("username: " + username);
	$.ajax({
		url:"login",
		data:{
			username: username,
		},
		type:'post',
		success: function(response) {
			// TODO i have no idea what to do here :( 
		}
	});  
}