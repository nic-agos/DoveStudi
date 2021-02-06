<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Review Form</title>
<link href="css/btn1.css" rel="stylesheet"/>
<link href="css/sidebar.css" rel="stylesheet" />
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
</head>
<body>
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
		 	<li><a href="SearchRooms.jsp">Search for Rooms</a></li>
		 	<li><a href="AccountPubInfo.jsp">My Account</a></li>
		 	<li><a href="MyGroups.jsp">My Groups</a></li>
		 	<li><a href="AccountMyFutReservations.jsp">My Reservations</a></li>
		 	<li><a href="AccountMyReviews.jsp">My Reviews</a></li>
		 	<li><a href="AccountMyRooms.jsp">My Rooms</a></li>
		 	<li><a href="PostRoom.jsp">Post a Room</a></li>
		 	<li><a href="Logout.jsp">Log out</a></li>
		 </ul>
	</div>
	<div class="container" style="text-align:center; height:80px;">	
		<div class="container" style="text-align:center; margin-top:20px; font-weight:600;">
	  		<div class="vertical-center">
	    		<h1 style="font-weight:600;">Review Form</h1>
	  		</div>
		</div>
	</div>
	<div class="card" id="card" style="width:600px; left:30%;margin-top:20px;">
			<form id="form_post_room" method="POST">
		  		<div class="row"style="margin-left:20px;margin-top:20px;">
					<div class="col -md-4">
		  				<label for="resID">You are reviewing:</label>
		  			</div>
		  			<div class="col-md-6">
		    			<p>Reviewed user
		  			</div>
		  		</div>
		  		<div class="row"style="margin-left:20px;margin-top:10px;">
		  			<div class="col-md-6">
            				<label for="title" class="col-form-label">You are reviewing as:</label>
            			</div>
            			<div class="col-md-5">
            				<select class="custom-select" id="inputGroupSelect01">							
							    <option value="1">Host</option>
							    <option value="2">Guest</option>
							</select>
            			</div>
		  		</div>
		  		<div class="row"style="margin-left:20px;margin-top:10px;">
	      				<div class="col-md-2">
            				<label for="title" class="col-form-label">Title:</label>
            			</div>
            			<div class="col-md-9">
            				<input type="text" class="form-control" id="title">
            			</div>
            		</div>
            		<div class="row"style="margin-left:20px;margin-top:10px;">
	      				<div class="col-md-11">
            				<textarea class="form-control" id="roomDescription" rows="3" style="max-height:100px; margin-top:20px;" placeHolder="Write a review"></textarea>
            			</div>        
            		</div>
            		<div class="row" style="margin-top:10px; margin-left:20px;">
	      				<div class="col-md-2">
            				<label for="title" class="col-form-label">Rating:</label>
            			</div>
            			<div class="col-md-9">
            				<select class="custom-select" id="inputGroupSelect01">
							    <option selected>Choose a number from 1 to 5</option>
							    <option value="1">One</option>
							    <option value="2">Two</option>
							    <option value="3">Three</option>
							    <option value="4">Four</option>
							    <option value="5">Five</option>
							</select>
            			</div>
            		</div>
		  			
		  		
			
			<div class="container" style="text-align:center; margin-top:20px; margin-bottom:60px;">
  				<div class="vertical-center">
    				<!-- <button type="submit" id="postBtn" name = "postBtn" class="btn btn-outline-warning"data-toggle="modal" data-target="#postRoomModal">Post Room</button> -->
  					<input type="button" name="reviewBtn" value="Post Review" id="reviewBtn" class="btn btn-outline-warning" />
  				</div>
			</div>
		</form>
	</div>
	
	<script>function toggleSideBar(){
				document.getElementById("sidebar").classList.toggle("active");
			}	
	</script>
</body>
</html>