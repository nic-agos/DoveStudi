<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix = "c"%> 

<%@ page import="java.util.*"%>

<%@ page import="logic.model.*"%>
<%@ page import="logic.bean.*"%>
<%@ page import="logic.exception.*"%>
<%@ page import="logic.controller.*"%>

<%
ReservationController rContr = ReservationController.getInstance();

	Person person = (Person)session.getAttribute("accPerson");

//	check if the user is logged
	if(person != null){
		
		AccountBean accBean = new AccountBean();
		PersonBean persBean = new PersonBean();
		RoomBean roomBean = new RoomBean();
		ReservationBean resBean = new ReservationBean();
		
		accBean.setCf(person.getAccount().getCf());
		
		List<Reservation> reservationsList = new ArrayList<>();
		
		try{

//			getting a list with my future reservation
			reservationsList = rContr.getMyFutureReservations(accBean);
	
			if(!reservationsList.isEmpty()) {
		
				request.setAttribute("reservationsList", reservationsList);
	
			}
		
		}catch(DatabaseException de){
			out.println("<div class=\"alert alert-info\" style=\" text-align:center;position: fixed; bottom: 5px;left:2%;width: 96%;\"role=\"alert\"><strong>"+de.getMessage()+"</strong><button type=\"button\" class=\"close\" data-dismiss=\"alert\"aria-label=\"Close\"> <span aria-hidden=\"true\">&times;</span></button></div>");
			
		}

//		method to handle clicks on room owner
		for(Reservation r : reservationsList){
	
			if(request.getParameter(r.getRoomOwner().getUsername()) != null){
		
		    	persBean.setUsername(r.getRoomOwner().getUsername());
				session.setAttribute("othAccUsername", persBean);
	
//				redirect
				String site = new String("OtherAccount.jsp");
		    	response.setStatus(response.SC_MOVED_TEMPORARILY);
			    response.setHeader("Location", site);
			}
		}
		
//		method to handle clicks on participant
		for(Reservation res : reservationsList){
	
//			iterate over room's participants
			for(Person p: res.getLinkedRoom().getParticipants()){
		
				if(request.getParameter(p.getUsername())!=null){
								    	
					persBean.setUsername(p.getUsername());
					session.setAttribute("othAccUsername", persBean);
	
//					redirect							
					String site = new String("OtherAccount.jsp");
	        		response.setStatus(response.SC_MOVED_TEMPORARILY);
	        		response.setHeader("Location", site);
				}
			}
		}
	

//		method to handle click on delete reservation
		for(Reservation res : reservationsList){
	
			if(request.getParameter(String.valueOf(res.getId())) != null) {
		
				try{
	
					resBean.setId(res.getId());
					rContr.deleteReservation(resBean);

//					redirect
					String site = new String("AccountMyFutReservations.jsp");
	        		response.setStatus(response.SC_MOVED_TEMPORARILY);
	        		response.setHeader("Location", site);
	
				}catch(DatabaseException de){
					out.println("<div class=\"alert alert-info\" style=\" text-align:center;position: fixed; bottom: 5px;left:2%;width: 96%;\"role=\"alert\"><strong>"+de.getMessage()+"</strong><button type=\"button\" class=\"close\" data-dismiss=\"alert\"aria-label=\"Close\"> <span aria-hidden=\"true\">&times;</span></button></div>");
				}catch(ReservationException re){
					out.println("<div class=\"alert alert-info\" style=\" text-align:center;position: fixed; bottom: 5px;left:2%;width: 96%;\"role=\"alert\"><strong>"+re.getMessage()+"</strong><button type=\"button\" class=\"close\" data-dismiss=\"alert\"aria-label=\"Close\"> <span aria-hidden=\"true\">&times;</span></button></div>");
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
<title>My Future Reservations</title>
<link href="css/reservations.css" rel="stylesheet"/>
<link href="css/btn1.css" rel="stylesheet"/>
<script src="https://code.jquery.com/jquery-3.5.0.js"></script> 
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
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
	
	<div class="container" style="text-align:center; height:200px;">	
		<div class="container" style="text-align:center; margin-top:20px; font-weight:600;">
  		<div class="vertical-center">
    		<h1 style="font-weight:600;">My Reservations</h1>
  		</div>
	</div>
		<div class="row" id="tab">
			<ul class="nav nav-tabs" id="myTab" role="tablist">
					<li class="nav-item">
                    	<a class="nav-link active" href="AccountMyFutReservations.jsp" role="tab" aria-controls="home" aria-selected="true">Future Reservations</a>
                    </li>
                    <li class="nav-item">
                     	<a class="nav-link" href="AccountMyPastReservations.jsp" role="tab" aria-controls="profile" aria-selected="false">Past Reservations</a>
                    </li>       
			</ul>
		</div>
	</div>	
	
	<div class= "container head-profile" style="margin-top:120px;">
		<div class="col-md-10">
			<c:forEach items="${reservationsList}" var="reservationsList">
				<div class="card">
	  				<div class="card-body">
						<div class="row"id="line">
                        	<div class="col-md-2">
								<label>Room name:</label>
							</div>
                            <div class="col-md-4">
                        		<p>${reservationsList.linkedRoom.name}</p>
                            </div>
							<div class="col-md-2">
								<label>Reservation ID</label>
							</div>
							<div class="col -md-7">
								<p>${reservationsList.id}
							</div>
						</div>
						<div class="row"id="line">
							<div class="col-md-2">
                        		<label>Host:</label>
                        	</div>
                        	<div class="col-md-4">
                        		<form method="get">
                        			<button type="submit" style="border:none;backgroup:#ffffff" id="${reservationsList.roomOwner.username}" name="${reservationsList.roomOwner.username}">${reservationsList.roomOwner.username}</button>
                        		</form>
                        	</div>								
	                    </div>
						<div class="row"id="line">
							<div class="col-md-2">
								<label>Address:</label>
							</div>
                            <div class="col-md-7">
                        		<p>${reservationsList.linkedRoom.address}</p>
                            </div>
	                    </div>
		                    <div class="row" id="line">
	                        	<div class="col-md-2">
	                        		<label>Description:</label>
	                        	</div>
	                            <div class="col-md-7">
	                        		<p>${reservationsList.linkedRoom.specification.description}</p>
	                            </div>
		                    </div>
	                        <div class="row"id="line">
	                        	<div class="col-md-2">
	                        		<label>CAP:</label>
	                        	</div>
	                        	<div class="col-md-4">
	                        		<p>${reservationsList.linkedRoom.specification.cap}
	                        	</div>
								<div class="col-md-2">
									<label>Date:</label>
								</div>
								<div class="col -md-4">
									<p>${reservationsList.date}
								</div>
							</div>
							<div class="row"id="line">
	                        	<div class="col-md-2">
	                        		<label>Start time:</label>
	                        	</div>
	                        	<div class="col-md-4">
	                        		<p>${reservationsList.startTime}
	                        	</div>
								<div class="col-md-2">
									<label>End time:</label>
								</div>
								<div class="col -md-4">
									<p>${reservationsList.endTime}
								</div>
							</div>
							<div class="row"id="line">
	                        	<div class="col-md-2">
	                        		<label>Participants:</label>
	                        	</div>	
	                        		<c:forEach items="${reservationsList.linkedRoom.participants}" var="person">	              
	                        			<form method="get">
	                        				<button type="submit" style="border:none;backgroup:#ffffff" id="${person.username}" name="${person.username}">${person.username}</button>
	                        				&nbsp
	                        			</form>	      	                   	                  
	                        		</c:forEach>                        			               	                        	
								</div>
								<form method="get">
									<button type="submit" id="${reservationsList.id}" name="${reservationsList.id}" class="btn btn-outline-warning">Delete Reservation</a></button>    		
								</form>
						</div>
	           		</div>
	    	</c:forEach>		
		</div>
	</div>
	
	<script>function toggleSideBar(){
				document.getElementById("sidebar").classList.toggle("active");
			}	
	</script>
	
	<script>
		function refreshPage(){
			window.location.reload();
		}
	</script>
	
</body>
</html>