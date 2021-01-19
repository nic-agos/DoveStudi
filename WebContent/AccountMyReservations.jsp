<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>My Account</title>
<link href="css/reservations.css" rel="stylesheet"/>

<link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<script src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script src="https://code.jquery.com/jquery-3.5.0.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
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
	
	<div class= "container head-profile">
		<form method="post">
			<div class="row">
			
				<div class="col-md-4">
					<div class="profile-img">
						<img src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcS52y5aInsxSm31CvHOFHWujqUx_wWTS9iM6s7BAm21oEN_RiGoog" alt=""/>
						<div class="file btn btn-lg btn-warning">
							Change Photo
							<input type="file" name="file"/>
						</div>
					</div>
				</div>
				
				<div class="col-md-6">
					<div class="profile-head">
						<h5>My name</h5>
						<p class="profile-rating">HOST RATE : <span>8/10</span> </p>
						<p class="profile-rating">GUEST RATE : <span>8/10</span> </p>
						<p class="profile-rating">TOKENS : <span>8/10</span> </p>
					</div>
					<!-- <div class="profile-tokens">
						<p class="profile-rating">EARNED TOKENS: <span>7</span></p>
						<p class="profile-rating">SPENT TOKENS: <span>7</span></p>
					</div> -->
				</div>
				<div class="col-md-2">
                    <input type="submit" class="profile-edit-btn" name="btnAddMore" value="Edit Profile"/>
                </div>
				
			</div>
		</form>
		<div class="row" id="tab">
			<ul class="nav nav-tabs" id="myTab" role="tablist">
					<li class="nav-item">
                    	<a class="nav-link" id="home-tab" data-toggle="tab" href="AccountPersonalInfo.jsp" role="tab" aria-controls="home" aria-selected="true">About</a>
                    </li>
                    <li class="nav-item">
                     	<a class="nav-link active" id="profile-tab" data-toggle="tab" href="AccountMyReservations.jsp" role="tab" aria-controls="profile" aria-selected="false">My Reservations</a>
                    </li>
                    <li class="nav-item">
                       	<a class="nav-link" id="reservation-tab" data-toggle="tab" href="AccountMyRooms.jsp" role="tab" aria-controls="res" aria-selected="false">My Rooms</a>
                    </li>
                    <li class="nav-item">
                      	<a class="nav-link" id="reviews-tab" data-toggle="tab" href="AccountMyReviews.jsp" role="tab" aria-controls="groups" aria-selected="false">My Reviews</a>
                    </li>
				</ul>
		</div>
		
		<div class="col-md-8" style="margin-top:50px;">
			<div class="card">
  				<div class="card-body">
						<div class="row">
                        	<div class="col-md-6">
                              	<label>Host name:</label>
                            </div>
                            <div class="col-md-6">
                        		<p>Mario123</p>
                            </div>
                        </div>
                        <div class="row">
                        	<div class="col-md-6">
                              	<label>Room:</label>
                            </div>
                            <div class="col-md-6">
                        		<p>Awesome room in Rome</p>
                            </div>
                        </div>
                        <div class="row">
                        	<div class="col-md-6">
                              	<label>Date and Time:</label>
                            </div>
                            <div class="col-md-6">
                        		<p>04/03/2021 from 15.00 to 18.00</p>
                            </div>
                        </div>
                 	<button id="res-btn"><a>See Room</a></button>
    				<button type="button" id="res-btn"data-toggle="modal" data-target="#myModal"><a href="#">Delete Reservation</a></button>
    				
    				<!-- Modal -->
  <div class="modal fade" id="myModal" role="dialog">
    <div class="modal-dialog">
    
      <!-- Modal content-->
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal">&times;</button>
          <h4 class="modal-title">Modal Header</h4>
        </div>
        <div class="modal-body">
          <p>Some text in the modal.</p>
        </div>
        <div class="modal-footer">
          <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
        </div>
      </div>
      
    </div>
  </div>
    				
                 </div>
                 </div>
    				
  				</div>
			</div>
		
	
	<div class="curved">
		<svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 1440 319"><path fill="#FF5500" fill-opacity="1" d="M0,64L48,96C96,128,192,192,288,224C384,256,480,256,576,245.3C672,235,768,213,864,181.3C960,149,1056,107,1152,106.7C1248,107,1344,149,1392,170.7L1440,192L1440,320L1392,320C1344,320,1248,320,1152,320C1056,320,960,320,864,320C768,320,672,320,576,320C480,320,384,320,288,320C192,320,96,320,48,320L0,320Z"></path></svg>
	</div>
	
	
	<script>function toggleSideBar(){
				document.getElementById("sidebar").classList.toggle("active");
			}	
	</script>
</body>
</html>