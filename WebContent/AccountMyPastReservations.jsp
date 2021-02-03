<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>My Past Reservations</title>
<link href="css/reservations.css" rel="stylesheet"/>
<link href="css/btn1.css" rel="stylesheet"/>
<link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<script src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script src="https://code.jquery.com/jquery-3.5.0.js"></script>

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
			<div class="toggle-btn" onclick="toggleSideBar(); togglePage();toggleTopBar();">
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
		 	<li><a href="index.jsp">Log out</a></li>
		 </ul>
	</div>
	
	<div class="container" style="text-align:center; height:200px;">	
		<div class="container" style="text-align:center; margin-top:20px; font-weight:600;">
  		<div class="vertical-center">
    		<h1 style="font-weight:600;">My Reservations</h1>
  		</div>
	</div>
		<div class="row" id="tab">
			<ul class="nav nav-tabs" id="myTab" role="tablist">
					<li class="nav-item">
                    	<a class="nav-link"href="AccountMyFutReservations.jsp" role="tab" aria-controls="home" aria-selected="false">Future Reservations</a>
                    </li>
                    <li class="nav-item">
                     	<a class="nav-link active" href="AccountMyPastReservations.jsp" role="tab" aria-controls="profile" aria-selected="true">Past Reservations</a>
                    </li>       
			</ul>
		</div>
	</div>	
	<div class= "container head-profile" style="margin-top:120px;">
		<div class="col-md-10">
			<div class="card">
  				<div class="card-body">
						<div class="row"id="line">
	                        	<div class="col-md-2">
	                        		<label>Host:</label>
	                        	</div>
	                        	<div class="col-md-4">
	                        		<p><a href="OtherAccount.jsp">Mario78</a>
	                        	</div>
								<div class="col-md-2">
									<label>Reservation ID</label>
								</div>
								<div class="col -md-4">
									<p>006547349
								</div>
						</div>
						<div class="row"id="line">
								<div class="col-md-2">
									<label>Address:</label>
								</div>
	                            <div class="col-md-7">
	                        		<p>Via Nomentana 23</p>
	                            </div>
	                    </div>
	                    <div class="row" id="line">
	                        	<div class="col-md-2">
	                        		<label>Description:</label>
	                        	</div>
	                            <div class="col-md-7">
	                        		<p>Room description fdjbglkjebgvlfjebvljfebvlfjdbvlfjdvbfv</p>
	                            </div>
	                    </div>
                        <div class="row"id="line">
	                        	<div class="col-md-2">
	                        		<label>CAP:</label>
	                        	</div>
	                        	<div class="col-md-4">
	                        		<p>00019
	                        	</div>
								<div class="col-md-2">
									<label>Date:</label>
								</div>
								<div class="col -md-4">
									<p>12/12/2021
								</div>
						</div>
						<div class="row"id="line">
	                        	<div class="col-md-2">
	                        		<label>Start time:</label>
	                        	</div>
	                        	<div class="col-md-4">
	                        		<p>15:00
	                        	</div>
								<div class="col-md-2">
									<label>End time:</label>
								</div>
								<div class="col -md-4">
									<p>19:00
								</div>
						</div>
						<div class="row"id="line">
	                        	<div class="col-md-2">
	                        		<label>Participants:</label>
	                        	</div>	                        	
	                        		<p><a href="OtherAccount.jsp">Mario98, </a> 
	                        		<p><a href="OtherAccount.jsp">Luca.p</a>	                        	
						</div>
    				<button id="btn" class="btn btn-outline-warning" data-toggle="modal" data-target="#makeAReviewModal"><a>Make a Review</a></button>    		
    				
           		</div>
           </div>
    				
  		</div>
	</div>
	
		<!-- Modal -->
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