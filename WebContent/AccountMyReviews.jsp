<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>My Reviews</title>
<link href="css/myReviews.css" rel="stylesheet"/>
<link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
</head>


<body>	
	<div class="curved">
		<svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 1440 319"><path fill="#FF5500" fill-opacity="1" d="M0,64L48,96C96,128,192,192,288,224C384,256,480,256,576,245.3C672,235,768,213,864,181.3C960,149,1056,107,1152,106.7C1248,107,1344,149,1392,170.7L1440,192L1440,320L1392,320C1344,320,1248,320,1152,320C1056,320,960,320,864,320C768,320,672,320,576,320C480,320,384,320,288,320C192,320,96,320,48,320L0,320Z"></path></svg>
	</div>
	<div id="sidebar">
		<div id="rectangle" >
			<div class="toggle-btn" onclick="toggleSideBar();">
				<span></span>
				<span></span>
				<span></span>
			</div>
		</div>
		 <ul>
		 	<li style="background:#FF5500; color:#ffffff"><a style="font-size:20px; font-weight:bold; background-color:#FF5500;">#DoveStudi</a></li>
		 	<li><a href="SearchRoomsHost.jsp">Search for Rooms</a></li>
		 	<li><a href="AccPubInfo.jsp">My Account</a></li>
		 	<li><a href="MyGroups.jsp">My Groups</a></li>
		 	<li><a href="AccountMyFutReservations.jsp">My Reservations</a></li>
		 	<li><a href="AccountMyReviews.jsp">My Reviews</a></li>
		 	<li><a href="AccountMyRooms.jsp">My Rooms</a></li>
		 	<li><a href="PostRoom.jsp">Post a Room</a></li>
		 	<li><a href="index.jsp">Log out</a></li>
		 </ul>
	</div>
	
	<div class="container" style="text-align:center; margin-top:20px; font-weight:600;">
  		<div class="vertical-center">
    		<h1 style="font-weight:600;">My Reviews</h1>
  		</div>
	</div>
		
	<div class= "container head-profile">
		<div class="col-md-18" style="margin-top:80px;" >
		<div class="row">
			<div class="card">
				<div class="card-header" style="font-size:20px;font-weight:600;">Done reviews</div>
					<div class="card">
  					<div class="card-body">
						<div class="row">
                        	<div class="col-md-4">
                              	<label>Reviewed user:</label>
                            </div>
                            <div class="col-md-3">
                        		<p><a href="OtherAccount.jsp">Luca456</a></p>
                            </div>
                            <div class="col-md-2">
                              	<label>Rating:</label>
                            </div>
                            <div class="col-md-3">
                        		<p>4/5</p>
                            </div>
                        </div>
                        <div class="row">

                            <div class="col-md-12">
                        		<p>4/5 Fantasyivdkbnòekfnbjearnbòerjknrbòenbkà
                        		erkelnlknklbtrbàkltrà
                        		kbnòktrnbàlktrq
                        		bkjntrbltkrnblktr
                        		fbnlkltrblkrtnwbtrh</p>
                            </div>
                        </div>
                 	</div></div>
                 </div>
                 
                 <div class="card">
				<div class="card-header"style="font-size:20px;font-weight:600;">Received reviews</div>
				<div class="card">
  				<div class="card-body">
						<div class="row">
                        	<div class="col-md-4">
                              	<label>Reviewing user:</label>
                            </div>
                            <div class="col-md-3">
                        		<p><a href="OtherAccount.jsp">Mario123</a></p>
                            </div>
                            <div class="col-md-2">
                              	<label>Rating:</label>
                            </div>
                            <div class="col-md-3">
                        		<p>3/5</p>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-12">
                        		<p>4/5 Fantasyivdkbnòekfnbjearnbòerjknrbòenbkà</p>
                            </div>
                        </div>
                 </div></div>
                 </div>
                 
			</div>
			</div></div>	
	
	<script>function toggleSideBar(){
				document.getElementById("sidebar").classList.toggle("active");
			}	
	</script>
</body>
</html>