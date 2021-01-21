<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix = "c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix = "sql"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>My Groups</title>
<link href="css/myGroups.css" rel="stylesheet"/>
<link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<script src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script src="https://code.jquery.com/jquery-3.5.0.js"></script>
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
		 	<li><a href="SearchRoom.jsp">Search for Rooms</a></li>
		 	<li><a href="AccountPublicInfo.jsp">My Account</a></li>
		 	<li><a href="MyGroups.jsp">My Groups</a></li>
		 	<li><a href="AccountMyFutReservations.jsp">My Reservations</a></li>
		 	<li><a href="AccountMyReviews.jsp">My Reviews</a></li>
		 	<li><a href="AccountMyRooms.jsp">My Rooms</a></li>
		 	<li><a href="PostRoom.jsp">Post a Room</a></li>
		 	<li><a href="index.jsp">Log out</a></li>
		 </ul>
	</div>
	<!-- <div class="curved">
		<svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 1440 319"><path fill="#FF5500" fill-opacity="1" d="M0,64L48,96C96,128,192,192,288,224C384,256,480,256,576,245.3C672,235,768,213,864,181.3C960,149,1056,107,1152,106.7C1248,107,1344,149,1392,170.7L1440,192L1440,320L1392,320C1344,320,1248,320,1152,320C1056,320,960,320,864,320C768,320,672,320,576,320C480,320,384,320,288,320C192,320,96,320,48,320L0,320Z"></path></svg>
	</div> -->
	<div class="container" style="text-align:center; margin-top:20px; font-weight:600;">
    		<h1 style="font-weight:600;">My Groups</h1>  		
	</div> <br/>
	
 
     
     <div class="container" style="text-align:center;">
     	<button><a>Create Group</a></button>
     </div>
	
	<div class= "container head-profile" style="margin-bottom:30px;">
	
			<div class="col-md-10" style="margin-top:50px; margin-left:100px;">
				
				<div class="card">         
	         		<div class="card-header" id="myCard" style="background:#ff6b24;font-weight: 600; font-size:20px;"><c:out value = "${row.Name}"/></div>
	  					
	  					<div class="card-body">
							<div class="row" id="line">
	                        	<div class="col-md-1">
	                        		<label>Name:</label>
	                        	</div>
	                            <div class="col-md-7">
	                        		<p>GroupName</p>
	                            </div>
	                        </div>							
	                        <div class="row"id="line">
	                        	<div class="col-md-1">
	                        		<label>Admin:</label>
	                        	</div>
	                        	<div class="col-md-4">
	                        		<p><a>Mario72</a></p>
	                        	</div>
								<div class="col-md-3">
									<label>Number of partecipants:</label>
								</div>
								<div class="col -md-4">
									<p>3
								</div>
							</div>
							<div class="row"id="line">
	                        	<div class="col-md-2">
	                        		<label>Participants:</label>
	                        	</div>	                        	
	                        		<p><a href="OtherAccount.jsp">Mario98, </a> 
	                        		<p><a href="OtherAccount.jsp">Luca.p</a>	                        	
							</div>
	                   	</div>
	                   	<div class="row">
	                   	<div class="col-md-3"></div>
		                <div class="col-md-3">
		                    <button id="res-btn" style="margin-bottom:10px;"><a href="#">Modify</a></button>
		                </div>
		                <div class="col-md-4">
		    				<button id="res-btn" style="margin-bottom:10px;"><a href="#">Delete</a></button>
		    			</div>
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