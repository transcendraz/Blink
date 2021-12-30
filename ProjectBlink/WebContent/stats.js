
// Immediately display statistics whenever homepage loads
window.onload = getNumOnlineUsers;
window.onload = getSuccessRate ;

// Display the number of users currently online
function getNumOnlineUsers() {
	var username;
	$.ajax({
		url:"numOnlineUsersServlet",
		data:{
			username: 1
		},
		type:'post',
		success: function(response) {
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
			console.log(response);
			document.getElementById("successRate").innerHTML = response;
		}
	});
}

