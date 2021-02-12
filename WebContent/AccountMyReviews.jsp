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

//	check if the user is logged	
	if(person != null){
		
		ReviewController revContr = ReviewController.getInstance();
		
		AccountBean accBean = new AccountBean();
		accBean.setCf(person.getAccount().getCf());
		
		PersonBean persBean = new PersonBean();
		
		List<Review> receivedList = new ArrayList<>();
		List<Review> doneList = new ArrayList<>();
		
		try{
			
			receivedList = revContr.getReceivedReviews(accBean);
			doneList = revContr.getDoneReviews(accBean);
			
			request.setAttribute("receivedList", receivedList);
			request.setAttribute("doneList", doneList);
		
		}catch(DatabaseException de){
			out.println("<div class=\"alert alert-info\" style=\" text-align:center;position: fixed; bottom: 5px;left:2%;width: 96%;\"role=\"alert\"><strong>"+de.getMessage()+"</strong><button type=\"button\" class=\"close\" data-dismiss=\"alert\"aria-label=\"Close\"> <span aria-hidden=\"true\">&times;</span></button></div>");	
		}

//		method to handle click on reviewing user
		for(Review r : receivedList) {
			if(request.getParameter(r.getReviewer().getPerson().getUsername()) != null){
				
				persBean.setUsername(r.getReviewer().getPerson().getUsername());
				session.setAttribute("othAccUsername", persBean);
				
//				redirect				
				String site = new String("OtherAccount.jsp");
		        response.setStatus(response.SC_MOVED_TEMPORARILY);
		        response.setHeader("Location", site);
		        
			}
		}

//		method to handle click on reviewed user
		for(Review r : doneList){
			
			if(request.getParameter(r.getReviewed().getUsername()) != null){
				
				persBean.setUsername(r.getReviewed().getUsername());
				session.setAttribute("othAccUsername", persBean);
				
//				redirect				
				String site = new String("OtherAccount.jsp");
		        response.setStatus(response.SC_MOVED_TEMPORARILY);
		        response.setHeader("Location", site);
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
<title>My Reviews</title>

<link href="css/myReviews.css" rel="stylesheet"/>
<link href="css/sidebar.css" rel="stylesheet" />

<link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">

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
	
	<div class="container" style="text-align:center; margin-top:20px; font-weight:600;">
  		<div class="vertical-center">
    		<h1 style="font-weight:600;">My Reviews</h1>
  		</div>
	</div>
		
	<div class= "container head-profile">
		<div class="col-md-18" style="margin-top:80px;" >
			<div class="row">
				<div class="card">
					<div class="card-header" style="font-size:20px;font-weight:600;">Done reviews</div>
						<c:forEach items ="${doneList}" var="doneList">
							<div class="card">
			  					<div class="card-body">
			  						<div class="row">
			  							<div class="col -md-1">
			  								<label>Review title: </label>
			  							</div>
			  							<div class="col-md-5">
			  								<p>${doneList.title}
			  							</div>
			  							<div class="col -md-4">
			  								<h4><span class="badge bg-secondary" style="color:#ffffff; margin-left:15px;">${doneList.tag}</span></h4>
			  							</div>
			  						</div>
									<div class="row">
			                        	<div class="col-md-4">
			                              	<label>Reviewed user:</label>                
			                        	</div>
			                            <div class="col-md-3">
			                        		<form method="get">
				                        			<button type="submit" style="border:none;backgroup:#ffffff" id="${doneList.reviewed.username}" name="${doneList.reviewed.username}">${doneList.reviewed.username}</button>
				                        	</form>
				                        </div>
			                            <div class="col-md-2">
			                              	<label>Rating:</label>
			                            </div>
			                            <div class="col-md-3">
			                        		<p>${doneList.rating}
			                            </div>
			                        </div>
			                        <div class="row">
			                            <div class="col-md-12">
			                        		<p>${doneList.description}</p>
			                            </div>
			                        </div>
			                 	</div>
		                  </div>
		      		</c:forEach>
				</div>
	                 
	            <div class="card">
					<div class="card-header"style="font-size:20px;font-weight:600;">Received reviews</div>
						<c:forEach items ="${receivedList}" var="receivedList">
							<div class="card">
				  				<div class="card-body">
				  						<div class="row">
			  							<div class="col -md-1">
			  								<label>Review title: </label>
			  							</div>
			  							<div class="col-md-5">
			  								<p>${receivedList.title}
			  							</div>
			  							<div class="col -md-4">
			  								<h4><span class="badge bg-secondary" style="color:#ffffff; margin-left:15px;">${receivedList.tag}</span></h4>
			  							</div>
			  						</div>
										<div class="row">
				                        	<div class="col-md-4">
				                              	<label>Reviewing user:</label>
				                            </div>
				                            <div class="col-md-3">
				                            	<form method="get">
				                        			<button type="submit" style="border:none;backgroup:#ffffff" id="${receivedList.reviewer.person.username}" name="${receivedList.reviewer.person.username}">${receivedList.reviewer.person.username}</button>
				                        		</form>
				                            </div>
				                            <div class="col-md-2">
				                              	<label>Rating:</label>
				                            </div>
				                            <div class="col-md-3">
				                        		<p>${receivedList.rating}</p>
				                            </div>
				                        </div>
				                        <div class="row">
				                            <div class="col-md-12">
				                        		<p>${receivedList.description}</p>
				                            </div>
				                        </div>
				                 </div>
			                </div>
		                </c:forEach>
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