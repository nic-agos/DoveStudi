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
	
	AccountBean accBean = new AccountBean();
	PersonBean persBean = new PersonBean();
	RoomBean roomBean = new RoomBean();
	
	accBean.setCf(person.getAccount().getCf());
	
	List<Reservation> reservationsList = new ArrayList<>();
	
	ReservationController rContr = ReservationController.getInstance();
	
	try{
		reservationsList = rContr.getMyPastReservations(accBean);

		if(!reservationsList.isEmpty()){
			
			for(Reservation resList : reservationsList){
				
				roomBean.setId(resList.getLinkedRoom().getId());
				resList.getLinkedRoom().setPartecipants(rContr.getAllRoomPartecipants(roomBean));
			}
			
			request.setAttribute("reservationsList", reservationsList);
		
		}
	
	}catch(DatabaseException de){
		de.printStackTrace();
	}
	
//	method to handle click on roomOwner	
	for(Reservation r : reservationsList){
		
		if(request.getParameter(r.getRoomOwner().getUsername()) != null){
				
		    	persBean.setUsername(r.getRoomOwner().getUsername());
				session.setAttribute("othAccUsername", persBean);
			
				String site = new String("OtherAccount.jsp");
		        response.setStatus(response.SC_MOVED_TEMPORARILY);
		        response.setHeader("Location", site);
		}
	}

//	method to handle click on reservation partecipant
		for(Reservation res: reservationsList){
			
//			iterate over room's partecipants
			for(Person p: res.getLinkedRoom().getPartecipants()){
				
				if(request.getParameter(p.getUsername())!=null){
														    	
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
			<c:forEach items="${reservationsList}" var="reservationsList">
			<div class="card">
  				<div class="card-body">
						<div class="row"id="line">
	                        	<div class="col-md-2">
	                        		<label>Host:</label>
	                        	</div>
	                        	<div class="col-md-4">
	                        		<form method="get">
	                        			<button type="submit" style="border:none;backgroup:#ffffff" id="${reservationsList.roomOwner.username}" name="${reservationsList.roomOwner.username}">${reservationsList.roomOwner.username}</button>
	                        		</form>
	                        		
	                        	</div>
								<div class="col-md-2">
									<label>Reservation ID</label>
								</div>
								<div class="col -md-4">
									<p>${reservationsList.id}
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
									<p>${reservationsList.linkedRoom.specification.date}
								</div>
						</div>
						<div class="row"id="line">
	                        	<div class="col-md-2">
	                        		<label>Start time:</label>
	                        	</div>
	                        	<div class="col-md-4">
	                        		<p>${reservationsList.linkedRoom.specification.startTime}
	                        	</div>
								<div class="col-md-2">
									<label>End time:</label>
								</div>
								<div class="col -md-4">
									<p>${reservationsList.linkedRoom.specification.startTime}
								</div>
						</div>
						<div class="row"id="line">
	                        	<div class="col-md-2">
	                        		<label>Participants:</label>
	                        	</div>
	                        	<c:forEach items="${reservationsList.linkedRoom.partecipants}" var="person">
	              
	                        			<form method="get">
	                        				<button type="submit" style="border:none;backgroup:#ffffff" id="${person.username}" name="${person.username}">${person.username}</button>
	                        				&nbsp
	                        			</form>  
	                        	</c:forEach>                	
	                    	                        	
						</div>
    				<button id="btn" class="btn btn-outline-warning" data-toggle="modal" data-target="#makeAReviewModal"><a>Make a Review</a></button>    		
    				
           		</div>
           </div>
    		</c:forEach>		
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
            			<div class="col-md-3">
            				<select class="custom-select" id="inputGroupSelect01">
							    <option selected>Choose a number from 1 to 5</option>
							    <option value="1">One</option>
							    <option value="2">Two</option>
							    <option value="3">Three</option>
							    <option value="4">Four</option>
							    <option value="5">Five</option>
							</select>
            			</div>
            			<div class="col-md-3">
            				<label for="title" class="col-form-label">You are reviewing as:</label>
            			</div>
            			<div class="col-md-3">
            				<select class="custom-select" id="inputGroupSelect01">							
							    <option value="1">Host</option>
							    <option value="2">Guest</option>
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