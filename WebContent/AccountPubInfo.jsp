<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="logic.model.Person"%>

<%
	Person person = (Person)session.getAttribute("accPerson");
	
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>My Account</title>
<link href="css/account.css" rel="stylesheet"/>
<link href="css/btn1.css" rel="stylesheet"/>
<link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<script src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<!-- <script src="https://code.jquery.com/jquery-3.5.0.js"></script> -->

<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
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
		 	<li><a href="SearchRooms.jsp">Search for Rooms</a></li>
		 	<li><a href="AccountPubInfo.jsp">My Account</a></li>
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
			<p class="profile-rating">HOST RATING : <span><%=person.getHostRating()%></span> </p>
			<p class="profile-rating">GUEST RATING : <span><%=person.getHostRating()%></span> </p>
		</div>
	</div>
	
	<div class="row">
		<ul class="nav nav-tabs" id="myTab" role="tabList" style="margin-left:300px; margin-top:30px;">
			<li class="nav-item">
				<a class="nav-link active" href="AccountPubInfo.jsp" >Public Info</a>
			</li>
			<li class="nav-item">
              	<a class="nav-link" href="AccountPersInfo.jsp">Personal Info</a>
            </li>
			
		</ul>
	</div>
	
	<div class="card" style="width:630px; margin-left:280px;">
		<div class="row" style="margin-left:20px;margin-top:20px;">
			<div class="col-md-6">
				<label>Username:</label>
			</div>
			<div class="col-md-12">
				<p><%=person.getUsername()%>
			</div>
		</div>
		<div class="row" style="margin-left:20px;">
			<div class="col-md-6">
				<label>Email:</label>
			</div>
			<div class="col-md-12">
				<p><%=person.getAccount().getEmail()%>
			</div>
		</div>
		<div class="row" style="margin-left:20px;">
			<div class="col-md-6">
				<label>Birthdate:</label>
			</div>
			<div class="col-md-12">
				<p><%=person.getAccount().getDateBirth()%>
			</div>
		</div>
		<div class="row" style="margin-left:20px;">
			<div class="col-md-6">
				<label>Studygrade:</label>
			</div>
			<div class="col-md-12">
				<p><%=person.getStudyGrade()%>
			</div>
		</div>
		<div class="row" style="margin-left:20px;">
			<div class="col-md-6">
				<label>School:</label>
			</div>
			<div class="col-md-12">
				<p><%=person.getSchool()%>
			</div>
		</div>
	</div>
	
	 <div style="position:absolute;top:0px;right:0px;width:25%;height:100%;">
		 <div class="row">
	     	<button class="btn btn-outline-warning" type="button"id="btn" style="margin-top:50px;"data-toggle="modal" data-target="#buyTokens">Buy Tokens</button>
	     </div>
	     <div class="row">
			<button class="btn btn-outline-warning" id="btn"style="margin-top:30px;"><a href="PostRoom.jsp">Post Room</a></button>
		</div>
    </div>
	
		<!-- Buy Tokens Modal -->
	<div class="modal fade bd-example-modal-lg" id="buyTokens" tabindex="-1" role="dialog" aria-labelledby="buyTokens" aria-hidden="true">
	  <div class="modal-dialog modal-lg" role="document">
	    <div class="modal-content">
	      <div class="modal-header">
	      	<img src="https://www.paypalobjects.com/webstatic/en_US/i/buttons/PP_logo_h_100x26.png" alt="PayPal" />
	        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
	          <span aria-hidden="true">&times;</span>
	        </button>
	      </div>
	      <div class="modal-body">
	      	<form>
          		<div class="form-group">
	      			<div class="row" style="margin-left:30px;">
            			
            				<p>If you have finished your tokens you can buy them here through PayPal! Each token costs 2$
            			
            		</div>
            		
            		<div class="row" style="margin-top:20px; margin-left:30px;">
            			<div class="col-md-10">
            				<select class="custom-select" id="inputGroupSelect01">
							    <option selected>Select the number of tokens you want to buy:</option>
							    <option value="1">1 at 2$</option>
							    <option value="2">2 at 4$</option>
							    <option value="3">3 at 6$</option>
							    <option value="4">4 at 8$</option>
							    <option value="5">5 at 10$</option>
							    <option value="1">6 at 12$</option>
							    <option value="2">7 at 14$</option>
							    <option value="3">8 at 16$</option>
							    <option value="4">9 at 18$</option>
							    <option value="5">10 at 20$</option>
							</select>
            		</div>
            		<div class="row" style="margin-left:60px;margin-top:15px;">
            			<a data-toggle="modal" data-target="#payment" href="#" style="margin-top:10px; color:#ff5500;">Or buy 5 tokens at 5$!</a>
            			<h3><span class="badge badge-secondary">Offer</span></h3>
            		</div>
          		</div>
          		</div>
	      	</form>
	      </div>
	      <div class="modal-footer">
	        <button type="button" class="btn btn-secondary" data-dismiss="modal">Cancel</button>
	        <button type="button" id="btn"class="btn btn-outline-warning" data-toggle="modal" data-target="#payment">Go to payment</a></button>
	      </div>
	    </div>
	  </div>
	</div>
	
		<!-- Payment Modal -->
	<div class="modal fade bd-example-modal-lg" id="payment" tabindex="-1" role="dialog" aria-labelledby="payment" aria-hidden="true">
	  <div class="modal-dialog modal-lg" role="document">
	    <div class="modal-content">
	      <div class="modal-header">
	      	<img src="https://www.paypalobjects.com/webstatic/en_US/i/buttons/PP_logo_h_100x26.png" alt="PayPal" />
	        <h5 style="margin-left:40px;">Select a payment type:</h5>
	       <button type="button" class="close" data-dismiss="modal" aria-label="Close">
	          <span aria-hidden="true">&times;</span>
	        </button>
	      </div>
	      <div class="modal-body">
	      	<!-- Set up a container element for the button -->
    		<div id="paypal-button-container"></div>

    		<!-- Include the PayPal JavaScript SDK -->
    		<script src="https://www.paypal.com/sdk/js?client-id=sb&currency=USD"></script>
    		<script>
		        // Render the PayPal button into #paypal-button-container
		        paypal.Buttons().render('#paypal-button-container');
		    </script>
	      </div>
	      <div class="modal-footer">
	        <button type="button" class="btn btn-secondary" data-dismiss="modal">Cancel</button>
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