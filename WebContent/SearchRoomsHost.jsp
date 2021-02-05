<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix = "c"%> 

<%@ page import="java.util.*"%>

<%@ page import="logic.model.*"%>
<%@ page import="logic.bean.*"%>
<%@ page import="logic.exception.*"%>
<%@ page import="logic.controller.*"%>

<%
	Person person = (Person)session.getAttribute("accPerson");

%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
<title>SearchRooms</title>
<link href="css/sidebar.css" rel="stylesheet"/>
<link href="css/searchRoom.css" rel="stylesheet"/>
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

	<div class="container" style="text-align:center; margin-top:20px; font-weight:600;">
  		<div class="vertical-center">
    		<h1 style="font-weight:600;">Search Rooms</h1>
  		</div>
	</div>

	<div style="margin-left:70px; margin-right:80px;" >
		<nav class="navbar navbar-expand-lg navbar-light bg-light" style="margin-left:230px;">
		  <div class="container-fluid">
		    <a class="navbar-brand" style="font-weight:600;">Search by: </a>
		    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
		    	<span class="navbar-toggler-icon"></span>
		    </button>
		    <div class="collapse navbar-collapse" id="navbarSupportedContent">
		      <ul class="navbar-nav me-auto mb-2 mb-lg-0">
		        <li class="nav-item" style="margin-left:50px;">
		          <a class="nav-link active" aria-current="page" href="SearchRoomsHost.jsp">Host name</a>
		        </li>
		        <li class="nav-item">
		          <a class="nav-link" href="SearchRoomsCAP.jsp">CAP</a>
		        </li>
		        <li class="nav-item">
		          <a class="nav-link" href="SearchRoomsDate.jsp">Date</a>
		        </li>
		        <li class="nav-item">
		          <a class="nav-link" href="SearchRoomsAvPl.jsp">Available places</a>
		        </li>
		      </ul>
		      <form class="d-flex">
		        <input class="form-control me-2" type="search" placeholder="Username" aria-label="Search">
		        <button class="btn btn-outline-warning" id="btn"type="submit">Search</button>
		      </form>
		    </div>
		  </div>
		</nav>
	</div> 
	
	<div class="card w-75" style="margin-left:300px; margin-right:80px;">
  		<div class="card-body">
    		<h5 class="card-title" style="font-weight:550;">Room name</h5>
    		<div class="row" id="line">
    			<div class="col-md-1">
    				<label>Host:</label>
    			</div>
    			<div class="col-md-6">
    				<p><a href="OtherAccount.jsp">Mario98</a>
    			</div>
    		</div>
    		<p class="card-text">Room description</p>
    		<div class="row"id="line">
    			<div class="col-md-1">
					<label>Address:</label>
				</div>
				<div class="col-md-3 ">
	            	<p>Via Nomentana 23</p>
	            </div>
	            <div class="col-md-2">
	            	<label>CAP:</label>
	          	</div>
	          	<div class="col-md-2">
	            	<p>00019</p>
	            </div>
	            <div class="col-md-1">
	            	<label>Date:</label>
	          	</div>
	            <div class="col-md-2">
	             	<p>22/02/2021
	            </div>
    		</div>
    		<div class="row"id="line">
	        	<div class="col-md-2">
	            	<label>Start time:</label>
	          	</div>
	            <div class="col-md-2">
	             	<p>15:00
	            </div>
				<div class="col-md-2">
					<label>End time:</label>
				</div>
				<div class="col -md-3">
					<p>19:00
				</div>
				<div class="col-md-2">
    				<label>Available places:</label>
    			</div>
    			<div class="col-md-2">
    				<p>6</p>
    			</div>
			</div>
			<div class="row"id="line">
	        	<div class="col-md-2">
	         		<label>Participants:</label>
	           	</div>	                        	
	                <p><a href="OtherAccount.jsp">Mario98, </a> 
	          		<p><a href="OtherAccount.jsp">Luca.p</a>	                        	
			</div>
    		<button id="btn" type="button"class="btn btn-outline-warning" data-toggle="modal" data-target="#exampleModalCenter">Book Room</button>
 		 </div>
	</div>

<!-- Modal -->
<div class="modal fade" id="exampleModalCenter" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
  <div class="modal-dialog modal-dialog-centered" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <!-- <h5 class="modal-title" id="exampleModalLongTitle">Modal title</h5> -->
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
        Are you sure you want to book this room?
		You will spend 1 token
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-dismiss="modal">No</button>
        <button type="button" id="btn"class="btn btn-outline-warning"><a href="AccountMyFutReservations.jsp">Yes</a></button>
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