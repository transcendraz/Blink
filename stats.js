// Display the number of users currently online
function getNumOnlineUsers() {
	$.ajax({
		url:"numOnlineUsersServlet",
		data:{
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
		},
		type:'post',
		success: function(response) {
			document.getElementById("successRate").innerHTML = response;
		}
	});
}

// Immediately display statistics whenever homepage loads
getNumOnlineUsers();
getSuccessRate();
