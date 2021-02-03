<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix = "c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix = "sql"%>
<%@ page import="logic.model.dao.RoomDAOImpl"%>
<%@ page import="logic.bean.RoomBean"%>
<%@ page import="java.util.*"%>
<%@ page import="java.util.ArrayList"%>

<%
	RoomDAOImpl dao = RoomDAOImpl.getInstance();
	List<RoomBean> listRoom;
	listRoom = dao.getAllRooms();
	request.setAttribute("listRoom", listRoom);
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>My Rooms</title>
<link href="css/myRooms.css" rel="stylesheet"/>
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
	<div class="container" style="text-align:center; margin-top:20px; font-weight:600;">
  		<div class="vertical-center">
    		<h1 style="font-weight:600;">My Rooms</h1>
  		</div>
	</div>
	
	<div class= "container head-profile" style="margin-bottom:30px;">
		
		
		
		<c:forEach items ="${listRoom}" var="listRoom">
			
			<div class="col-md-9" style="margin-top:70px;">
				
				<div class="card">         
	         		<!-- <div class="card-header" id="myCard" style="background:#ff6b24;font-weight: 600; font-size:15px;"></div> -->
	  				<div class="card-header" id="myCard2" style="font-weight:600; font-size:20px;"> <c:out value = "${listRoom.name}"/>	</div>
	  					<div class="card-body">
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
									<label>Address:</label>
								</div>
	                            <div class="col-md-7">
	                        		<p><c:out value = "${listRoom.address}"/></p>
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
	                        		<label>Max seats:</label>
	                        	</div>
	                        	<div class="col-md-4">
	                        		<p>8
	                        	</div>
								<div class="col-md-3">
									<label>Available seats:</label>
								</div>
								<div class="col -md-4">
									<p>6
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
	                   	<div class="col-md-4">
		    				<button id="res-btn" data-toggle="modal" data-target="#exampleModalCenter" class="btn btn-outline-success"style="margin-bottom:10px;margin-left:20px;">Delete Room</button>
		    			</div>
		    			</div>
	        	</div>
	     	</div>
	       	
    	</c:forEach>		
  		
	</div>
	
	<!-- Delete Room Modal -->
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
        Are you sure you want to delete this room?
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-dismiss="modal">No</button>
        <button type="button" id="btn"class="btn btn-outline-warning" data-dismiss="modal"data-toggle="modal" data-target="#roomDeleted">Yes</button>
      </div>
    </div>
  </div>
</div>

<!-- Room Deleted Modal -->
<div class="modal fade" id="roomDeleted" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
  <div class="modal-dialog modal-dialog-centered" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <!-- <h5 class="modal-title" id="exampleModalLongTitle">Modal title</h5> -->
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
        Your Room has been deleted!
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