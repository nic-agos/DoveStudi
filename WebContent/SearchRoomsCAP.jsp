<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix = "c"%> 

<%@ page import="java.util.*"%>

<%@ page import="logic.model.*"%>
<%@ page import="logic.bean.*"%>
<%@ page import="logic.exception.*"%>
<%@ page import="logic.controller.*"%>

<jsp:useBean id="roomSpecBean" scope="request" class="logic.bean.RoomSpecBean"/>
<jsp:setProperty name="roomSpecBean" property="*"/>

<%
	Person person = (Person)session.getAttribute("accPerson");

	RoomController rContr = RoomController.getInstance();
	ReservationController resContr = ReservationController.getInstance();
	
	List<Room> roomsList = new ArrayList<>();
	List<Room> allRoomsList = new ArrayList<>();
	
	AccountBean accBean = new AccountBean();
	PersonBean persBean = new PersonBean();
	RoomBean roomBean = new RoomBean();
	RoomBean tempRoomBean = new RoomBean();
	
	if(request.getParameter("searchBtn") != null){
		
		try{
			
			allRoomsList = rContr.searchRoomByCap(roomSpecBean);
			
			if(!allRoomsList.isEmpty()){
				
				if(person != null){
				
//					create a new list withous user rooms	
					for(Room r : allRoomsList) {
						
						if(r.getOwner().getCf().compareTo(person.getAccount().getCf()) != 0){
							roomsList.add(r);
						}
					}
				
				}else{
					roomsList = allRoomsList;
				}	
				
				for(Room r : roomsList) {
					
					tempRoomBean.setId(r.getId());
					r.setPartecipants(resContr.getAllRoomPartecipants(tempRoomBean));
					
				}
				
				session.setAttribute("roomsList", roomsList);
				
				String site = new String("SearchRoomsResult.jsp");
			    response.setStatus(response.SC_MOVED_TEMPORARILY);
			    response.setHeader("Location", site);
			}	

		}catch(DatabaseException de){
			de.printStackTrace();
			
		}catch(NotFoundException ne){
			ne.printStackTrace();
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
		          <a class="nav-link" href="SearchRoomsHost.jsp">Host name</a>
		        </li>
		        <li class="nav-item">
		          <a class="nav-link active" aria-current="page" href="SearchRoomsCAP.jsp">CAP</a>
		        </li>
		        <li class="nav-item">
		          <a class="nav-link" href="SearchRoomsDate.jsp">Date</a>
		        </li>
		        <li class="nav-item">
		          <a class="nav-link" href="SearchRoomsAvPl.jsp">Available places</a>
		        </li>
		      </ul>
		      <form class="d-flex">
		        <input class="form-control me-2" type="search" placeholder="CAP" id="cap" name="cap" aria-label="Search">
		        <button class="btn btn-outline-warning" id="searchBtn" name="searchBtn" type="submit">Search</button>
		      </form>
		    </div>
		  </div>
		</nav>
	</div>
	<script>function toggleSideBar(){
				document.getElementById("sidebar").classList.toggle("active");
			}	
	</script> 
</body>
</html>