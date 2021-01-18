<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Post a Room</title>
<link href="css/postRoom.css" rel="stylesheet"/>
<link href="css/sidebar.css" rel="stylesheet" />
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">
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
		 	<li style="background:#FF5500; color:#ffffff"><a style="font-size:20px; font-weight:bold; background-color:#FF5500;">#DoveStudi</a></li>
		 	<li><a href="HomePage.jsp">LoggedHomePage.jsp</a></li>
		 	<li><a href="AccountPersonalInfo.jsp">My Account</a></li>
		 	<li><a href="MyGroups.jsp">My Groups</a></li>
		 	<li><a href="SearchRoom.jsp">Search for Rooms</a></li>
		 	<li><a href="PostRoom.jsp">Post a Room</a></li>
		 	<li><a href="index2.jsp">Log out</a></li>
		 </ul>
	</div>
	<div class="curved upper">
		<h2>#DoveStudi</h2>
		<svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 1440 320"><path fill="#ffffff" fill-opacity="1" d="M0,64L48,96C96,128,192,192,288,224C384,256,480,256,576,245.3C672,235,768,213,864,181.3C960,149,1056,107,1152,106.7C1248,107,1344,149,1392,170.7L1440,192L1440,320L1392,320C1344,320,1248,320,1152,320C1056,320,960,320,864,320C768,320,672,320,576,320C480,320,384,320,288,320C192,320,96,320,48,320L0,320Z"></path></svg>
		<h1>Post a Room</h1>
	</div>
	<div class="curved">
		<div class="row">
			<form>
  				<div class="form-group">
    				<label for="adName">Room ad name</label>
    				<input type="text" class="form-control" id="adName">
  				</div>
  				<div class="form-group">
    				<label for="roomAddress">Room address</label>
    				<input type="text" class="form-control" id="roomAddress">
  				</div>
  				<div class="form-group">
    				<label for="roomDescription">Room description</label>
    				<textarea class="form-control" id="roomDescription" rows="3"></textarea>
  				</div>
  				<div class="form-group">
    				<label for="maxSeats">Max seats</label>
    				<input type="text" class="form-control" id="adName">
  				</div>
			</form>
		</div>
		<div class="wrapper" id="div-btn">
			<button id="btn"><a href="AccountMyRooms.jsp">PostRoom</a></button>
		</div>
		

			<!-- <div class="container">
				<div class="vertical-center">
					<button class="btn"><a href="AccountMyRooms.jsp">Post Room</a></button>
				</div>
			</div> -->
		
		<svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 1440 320"><path fill="#FF5500" fill-opacity="1" d="M0,64L48,96C96,128,192,192,288,224C384,256,480,256,576,245.3C672,235,768,213,864,181.3C960,149,1056,107,1152,106.7C1248,107,1344,149,1392,170.7L1440,192L1440,320L1392,320C1344,320,1248,320,1152,320C1056,320,960,320,864,320C768,320,672,320,576,320C480,320,384,320,288,320C192,320,96,320,48,320L0,320Z"></path></svg>
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