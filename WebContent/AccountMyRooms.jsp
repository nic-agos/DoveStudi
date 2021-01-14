<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link href="css/sidebar.css" rel="stylesheet">
<link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<script src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script src="https://code.jquery.com/jquery-3.5.0.js"></script>
</head>


<body>
	<div id="sidebar">
		<div id="rectangle" >
			<div class="toggle-btn" onclick="toggleSideBar(); togglePage();toggleTopBar();">
				<span></span>
				<span></span>
				<span></span>
			</div>
		</div>
		 <ul>
		 	<li><a style="font-size:25px; border-bottom:1px solid #FFFFFF;">#WhereToStudy</a></li>
		 	<li><a href="HomePage.jsp">Homepage</a></li>
		 	<li><a style="text-decoration:underline;" href="AccountPagePersonalInfo.jsp">My Account</a></li>
		 	<li><a href="SearchRoom.jsp">Search for Rooms</a></li>
		 	<li><a href="PostRoom.jsp">Post a Room</a></li>
		 	<li><a href="index.jsp">Log out</a></li>
		 </ul>
	</div>
	
	<script>function toggleSideBar(){
				document.getElementById("sidebar").classList.toggle("active");
			}	
	</script>
	<script>function togglePage(){
				document.getElementById("page").classList.toggle("active");
			}	
	</script>	
	<script>function toggleTopBar(){
				document.getElementById("topBar").classList.toggle("active");
			}	
	</script>	
</body>
</html>