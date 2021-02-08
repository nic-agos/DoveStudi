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
	
	RoomController rContr = RoomController.getInstance();
	ReservationController resContr = ReservationController.getInstance();
	
	AccountBean accBean = new AccountBean();
	RoomBean tempRoomBean = new RoomBean();
	RoomBean roomBean = new RoomBean();
	PersonBean persBean = new PersonBean();
	List<Room> roomsList = new ArrayList<>();
	
//	check if the user is logged	
	if(person != null){
		
		try{
	accBean.setCf(person.getAccount().getCf());
	roomsList = rContr.getMyRooms(accBean);
	
	if(!roomsList.isEmpty()) {
		
//				adding participnats list to every room				
		for(Room r : roomsList) {
	
	tempRoomBean.setId(r.getId());
	r.setParticipants(resContr.getAllRoomParticipants(tempRoomBean));
	
		}

		request.setAttribute("roomsList", roomsList);
	}

		
		}catch(DatabaseException de){
	de.printStackTrace();
		}

//		method to handle click on delete room
		for(Room r : roomsList){
	
	if(request.getParameter(String.valueOf(r.getId())) != null){
		
		try {
	roomBean.setId(r.getId());
	rContr.deleteRoom(roomBean);
	
//					redirect
	String site = new String("AccountMyRooms.jsp");
		    response.setStatus(response.SC_MOVED_TEMPORARILY);
		    response.setHeader("Location", site);
	
		}catch(DatabaseException de){
	de.printStackTrace();
		}
	}
		}

//		method to handle click on room participant
		for(Room r: roomsList){
		
//			iterate over room's participants
	for(Person p: r.getParticipants()){
	
		if(request.getParameter(p.getUsername()) != null){
									    	
	persBean.setUsername(p.getUsername());
	session.setAttribute("othAccUsername", persBean);
	
//					redirect								
	String site = new String("OtherAccount.jsp");
	        response.setStatus(response.SC_MOVED_TEMPORARILY);
	        response.setHeader("Location", site);
		}
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
		 	<li><a href="Logout.jsp">Log out</a></li>
		 </ul>
	</div>
	<div class="container" style="text-align:center; margin-top:20px; font-weight:600;">
  		<div class="vertical-center">
    		<h1 style="font-weight:600;">My Rooms</h1>
  		</div>
	</div>
	
	<div class= "container head-profile" style="margin-bottom:30px;margin-top:70px;">	
		<c:forEach items ="${roomsList}" var="roomsList">	
			<div class="col-md-9">
				<div class="card">         
	  				<div class="card-header" id="myCard2" style="font-weight:600; font-size:20px;"> <c:out value = "${roomsList.name}"/>	</div>
	  					<div class="card-body">
							<div class="row" id="line">
	                        	<div class="col-md-2">
	                        		<label>Description:</label>
	                        	</div>
	                            <div class="col-md-7">
	                        		<p>${roomsList.specification.description}</p>
	                            </div>
	                        </div>
							<div class="row"id="line">
								<div class="col-md-2">
									<label>Address:</label>
								</div>
	                            <div class="col-md-7">
	                        		<p><c:out value = "${roomsList.address}"/></p>
	                            </div>
	                        </div>
	                        <div class="row"id="line">
	                        	<div class="col-md-2">
	                        		<label>CAP:</label>
	                        	</div>
	                        	<div class="col-md-4">
	                        		<p>${roomsList.specification.cap}
	                        	</div>
								<div class="col-md-2">
									<label>Date:</label>
								</div>
								<div class="col -md-4">
									<p>${roomsList.specification.date}
								</div>
							</div>
							<div class="row"id="line">
	                        	<div class="col-md-2">
	                        		<label>Start time:</label>
	                        	</div>
	                        	<div class="col-md-4">
	                        		<p>${roomsList.specification.startTime}
	                        	</div>
								<div class="col-md-2">
									<label>End time:</label>
								</div>
								<div class="col -md-4">
									<p>${roomsList.specification.endTime}
								</div>
							</div>
							<div class="row"id="line">
	                        	<div class="col-md-2">
	                        		<label>Max seats:</label>
	                        	</div>
	                        	<div class="col-md-4">
	                        		<p>${roomsList.numParticipants}
	                        	</div>
								<div class="col-md-3">
									<label>Available seats:</label>
								</div>
								<div class="col -md-4">
									<p>${roomsList.numAvailableSeats}
								</div>
							</div>
							<div class="row"id="line">
	                        	<div class="col-md-2">
	                        		<label>Participants:</label>
	                        	</div>	                        	
	                        		<c:forEach items="${roomsList.participants}" var="person">	           
	                        			<form method="get">
	                        				<button type="submit" style="border:none;backgroup:#ffffff" id="${person.username}" name="${person.username}">${person.username}</button>
	                        				&nbsp
	                        			</form>	                  
	               					</c:forEach>	                            	
							</div>
	                   	</div>
	                   		<form method="get">
								<button type="submit" id="${roomsList.id}" name="${roomsList.id}" class="btn btn-outline-warning" style="margin-left:20px;margin-bottom:20px;">Delete Room</a></button>    		
							</form>
	        		</div>
	     		</div>	
    	</c:forEach>				
	</div>
	
	<script>function toggleSideBar(){
				document.getElementById("sidebar").classList.toggle("active");
			}	
	</script>
	
</body>
</html>