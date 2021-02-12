<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ page import="logic.model.*"%>
<%@ page import="logic.model.dao.*"%>
<%@ page import="logic.bean.*"%>
<%@ page import="logic.exception.*"%>
<%@ page import="logic.controller.*"%>

<jsp:useBean id="reviewBean" scope="request" class="logic.bean.ReviewBean"/>
<jsp:setProperty name="reviewBean" property="*"/>

<%
	Person person = (Person)session.getAttribute("accPerson");
	PersonBean persBean = (PersonBean)session.getAttribute("revPers");
	
	ReviewController rContr = ReviewController.getInstance();
	
	if(request.getParameter("reviewBtn") != null){
		
		reviewBean.setReviewer(person.getAccount().getCf());
		
		try{
			
			reviewBean.validate();
			
			rContr.makeReview(reviewBean, persBean);

//			redirect
			String site = new String("AccountMyReviews.jsp");
	        response.setStatus(response.SC_MOVED_TEMPORARILY);
	        response.setHeader("Location", site);
		
		}catch(DatabaseException de){
			out.println("<div class=\"alert alert-info\" style=\" text-align:center;position: fixed; bottom: 5px;left:2%;width: 96%;\"role=\"alert\"><strong>"+de.getMessage()+"</strong><button type=\"button\" class=\"close\" data-dismiss=\"alert\"aria-label=\"Close\"> <span aria-hidden=\"true\">&times;</span></button></div>");
		}catch(ReviewException re){
			out.println("<div class=\"alert alert-info\" style=\" text-align:center;position: fixed; bottom: 5px;left:2%;width: 96%;\"role=\"alert\"><strong>"+re.getMessage()+"</strong><button type=\"button\" class=\"close\" data-dismiss=\"alert\"aria-label=\"Close\"> <span aria-hidden=\"true\">&times;</span></button></div>");
		}catch(AccountException ae){
			out.println("<div class=\"alert alert-info\" style=\" text-align:center;position: fixed; bottom: 5px;left:2%;width: 96%;\"role=\"alert\"><strong>"+ae.getMessage()+"</strong><button type=\"button\" class=\"close\" data-dismiss=\"alert\"aria-label=\"Close\"> <span aria-hidden=\"true\">&times;</span></button></div>");
		}
	}
%>

<!DOCTYPE html>
<html>
<head>

<meta charset="ISO-8859-1">
<title>Review Form</title>
<link href="css/btn1.css" rel="stylesheet"/>
<link href="css/sidebar.css" rel="stylesheet" />
<style>
	label{
	font-weight:600;}
</style>
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
	    		<h1 style="font-weight:600;">Review Form</h1>
	  		</div>
		</div>
	</div>
	<div class="card" id="card" style="width:600px; left:30%;margin-top:20px;">
		<form id="form_post_room" method="POST">
	  		<div class="row"style="margin-left:20px;margin-top:20px;">
				<div class="col -md-4">
	  				<label for="resID">You are reviewing:</label>
	  			</div>
	  			<div class="col-md-6">
	    			<p><%=persBean.getUsername() %>
	  			</div>
	  		</div>
	  		<div class="row"style="margin-left:20px;margin-top:10px;">
	  			<div class="col-md-6">
           				<label for="title" class="col-form-label">You are reviewing as:</label>
           		</div>
         		<div class="col-md-5">
         			<select class="custom-select" id="tag" name="tag" required>							
				    	<option value="HOST">Host</option>
				    	<option value="GUEST">Guest</option>
					</select>
         		</div>
	  		</div>
	  		<div class="row"style="margin-left:20px;margin-top:10px;">
    			<div class="col-md-2">
         			<label for="title" class="col-form-label">Title:</label>
         		</div>
         		<div class="col-md-9">
         			<input type="text" class="form-control" id="title" name="title" required>
         		</div>
           	</div>
          	<div class="row"style="margin-left:20px;margin-top:10px;">
      			<div class="col-md-11">
           			<textarea class="form-control" id="description" name="description" rows="3" style="max-height:100px; margin-top:20px;" placeHolder="Write a review" required></textarea>
           		</div>        
           	</div>
           	<div class="row" style="margin-top:10px; margin-left:20px;">
      			<div class="col-md-2">
           			<label for="title" class="col-form-label">Rating:</label>
           		</div>
           		<div class="col-md-9">
           			<select class="custom-select" id="rating" name="rating" required>
					    <option value="1">One</option>
					    <option value="2">Two</option>
					    <option value="3">Three</option>
					    <option value="4">Four</option>
					    <option value="5">Five</option>
					</select>
           		</div>
           	</div>
			<div class="container" style="text-align:center; margin-top:20px; margin-bottom:60px;">
 				<div class="vertical-center"> 			
	     		<button class="btn btn-outline-warning" id="reviewBtn" name="reviewBtn" style="margin-top:30px;">Post Review</button>
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