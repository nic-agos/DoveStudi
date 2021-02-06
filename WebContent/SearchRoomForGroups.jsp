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
	Group group = (Group)session.getAttribute("groupBook");
	
	GroupController gContr = GroupController.getInstance();
	RoomController rContr = RoomController.getInstance();
	ReservationController resContr = ReservationController.getInstance();
	
	RoomBean tempRoomBean = new RoomBean();
	GroupBean groupBean = new GroupBean();
	RoomBean roomBean = new RoomBean();
	
	PersonBean persBean = new PersonBean();
	
	List<Room> roomsList = new ArrayList<>();
	RoomBean rBean = new RoomBean();
	
	try{
		
		rBean.setNumAvailableSeats(group.getNumPartecipants());
		
		roomsList = rContr.searchRoomByAvailableSeats(rBean);
		
		if(!roomsList.isEmpty()){
			
			for(Room r : roomsList) {
				
				tempRoomBean.setId(r.getId());
				r.setPartecipants(resContr.getAllRoomPartecipants(tempRoomBean));
				
			}
			
			request.setAttribute("roomsList", roomsList);
		}	
	
	}catch(DatabaseException de){
		de.printStackTrace();
	}
	
//	method to handle click on room owner
	for(Room room : roomsList){
		
		if(request.getParameter(room.getOwner().getPerson().getUsername()) != null){
			
			persBean.setUsername(room.getOwner().getPerson().getUsername());
			session.setAttribute("othAccUsername", persBean);
				
			String site = new String("OtherAccount.jsp");
		    response.setStatus(response.SC_MOVED_TEMPORARILY);
		    response.setHeader("Location", site);
		    
		}
	}

//	method to handle click on book room
	for(Room ro : roomsList){
		
		if(request.getParameter(String.valueOf(ro.getId())) != null) {
				
			if(person != null){
					
					try{
						
						roomBean.setId(ro.getId());
						groupBean.setName(group.getName());
						groupBean.setAdmin(group.getAdmin().getCf());
						gContr.bookRoomGroup(groupBean, roomBean);
						
						String site = new String("AccountMyFutReservations.jsp");
				        response.setStatus(response.SC_MOVED_TEMPORARILY);
				        response.setHeader("Location", site);
					
					}catch(DatabaseException de){
						de.printStackTrace();
					
					}catch(RoomException re){
						re.printStackTrace();
					
					}catch(AccountException ae){
						ae.printStackTrace();
						
					}catch(ReservationException ree){
						ree.printStackTrace();
					}
					
			}else{
				String site = new String("Login.jsp");
		        response.setStatus(response.SC_MOVED_TEMPORARILY);
		        response.setHeader("Location", site);
			}
		}
	}

//	method to handle click on room partecipant
	for(Room r: roomsList){
			
//		iterate over room's partecipants
		for(Person p: r.getPartecipants()){
				
			if(request.getParameter(p.getUsername()) != null){
														    	
				persBean.setUsername(p.getUsername());
				session.setAttribute("othAccUsername", persBean);
							
				String site = new String("OtherAccount.jsp");
			    response.setStatus(response.SC_MOVED_TEMPORARILY);
			    response.setHeader("Location", site);
			}
		}
	}
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
<link href="css/btn1.css" rel="stylesheet"/>
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
    		<h1 style="font-weight:600;">Rooms for your group</h1>
  		</div>
	</div>
 
	<c:forEach items="${roomsList}" var="roomsList">
	<div class="card w-75" style="margin-left:300px; margin-right:80px;">
  		<div class="card-body">
    		<h5 class="card-title" style="font-weight:550;">${roomsList.name}</h5>
    		<div class="row" id="line">
    			<div class="col-md-1">
    				<label>Host:</label>
    			</div>
    			<div class="col-md-6">
    				<form method="get">
	                	<button type="submit" style="border:none;backgroup:#ffffff" id="${roomsList.owner.person.username}" name="${roomsList.owner.person.username}">${roomsList.owner.person.username}</button>
	                </form>
    			</div>
    		</div>
    		<p class="card-text">${roomsList.specification.description}</p>
    		<div class="row"id="line">
    			<div class="col-md-1">
					<label>Address:</label>
				</div>
				<div class="col-md-3 ">
	            	<p>----------</p>
	            </div>
	            <div class="col-md-2">
	            	<label>CAP:</label>
	          	</div>
	          	<div class="col-md-2">
	            	<p>${roomsList.specification.cap}</p>
	            </div>
	            <div class="col-md-1">
	            	<label>Date:</label>
	          	</div>
	            <div class="col-md-2">
	             	<p>${roomsList.specification.date}
	            </div>
    		</div>
    		<div class="row"id="line">
	        	<div class="col-md-2">
	            	<label>Start time:</label>
	          	</div>
	            <div class="col-md-2">
	             	<p>${roomsList.specification.startTime}
	            </div>
				<div class="col-md-2">
					<label>End time:</label>
				</div>
				<div class="col -md-3">
					<p>${roomsList.specification.endTime}
				</div>
				<div class="col-md-2">
    				<label>Available places:</label>
    			</div>
    			<div class="col-md-2">
    				<p>${roomsList.numAvailableSeats}</p>
    			</div>
			</div>
			<div class="row"id="line">
	        	<div class="col-md-2">
	         		<label>Participants:</label>
	           	</div>	                        	
	            	<c:forEach items="${roomsList.partecipants}" var="person">	           
	                        	<form method="get">
	                        			<button type="submit" style="border:none;backgroup:#ffffff" id="${person.username}" name="${person.username}">${person.username}</button>
	                        			&nbsp
	                        	</form>	                  
	               	</c:forEach>	                        	
			</div>
    		<form method="get">
				<button type="submit" id="${roomsList.id}" name="${roomsList.id}" class="btn btn-outline-warning">Book Room</button>    		
			</form>
 		 </div>
	</div>
	 </c:forEach>
	
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