<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ page import="logic.model.*"%>
<%@ page import="logic.bean.*"%>
<%@ page import="logic.exception.*"%>
<%@ page import="logic.controller.*"%>   
<%
	PersonBean persBean = (PersonBean)session.getAttribute("othAccount");
	AccountController rContr = AccountController.getInstance();
	Person person = null;
	
	try{
		person = rContr.getOtherAccountInfo(persBean);
		
	}catch(DatabaseException de){
		de.printStackTrace();
	}
	
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Account</title>
<link href="css/account.css" rel="stylesheet"/>
<link href="css/btn1.css" rel="stylesheet"/>
<link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<script src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script src="https://code.jquery.com/jquery-3.5.0.js"></script>

</head>


<body>
	<div class="curved">
		<svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 1440 319"><path fill="#FF5500" fill-opacity="1" d="M0,64L48,96C96,128,192,192,288,224C384,256,480,256,576,245.3C672,235,768,213,864,181.3C960,149,1056,107,1152,106.7C1248,107,1344,149,1392,170.7L1440,192L1440,320L1392,320C1344,320,1248,320,1152,320C1056,320,960,320,864,320C768,320,672,320,576,320C480,320,384,320,288,320C192,320,96,320,48,320L0,320Z"></path></svg>
	</div>
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
		 	<li><a href="AccountMyReservations.jsp">My Reservations</a></li>
		 	<li><a href="AccountMyReviews.jsp">My Reviews</a></li>
		 	<li><a href="AccountMyRooms.jsp">My Rooms</a></li>
		 	<li><a href="PostRoom.jsp">Post a Room</a></li>
		 	<li><a href="index.jsp">Log out</a></li>
		 </ul>
	</div>
	
	<div class="d-flex justify-content-xl-left" style="margin-left:330px;">
		<div class="col-md-5">
			<h1 style="font-weight:600; font-family:sans-serif;margin-top:50px; margin-left:0px;">My Account</h1>
		</div>
		<div class="col-md-6" style="margin-top:20px;">
			<p class="profile-rating">HOST RATING : <span><%=person.getHostRating()%></span> </p>
			<p class="profile-rating">GUEST RATING : <span><%=person.getGuestRating()%></span> </p>
		</div>
	</div>
	
	<div class="row">
		<ul class="nav nav-tabs" id="myTab" role="tabList" style="margin-left:300px; margin-top:30px;">
			<li class="nav-item">
				<a class="nav-link active" id="home-tab"  href="OtherAccount.jsp" >Info</a>
			</li>
			<li class="nav-item">
              	<a class="nav-link" id="profile-tab" href="OtherAccountReviews.jsp">Reviews</a>
            </li>
			
		</ul>
	</div>
	
	<div class="card" style="width:630px; margin-left:280px;">
		<div class="row" style="margin-left:20px;margin-top:20px;">
			<div class="col-md-6">
				<label>Username:</label>
			</div>
			<div class="col-md-12">
				<p><%=person.getUsername()%>
			</div>
		</div>
		<div class="row" style="margin-left:20px;">
			<div class="col-md-6">
				<label>Email:</label>
			</div>
			<div class="col-md-12">
				<p><%=person.getAccount().getEmail()%>
			</div>
		</div>
		<div class="row" style="margin-left:20px;">
			<div class="col-md-6">
				<label>Birthdate:</label>
			</div>
			<div class="col-md-12">
				<p><%=person.getAccount().getDateBirth()%>
			</div>
		</div>
		<div class="row" style="margin-left:20px;">
			<div class="col-md-6">
				<label>Studygrade:</label>
			</div>
			<div class="col-md-12">
				<p><%=person.getStudyGrade()%>
			</div>
		</div>
		<div class="row" style="margin-left:20px;">
			<div class="col-md-6">
				<label>School:</label>
			</div>
			<div class="col-md-12">
				<p><%=person.getSchool()%>
			</div>
		</div>
	</div>
	
	<script>function toggleSideBar(){
				document.getElementById("sidebar").classList.toggle("active");
			}	
	</script>
</body>
</html>