<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix = "c"%> 

<%@ page import="java.util.*"%>

<%@ page import="logic.model.*"%>
<%@ page import="logic.bean.*"%>
<%@ page import="logic.exception.*"%>
<%@ page import="logic.controller.*"%>

<jsp:useBean id="roomBean" scope="request" class="logic.bean.RoomBean"/>
<jsp:useBean id="roomSpecBean" scope="request" class="logic.bean.RoomSpecBean"/>
<jsp:setProperty name="roomBean" property="*"/>
<jsp:setProperty name="roomSpecBean" property="*"/>

<%
	RoomController rContr = RoomController.getInstance(); 

	Person person = (Person)session.getAttribute("accPerson");

//	check if the user is logged
	if(person != null){
		
		boolean res;
		
		if (request.getParameter("confirmBtn")!= null) {
			
			try {

//				check if all data are correct
				roomBean.validate();
				roomSpecBean.validate();
				
				roomBean.setOwner(person.getAccount().getCf());
				rContr.postRoom(roomBean, roomSpecBean);
						
				String site = new String("AccountMyRooms.jsp");
			    response.setStatus(response.SC_MOVED_TEMPORARILY);
			    response.setHeader("Location", site);
			
			}catch(RoomException re){
				out.println("<div class=\"alert alert-info\" style=\" text-align:center;position: fixed; bottom: 5px;left:2%;width: 96%;\"role=\"alert\"><strong>"+re.getMessage()+"</strong><button type=\"button\" class=\"close\" data-dismiss=\"alert\"aria-label=\"Close\"> <span aria-hidden=\"true\">&times;</span></button></div>");
			
			}catch(DatabaseException de){
				out.println("<div class=\"alert alert-info\" style=\" text-align:center;position: fixed; bottom: 5px;left:2%;width: 96%;\"role=\"alert\"><strong>"+de.getMessage()+"</strong><button type=\"button\" class=\"close\" data-dismiss=\"alert\"aria-label=\"Close\"> <span aria-hidden=\"true\">&times;</span></button></div>");
			}
		}
		
	}else{
		
		String site = new String("Login.jsp");
        response.setStatus(response.SC_MOVED_TEMPORARILY);
        response.setHeader("Location", site);
	}	
	
%>

<!DOCTYPE html>
<html>
<head>

<meta charset="ISO-8859-1">
<title>Post a Room</title>

<link href="css/postRoom.css" rel="stylesheet"/>
<link href="css/sidebar.css" rel="stylesheet" />

<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">

<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>

</head>

<body>
	<svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 1440 319"><path fill="#FF5500" fill-opacity="1" d="M0,64L48,96C96,128,192,192,288,224C384,256,480,256,576,245.3C672,235,768,213,864,181.3C960,149,1056,107,1152,106.7C1248,107,1344,149,1392,170.7L1440,192L1440,320L1392,320C1344,320,1248,320,1152,320C1056,320,960,320,864,320C768,320,672,320,576,320C480,320,384,320,288,320C192,320,96,320,48,320L0,320Z"></path></svg>
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
			<li><a href="index.jsp">Log out</a></li>
		</ul>
		</div>
		<h1 style="font-family: sans-serif; font-weight:600;">Post a Room</h1>
		<div class="card" id="card">
			<form id="form_post_room" method="POST">
				<div class="row">
					<div class="col -md-8">
		  				<div class="form-group">
		    				<label for="name">Name</label>
		    					<input type="text" class="form-control" id="name" name="name" required>
		  				</div>
		  			</div>
		  			<div class="col -md-8">
		  				<div class="form-group">
		    				<label for="address">Address</label>
		    					<input type="text" class="form-control" id="address" name="address" required>
		  				</div>
	  				</div>
	  			</div>
	  			<div class="row">
	  				<div class="col -md-8">
		  				<div class="form-group">
		    				<label for="date">Date</label>
		    					<input type="date" id="date" name="date" placeholder="Birth date" value="" required>
						</div>
					</div>
					<div class="col -md-12">	
		  				<div class="form-group">
		    				<label for="cap">CAP</label>
		    					<input type="text" class="form-control" id="cap" name="cap" required>
		  				</div>
		  			</div>	
		  			<div class="col -md-4">	
		  				<div class="form-group">
		    				<label for="numParticipants">Max seats</label>
		    					<input type="text" class="form-control" id="numParticipants" name="numParticipants" required>
		  				</div>
		  			</div>
	  			</div>
		  				<div class="form-group">
		    				<label for="description">Description</label>
		    					<input type="text" class="form-control" id="description" name="description"  style="height:100px;" required>
		  				</div>
		  		<div class="row">
		  		<!-- Start time -->
  				<div class="col-md-6">
          			<select class="custom-select" id="startTime" name="startTime" required>					 
						<option value="07:00">07:00</option>
					    <option value="07:30">07:30</option>
					    <option value="08:00">08:00</option>
					    <option value="08:30">08:30</option>
					    <option value="09:00">09:00</option>
					    <option value="09:30">09:30</option>
					    <option value="10:00">10:00</option>
					    <option value="10:30">10:30</option>
					    <option value="11:00">11:00</option>
					    <option value="11:30">11:30</option>
					    <option value="12:00">12:00</option>
					    <option value="12:30">12:30</option>
					    <option value="13:00">13:00</option>
					    <option value="13:30">13:30</option>
					    <option value="14:00">14:00</option>
					    <option value="14:30">14:30</option>
					    <option value="15:00">15:00</option>
					    <option value="15:30">15:30</option>
					    <option value="16:00">16:00</option>
					    <option value="16:30">16:30</option>
					    <option value="17:00">17:00</option>
					    <option value="17:30">17:30</option>
					    <option value="18:00">18:00</option>
					    <option value="18:30">18:30</option>
					    <option value="19:00">19:00</option>
					    <option value="19:30">19:30</option>
					    <option value="20:00">20:00</option>
					    <option value="20:30">20:30</option>							    
					</select>
           		</div>
          		<!-- End time -->
	  			<div class="col-md-6">
          			<select class="custom-select" id="endTime" name="endTime" required>					  
					    <option value="07:30">07:30</option>
					    <option value="08:00">08:00</option>
					    <option value="08:30">08:30</option>
					    <option value="09:00">09:00</option>
					    <option value="09:30">09:30</option>
					    <option value="10:00">10:00</option>
					    <option value="10:30">10:30</option>
					    <option value="11:00">11:00</option>
					    <option value="11:30">11:30</option>
					    <option value="12:00">12:00</option>
					    <option value="12:30">12:30</option>
					    <option value="13:00">13:00</option>
					    <option value="13:30">13:30</option>
					    <option value="14:00">14:00</option>
					    <option value="14:30">14:30</option>
					    <option value="15:00">15:00</option>
					    <option value="15:30">15:30</option>
					    <option value="16:00">16:00</option>
					    <option value="16:30">16:30</option>
					    <option value="17:00">17:00</option>
					    <option value="17:30">17:30</option>
					    <option value="18:00">18:00</option>
					    <option value="18:30">18:30</option>
					    <option value="19:00">19:00</option>
					    <option value="19:30">19:30</option>
					    <option value="20:00">20:00</option>
					    <option value="20:30">20:30</option>
					    <option value="21:00">21:00</option>
					</select>
          		</div>
	  		</div>
			
			<div class="container" style="text-align:center; margin-top:70px; margin-bottom:60px;">
  				<div class="vertical-center">
  					<input type="button" name="postBtn" value="Post Room" id="postBtn" data-toggle="modal" data-target="#postRoomModal" class="btn btn-outline-warning" />
  				</div>
			</div>
			
		<!-- Modal -->
	<div class="modal fade" id="postRoomModal" tabindex="-1" role="dialog" aria-labelledby="postRoomModal" aria-hidden="true">
	  <div class="modal-dialog modal-dialog-centered" role="document">
	    <div class="modal-content">
	      <div class="modal-header">
	        <h5 class="modal-title" id="exampleModalLongTitle">Post Room</h5>
	        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
	          <span aria-hidden="true">&times;</span>
	        </button>
	      </div>
	      <div class="modal-body">
	        Are you sure you want to post this Room?
	      </div>
	      <div class="modal-footer">
	        <button type="button" class="btn btn-secondary" data-dismiss="modal">No</button>
	      	<button id="confirmBtn" name="confirmBtn" class="btn btn-success success">Yes</button>
	      </div>
	    </div>
	  </div>
	</div>

		<script>
			$('#postRoomBtn').click(function() {
			    $('#uname').text($('#username').val());
			    $('#psw').text($('#password').val());
			});
		
			$('#confirmBtn').click(function(){
			   $('#form_post_room').submit();
			});
		</script>
	
	</form>
</div>

	<script>function toggleSideBar(){
				document.getElementById("sidebar").classList.toggle("active");
			}	
	</script>

</body>
</html>