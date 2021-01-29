<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix = "c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix = "sql"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>My Groups</title>
<link href="css/myGroups.css" rel="stylesheet"/>
<link href="css/btn2.css" rel="stylesheet"/>
<link href="css/btn1.css" rel="stylesheet"/>
<link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
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
	<!-- <div class="curved">
		<svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 1440 319"><path fill="#FF5500" fill-opacity="1" d="M0,64L48,96C96,128,192,192,288,224C384,256,480,256,576,245.3C672,235,768,213,864,181.3C960,149,1056,107,1152,106.7C1248,107,1344,149,1392,170.7L1440,192L1440,320L1392,320C1344,320,1248,320,1152,320C1056,320,960,320,864,320C768,320,672,320,576,320C480,320,384,320,288,320C192,320,96,320,48,320L0,320Z"></path></svg>
	</div> -->
	<div class="container" style="text-align:center; margin-top:20px; font-weight:600;">
    		<h1 style="font-weight:600;">My Groups</h1>  		
	</div> <br/>
	
 
     
     <div class="container" style="text-align:center;">
     	<button type="button" class="btn btn-outline-warning" data-toggle="modal" data-target="#createGroup"id="btn"><a>Create Group</a></button>
     </div>
	
	<div class= "container head-profile" style="margin-bottom:30px;">
	
			<div class="col-md-10" style="margin-top:50px; margin-left:100px;">
				
				<div class="card">         
	         		<div class="card-header" id="myCard" style="font-weight: 600; font-size:20px;">Group Name</div>
	  					
	  					<div class="card-body">						
	                        <div class="row"id="line">
	                        	<div class="col-md-1">
	                        		<label>Admin:</label>
	                        	</div>
	                        	<div class="col-md-4">
	                        		<p><a href="OtherAccount.jsp">Mario72</a></p>
	                        	</div>
								<div class="col-md-3">
									<label>Number of partecipants:</label>
								</div>
								<div class="col -md-4">
									<p>3
								</div>
							</div>
							<div class="row"id="line">
	                        	<div class="col-md-2">
	                        		<label>Participants:</label>
	                        	</div>	                        	
	                        		<p><a href="OtherAccount.jsp">Mario98, </a> 
	                        		<p><a href="OtherAccount.jsp">Luca.p</a>	                        	
							</div>
	                   	</div>
	                   	<div class="row">
		                	<div class="col-md-3">
		                    	<button class="btn btn-outline-warning"data-toggle="modal" data-target="#exampleModalCenter"id="btn" style="margin-bottom:10px;margin-left:30px;"><a href="#">Leave Group</a></button>
		                	</div>
		             	</div>
	        	</div>
	        	<div class="card">         
	         		<div class="card-header" id="myCard" style="font-weight: 600; font-size:20px;">My Group Name</div>
	  					<div class="card-body">						
	                        <div class="row"id="line">
	                        	<div class="col-md-1">
	                        		<label>Admin:</label>
	                        	</div>
	                        	<div class="col-md-4">
	                        		<p><a>Me</a></p>
	                        	</div>
								<div class="col-md-3">
									<label>Number of partecipants:</label>
								</div>
								<div class="col -md-4">
									<p>3
								</div>
							</div>
							<div class="row"id="line">
	                        	<div class="col-md-2">
	                        		<label>Participants:</label>
	                        	</div>	                        	
	                        		<p><a href="OtherAccount.jsp">Mario98, </a> 
	                        		<p><a href="OtherAccount.jsp">Luca.p</a>	                        	
							</div>
	                   	</div>
	                   	<div class="row">
		                	<div class="col-md-12">
		                    	<button class="btn btn-outline-warning"id="btn" style="margin-bottom:10px;margin-left:30px;"><a href="SearchRoomsHost.jsp">Book Room</a></button>
		                    	<button class="btn btn-outline-warning"data-toggle="modal" data-target="#deleteGroupModal"id="btn" style="margin-bottom:10px;margin-left:30px;">Delete Group</button>
		                    	<!-- da vedere se mettere l'opzione di modificare il gruppo -->
								<button class="btn btn-outline-warning"data-toggle="modal" data-target="#modifyGroupModal"id="btn" style="margin-bottom:10px;margin-left:30px;">Modify Group</button>		                	
		                	</div>
		             	</div>
	        	</div>
	     	</div>	
  		
	</div>
	
		<!-- Delete Room Modal -->
<div class="modal fade" id="deleteGroupModal" tabindex="-1" role="dialog" aria-labelledby="deleteGroupModal" aria-hidden="true">
  <div class="modal-dialog modal-dialog-centered" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalLongTitle">Delete Group</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
        Are you sure you want to delete this Group?
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-dismiss="modal">No</button>
        <button type="button" id="btn"class="btn btn-outline-warning" data-toggle="modal"><a href="MyGroups.jsp">Yes</a></button>
      </div>
    </div>
  </div>
</div>

<!-- Create Group Modal -->
<div class="modal fade bd-example-modal-lg" id="createGroup" tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel" aria-hidden="true">
  <div class="modal-dialog modal-lg" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalLabel">Create Group</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
        <form>
          <div class="form-group">
            <label for="recipient-name" class="col-form-label">Group name:</label>
            <input type="text" class="form-control" id="recipient-name">
          </div>
        </form>
        <form class="d-flex">
		        <input class="form-control me-2" type="search" placeholder="Username" aria-label="Search">
		        <button class="btn btn-outline-warning" id="btn"type="submit">Add</button>
		</form>
        
    <div class="tab-content" id="nav-tabContent">
      <div class="tab-pane fade show active" id="list-home" role="tabpanel" aria-labelledby="list-home-list">...</div>
      <div class="tab-pane fade" id="list-profile" role="tabpanel" aria-labelledby="list-profile-list">...</div>
      <div class="tab-pane fade" id="list-messages" role="tabpanel" aria-labelledby="list-messages-list">...</div>
      <div class="tab-pane fade" id="list-settings" role="tabpanel" aria-labelledby="list-settings-list">...</div>
    </div>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
        <button type="button" class="btn btn-outline-warning" id="btn"><a href="MyGroups.jsp">Create Group</button>
      </div>
    </div>
  </div></div>


	
	
	
	
	<script>function toggleSideBar(){
				document.getElementById("sidebar").classList.toggle("active");
			}	
	</script>
</body>
</html>