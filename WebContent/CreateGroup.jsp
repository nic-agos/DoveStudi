<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix = "c"%> 

<%@ page import="java.util.*"%>

<%@ page import="logic.model.*"%>
<%@ page import="logic.bean.*"%>
<%@ page import="logic.exception.*"%>
<%@ page import="logic.controller.*"%>
 
<%
	GroupController gContr = GroupController.getInstance();

	Person person = (Person)session.getAttribute("accPerson");
	
	GroupBean groupBean = new GroupBean();

	if(request.getParameter("createGroupBtn") != null){
		
		
		groupBean.setName(request.getParameter("groupName"));
		groupBean.setAdmin(person.getAccount().getCf());
		
		try{
			
//			check if all data are correct
			groupBean.validate();
			
			gContr.createGroup(groupBean);

//			redirect
			String site = new String("MyGroups.jsp");
	        response.setStatus(response.SC_MOVED_TEMPORARILY);
	        response.setHeader("Location", site);
			
		}catch(GroupException ge){
			out.println("<div class=\"alert alert-info\" style=\" text-align:center;position: fixed; bottom: 5px;left:2%;width: 96%;\"role=\"alert\"><strong>"+ge.getMessage()+"</strong><button type=\"button\" class=\"close\" data-dismiss=\"alert\"aria-label=\"Close\"> <span aria-hidden=\"true\">&times;</span></button></div>");
		}catch(DatabaseException de){
			out.println("<div class=\"alert alert-info\" style=\" text-align:center;position: fixed; bottom: 5px;left:2%;width: 96%;\"role=\"alert\"><strong>"+de.getMessage()+"</strong><button type=\"button\" class=\"close\" data-dismiss=\"alert\"aria-label=\"Close\"> <span aria-hidden=\"true\">&times;</span></button></div>");
		}
	}
%>   

<!DOCTYPE html>
<html>
<head>

<meta charset="ISO-8859-1">
<title>Group Form</title>
<link href="css/btn1.css" rel="stylesheet"/>
<link href="css/sidebar.css" rel="stylesheet" />

<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>

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
	<div class="container" style="text-align:center; height:80px;">	
		<div class="container" style="text-align:center; margin-top:20px; font-weight:600;">
	  		<div class="vertical-center">
	    		<h2 style="font-weight:600;">Create Group</h2>
	  		</div>
		</div>
	</div>
	<div class="card" id="card" style="width:600px; left:30%;margin-top:20px;">
		<form id="form_post_room" method="POST">
	  		<div class="row"style="margin-left:20px;margin-top:10px;">
      				<div class="col-md-4">
           				<label for="title" class="col-form-label">Group name:</label>
           			</div>
           			<div class="col-md-11">
           				<input type="text" class="form-control" id="groupName" name="groupName" required placeHolder="Insert Group Name" required>
           			</div>
           	</div>  			
	  		<div class="container" style="text-align:center; margin-top:20px; margin-bottom:30px;">
  				<div class="vertical-center">	    				
  					<button type="submit" id="createGroupBtn" name="createGroupBtn" class="btn btn-outline-warning">Create Group</button>	
  				</div>
			</div>
		</form>
	</div>
	
	<script>function toggleSideBar(){
				document.getElementById("sidebar").classList.toggle("active");
			}	
	</script>
	
</body>
</html>