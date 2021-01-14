<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Post a Room</title>
<link href="css/postRoom.css" rel="stylesheet"/>
<link href="css/sidebar.css" rel="stylesheet" />
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
		 	<li style="background:#FF5500;""><a style="font-size:25px; font-weight:bold; background-color:#FF5500;">#WhereToStudy</a></li>
		 	<li><a href="HomePage.jsp">LoggedHomePage.jsp</a></li>
		 	<li><a href="AccountPagePersonalInfo.jsp">My Account</a></li>
		 	<li><a href="SearchRoom.jsp">Search for Rooms</a></li>
		 	<li><a href="PostRoom.jsp">Post a Room</a></li>
		 	<li><a href="index2.jsp">Log out</a></li>
		 </ul>
	</div>
	<div class="curved upper">
		<h2>#WhereToStudy</h2>
		<svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 1440 320"><path fill="#ffffff" fill-opacity="1" d="M0,64L48,96C96,128,192,192,288,224C384,256,480,256,576,245.3C672,235,768,213,864,181.3C960,149,1056,107,1152,106.7C1248,107,1344,149,1392,170.7L1440,192L1440,320L1392,320C1344,320,1248,320,1152,320C1056,320,960,320,864,320C768,320,672,320,576,320C480,320,384,320,288,320C192,320,96,320,48,320L0,320Z"></path></svg>
		<h1>Post a Room</h1>
	</div>
	<div class="curved">
	<div class="wrapper">
		
		<form class="regform" method="post" onsubmit="return validate();"><!-- action="AccountPage.jsp"* -->
			
			<div class="container">
				<div id ="line" class="name">
					<label>Room ad name</label>
					<input id="room_name" type="text" name="room_name" value="" required><br/>
				</div>
				<div id ="line">
					<label>Room description</label>
					<input type="txt" id="description" name="description" value="" required style="height:150px; width:300px;"><!-- style="width:100%; font-size:16px; padding: 8px 0; margin: 8px 0; border-bottom: 1px solid #000000;" -->
				</div>
				<div id="line">	
					<label>Available places</label>
					<input id="room_name" type="text" name="room_name" value="" required><br/>
				</div>
				<div id="line">
					<label>Offered services</label>
					<div>
  						<input type="radio" id="wifi" name="wifi" value="wifi"checked >
  						<label for="wifi">WI-FI</label>
					</div>

					<div>
  						<input type="radio" id="sockets" name="sockets" value="sockets">
  						<label for="sockets">Sockets</label>
					</div>

					<div>
  						<input type="radio" id="airConditioning" name="airConditioning" value="airConditioning">
  						<label for="airConditioning">Air conditioning</label>
					</div>
					<div>
  						<input type="radio" id="smoking" name="smoking" value="smoking">
  						<label for="smoking">Smoking Area</label>
					</div>
					<div>
  						<input type="radio" id="wc" name="wc" value="wc">
  						<label for="wc">WC</label>
					</div>
					<div>
  						<input type="radio" id="accessible" name="accessible" value="accessible">
  						<label for="accessible">Wheelchair accessible</label>
					</div>
					<div>
  						<input type="radio" id="pctablet" name="pctablet" value="pctablet">
  						<label for="pctablet">Pc or Tablet availability</label>
					</div>
					<div>
  						<input type="radio" id="minibar" name="minibar" value="minibar">
  						<label for="minibar">Minibar</label>
					</div>
					<div>
  						<input type="radio" id="parking" name="parking" value="parking">
  						<label for="parking">Parking</label>
					</div>
					
				</div>
				<div id="line">	
					<label>Room address</label>
					<input id="room_address" type="text" name="room_address" value="" required><br/>
				</div>
			</div>
			<button class="btn"><a href="AccountMyRooms.jsp">Post Room</a></button>
		</div>
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