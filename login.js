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
			// TODO
			/* The newLogin server needs to:
			 * 
			 * 1.) check if username already exists 
			 * and display an error if it does
			 * 
			 * 2.) otherwise, add the user to the database
			 * and redirect the user to the profile page
			*/
			

		}
	});  
}

// Guest Login
function loginGuest() {
	window.location = 'guestChatroom.html';
}