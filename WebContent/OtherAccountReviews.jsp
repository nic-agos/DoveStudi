<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>My Account</title>
<link href="css/account.css" rel="stylesheet"/>
<link href="css/btn1.css" rel="stylesheet"/>
<link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<!-- Riga dopo è in conflitto con le tab -->
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
<script src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script src="https://code.jquery.com/jquery-3.5.0.js"></script>

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
		 	<li><a href="AccountMyReservations.jsp">My Reservations</a></li>
		 	<li><a href="AccountMyReviews.jsp">My Reviews</a></li>
		 	<li><a href="AccountMyRooms.jsp">My Rooms</a></li>
		 	<li><a href="PostRoom.jsp">Post a Room</a></li>
		 	<li><a href="index.jsp">Log out</a></li>
		 </ul>
	</div>
	
	<div class="d-flex justify-content-xl-left" style="margin-left:330px;">
		<div class="col-md-5">
			<h1 style="font-weight:600; font-family:sans-serif;margin-top:50px; margin-left:0px;">My Account</h1>
		</div>
		<div class="col-md-6" style="margin-top:20px;">
			<p class="profile-rating">HOST RATE : <span>8/10</span> </p>
			<p class="profile-rating">GUEST RATE : <span>8/10</span> </p>
		</div>
	</div>
	
	<div class="row">
		<ul class="nav nav-tabs" id="myTab" role="tabList" style="margin-left:300px; margin-top:30px;">
			<li class="nav-item">
				<a class="nav-link" id="home-tab"href="OtherAccount.jsp" >Info</a>
			</li>
			<li class="nav-item">
              	<a class="nav-link active" id="profile-tab" href="OtherAccountReviews.jsp">Reviews</a>
            </li>
			
		</ul>
	</div>
	
	<div class="card" style="width:630px; margin-left:280px; max-height:200px;">
		<div class="row" style="margin-left:20px;margin-top:20px;">
			<div class="col-md-3">
				<label>Reviewed from:</label>
			</div>
			<div class="col-md-12">
				<p>Mario97
			</div>
		</div>
		<div class="row" style="margin-left:20px;">
			<div class="col-md-2">
				<label>Date:</label>
			</div>
			<div class="col-md-3">
				<p>03/03/2020
			</div>
			<div class="col-md-2">
				<label>Rating:</label>
			</div>
			<div class="col-md-3">
				<p>4
			</div>
		</div>
		<div class="row" style="margin-left:20px;">
			<div class="col-md-12">
				<p style="margin-left:20px;">fbqkfllllllllllllllllllllllllllllllllllllllllllllavkbbnkdkbf
			</div>
		</div>
	</div>
	<div style="position:absolute;top:0px;right:0px;width:25%;height:100%;">
	     <div class="row">
	     	<button class="btn btn-outline-warning" id="btn" style="margin-top:30px;"data-toggle="modal" data-target="#makeAReviewModal">Make a Review</button>
	     </div>
	</div>
	
		<!--Review Form Modal -->
	<div class="modal fade bd-example-modal-lg" id="makeAReviewModal" tabindex="-1" role="dialog" aria-labelledby="makeAReviewModal" aria-hidden="true">
	  <div class="modal-dialog modal-lg" role="document">
	    <div class="modal-content">
	      <div class="modal-header">
	      	<div class="row">
		      	<div class="col-md-7">
		        	<h5 class="modal-title" id="exampleModalLongTitle">You are reviewing:</h5> 
		        </div>
		        <div class="col-md-7">
		        	<h5>Reservation ID:</h5>
		        </div>
		    </div>   
		    <br>
		    <div class="row">
		      	<div class="col-md-8">
		        	<h5 class="modal-title" id="exampleModalLongTitle">Mario78</h5> 
		        </div>
		        <div class="col-md-5">
		        	<h5>006547349</h5>
		        </div>
		    </div>   
	        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
	          <span aria-hidden="true">&times;</span>
	        </button>
	      </div>
	      <div class="modal-body">
	      	<form>
          		<div class="form-group">
	      			<div class="row">
	      				<div class="col-md-2">
            				<label for="title" class="col-form-label">Title:</label>
            			</div>
            			<div class="col-md-8">
            				<input type="text" class="form-control" id="title">
            			</div>
            		</div>
            		<div class="row">
	      				<div class="col-md-11">
            				<textarea class="form-control" id="roomDescription" rows="3" style="max-height:100px; margin-top:20px;" placeHolder="Write a review"></textarea>
            			</div>        
            		</div>
            		<div class="row" style="margin-top:20px;">
	      				<div class="col-md-2">
            				<label for="title" class="col-form-label">Rating:</label>
            			</div>
            			<div class="col-md-8">
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
          		</div>
	      	</form>
	      </div>
	      <div class="modal-footer">
	        <button type="button" class="btn btn-secondary" data-dismiss="modal">Cancel</button>
	        <button type="button" id="btn"class="btn btn-outline-warning"><a href="AccountMyReviews.jsp">Post Review</a></button>
	      </div>
	    </div>
	  </div>
	</div>
	
	<script>function toggleSideBar(){
				document.getElementById("sidebar").classList.toggle("active");
			}	
	</script>
</body>
</html>