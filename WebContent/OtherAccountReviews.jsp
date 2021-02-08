<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix = "c"%> 

<%@ page import="java.util.*"%>
    
<%@ page import="logic.model.*"%>
<%@ page import="logic.model.dao.*"%>
<%@ page import="logic.bean.*"%>
<%@ page import="logic.exception.*"%>
<%@ page import="logic.controller.*"%>   

<%

	PersonBean personBean = (PersonBean)session.getAttribute("othPersBean");
	
	AccountBean accBean = new AccountBean();
	
	PersonBean persBean = new PersonBean(); 
	
	accBean.setCf(personBean.getAccount());
	
	ReviewController rContr = ReviewController.getInstance();
	
	List<Review> reviewList = null;
			
	try{
		 reviewList = rContr.getReceivedReviews(accBean);
		 request.setAttribute("reviewList", reviewList);
		
	}catch(DatabaseException de){
		de.printStackTrace();
	}
	if(request.getParameter("reviewBtn") != null){
		
		persBean.setUsername(personBean.getUsername());
		session.setAttribute("revPers", persBean);
		
		String site = new String("ReviewForm.jsp");
	    response.setStatus(response.SC_MOVED_TEMPORARILY);
	    response.setHeader("Location", site);
		
	}
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>My Account</title>
<link href="css/account.css" rel="stylesheet"/>
<link href="css/btn1.css" rel="stylesheet"/>
<link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<!-- Riga dopo è in conflitto con le tab -->
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
<script src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script src="https://code.jquery.com/jquery-3.5.0.js"></script>

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
	
	<div class="d-flex justify-content-xl-left" style="margin-left:330px;">
		<div class="col-md-5">
			<h1 style="font-weight:600; font-family:sans-serif;margin-top:50px; margin-left:0px;">My Account</h1>
		</div>
		<div class="col-md-6" style="margin-top:20px;">
		</div>
	</div>
	
	<div class="row">
		<ul class="nav nav-tabs" id="myTab" role="tabList" style="margin-left:300px; margin-top:30px;">
			<li class="nav-item">
				<a class="nav-link" id="home-tab"href="OtherAccount.jsp" >Info</a>
			</li>
			<li class="nav-item">
              	<a class="nav-link active" id="profile-tab" href="OtherAccountReviews.jsp">Reviews</a>
            </li>			
		</ul>
	</div>
	
	<c:forEach items="${reviewList}" var="reviewList">
		<div class="card" style="width:630px;min-height:200px; margin-left:280px; max-height:200px;">
			<div class="row" style="margin-left:20px;margin-top:20px;">
				<div class="col-md-2">
					<label>Title:</label>
				</div>
				<div class="col-md-12">
					<p>${reviewList.title}
				</div>
			</div>
			<div class="row" style="margin-left:20px;">
				<div class="col-md-1">
					<label>Tag:</label>
				</div>
				<div class="col-md-3">
					<p>${reviewList.tag}
				</div>
				<div class="col-md-2">
					<label>Rating:</label>
				</div>
				<div class="col-md-2">
					<p>${reviewList.rating}
				</div>
			</div>
			<div class="row" style="margin-left:20px;">
				<div class="col-md-12">
					<input readOnly style="margin-left:20px;min-height:80px;width:550px; maxlenght:50; overflow:hidden;" value="${reviewList.description}">
				</div>
			</div>
		</div>
	</c:forEach>
	<div style="position:absolute;top:0px;right:0px;width:25%;height:100%;">
	     <div class="row">
		     <form method="get">
		     	<button class="btn btn-outline-warning" id="reviewBtn" name="reviewBtn" style="margin-top:30px;">Make a Review</button>
		     </form>
     	</div>
	</div>
	
	<script>function toggleSideBar(){
				document.getElementById("sidebar").classList.toggle("active");
			}	
	</script>
	
</body>
</html>