<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.sql.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>My Account</title>
<link href="css/sidebar.css" rel="stylesheet">
<link href="css/account2.css" rel="stylesheet">
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
		 	<li style="background:#FF5500; color:#ffffff"><a style="font-size:25px; font-weight:bold; background-color:#FF5500;">#WHERETOSTUDY</a></li>
		 	<li><a href="HomePage.jsp">LoggedHomePage.jsp</a></li>
		 	<li><a href="AccountPagePersonalInfo.jsp">My Account</a></li>
		 	<li><a href="SearchRoom.jsp">Search for Rooms</a></li>
		 	<li><a href="PostRoom.jsp">Post a Room</a></li>
		 	<li><a href="index2.jsp">Log out</a></li>
		 </ul>
	</div>
	
	<div class="curved upper">
		<svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 1440 319"><path fill="#ffffff" fill-opacity="1" d="M0,64L48,96C96,128,192,192,288,224C384,256,480,256,576,245.3C672,235,768,213,864,181.3C960,149,1056,107,1152,106.7C1248,107,1344,149,1392,170.7L1440,192L1440,320L1392,320C1344,320,1248,320,1152,320C1056,320,960,320,864,320C768,320,672,320,576,320C480,320,384,320,288,320C192,320,96,320,48,320L0,320Z"></path></svg>
		<div class="regTitle">
			
		</div>
	</div>
	<div class="curved">
		<svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 1440 319"><path fill="#FF5500" fill-opacity="1" d="M0,64L48,96C96,128,192,192,288,224C384,256,480,256,576,245.3C672,235,768,213,864,181.3C960,149,1056,107,1152,106.7C1248,107,1344,149,1392,170.7L1440,192L1440,320L1392,320C1344,320,1248,320,1152,320C1056,320,960,320,864,320C768,320,672,320,576,320C480,320,384,320,288,320C192,320,96,320,48,320L0,320Z"></path></svg>
	<div id="page" style="background:rgb(0,0,0,0);">
			
			<!-- <img id="profilePct" src="https://image.flaticon.com/icons/png/128/1077/1077114.png" "/> style="height:150px; width:150px;float:left; -->
			<!-- <p style="font-size:40px; margin-top:50px; margin-left:200px; position:fixed; display:inline-block">Flaps44</h1><br> -->
			<!-- <hr is="line"> -->
	<!-- 	<div id="topbar">
			<ul>
				<li style="background-color:black; color:#FFFFFF; font-size:20px;"><a style="background-color:black; color:#FFFFFF; font-size:20px;" href="#">Personal Info</a></li>
				<li><a href="#">Request</a></li>
				<li><a href="#">Rooms</a></li>
				<li><a href="#">Reservation</a></li>
				
			</ul>
		</div> -->
		<div class="container emp-profile">
            <form method="post">
                <div class="row">
                    <div class="col-md-4">
                        <div class="profile-img">
                            <img src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcS52y5aInsxSm31CvHOFHWujqUx_wWTS9iM6s7BAm21oEN_RiGoog" alt=""/>
                            <div class="file btn btn-lg btn-primary">
                                Change Photo
                                <input type="file" name="file"/>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-6">
                        <div class="profile-head">
                                    <h5>
                                        Kshiti Ghelani
                                    </h5>                   
                                    <p class="proile-rating">HOST RATE : <span>8/10</span></p>
                                    <p class="proile-rating">GUEST RATE : <span>8/10</span></p>
                                    <p class="proile-rating">TOKENS : <span>20</span></p> 
                            <ul class="nav nav-tabs" id="myTab" role="tablist" >
                                <li class="nav-item">
                                    <a class="nav-link active" id="home-tab" data-toggle="tab" href="#home" role="tab" aria-controls="home" aria-selected="true">About</a>
                                </li>
                                <li class="nav-item">
                                    <a class="nav-link" id="profile-tab" data-toggle="tab" href="AccountMyReservations.jsp" role="tab" aria-controls="profile" aria-selected="false">My Reservations</a>
                                </li>
                                <li class="nav-item">
                                    <a class="nav-link" id="reservation-tab" data-toggle="tab" href="AccountMyRooms.jsp" role="tab" aria-controls="res" aria-selected="false">My Rooms</a>
                                </li>
                                <li class="nav-item">
                                    <a class="nav-link" id="groups-tab" data-toggle="tab" href="#AccountMyReservations.jsp" role="tab" aria-controls="groups" aria-selected="false">My Reservations</a>
                                </li>
                            </ul>
                        </div>
                    </div>
                    <div class="col-md-2">
                        <input type="submit" class="profile-edit-btn" name="btnAddMore" value="Edit Profile"/>
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-4">
                        <div class="profile-work">
                        	<p class="proile-rating">HOST RATE : <span>8/10</span></p>
                            <p class="proile-rating">GUEST RATE : <span>8/10</span></p>
                            <p class="proile-rating">TOKENS : <span>20</span></p>
                            <!-- <p>WORK LINK</p>
                            <a href="">Website Link</a><br/>
                            <a href="">Bootsnipp Profile</a><br/>
                            <a href="">Bootply Profile</a>
                            <p>SKILLS</p>
                            <a href="">Web Designer</a><br/>
                            <a href="">Web Developer</a><br/>
                            <a href="">WordPress</a><br/>
                            <a href="">WooCommerce</a><br/>
                            <a href="">PHP, .Net</a><br/> -->
                        </div>
                    </div>
                    <div class="col-md-8">
                        <div class="tab-content profile-tab" id="myTabContent">
                            <div class="tab-pane fade show active" id="home" role="tabpanel" aria-labelledby="home-tab">
                                        <div class="row">
                                            <div class="col-md-6">
                                                <label>User Id</label>
                                            </div>
                                            <div class="col-md-6">
                                                <p>Kshiti123</p>
                                            </div>
                                        </div>
                                        <div class="row">
                                            <div class="col-md-6">
                                                <label>Name</label>
                                            </div>
                                            <div class="col-md-6">
                                                <p>Kshiti Ghelani</p>
                                            </div>
                                        </div>
                                        <div class="row">
                                            <div class="col-md-6">
                                                <label>Email</label>
                                            </div>
                                            <div class="col-md-6">
                                                <p>kshitighelani@gmail.com</p>
                                            </div>
                                        </div>
                                        <div class="row">
                                            <div class="col-md-6">
                                                <label>BIRTH DATE</label>
                                            </div>
                                            <div class="col-md-6">
                                                <p>14/02/72</p>
                                            </div>
                                        </div>
                                        <div class="row">
                                            <div class="col-md-6">
                                                <label>Phone</label>
                                            </div>
                                            <div class="col-md-6">
                                                <p>123 456 7890</p>
                                            </div>
                                        </div>
                                        <div class="row">
                                            <div class="col-md-6">
                                                <label>SEX</label>
                                            </div>
                                            <div class="col-md-6">
                                                <p>Female</p>
                                            </div>
                                        </div>
                                      
                            </div>
                            <div class="tab-pane fade" id="profile" role="tabpanel" aria-labelledby="profile-tab">
                                        <div class="row">
                                            <div class="col-md-6">
                                                <label>Experience</label>
                                            </div>
                                            <div class="col-md-6">
                                                <p>Expert</p>
                                            </div>
                                        </div>
                                        <div class="row">
                                            <div class="col-md-6">
                                                <label>Hourly Rate</label>
                                            </div>
                                            <div class="col-md-6">
                                                <p>10$/hr</p>
                                            </div>
                                        </div>
                                        <div class="row">
                                            <div class="col-md-6">
                                                <label>Total Projects</label>
                                            </div>
                                            <div class="col-md-6">
                                                <p>230</p>
                                            </div>
                                        </div>
                                        <div class="row">
                                            <div class="col-md-6">
                                                <label>English Level</label>
                                            </div>
                                            <div class="col-md-6">
                                                <p>Expert</p>
                                            </div>
                                        </div>
                                        <div class="row">
                                            <div class="col-md-6">
                                                <label>Availability</label>
                                            </div>
                                            <div class="col-md-6">
                                                <p>6 months</p>
                                            </div>
                                        </div>
                                <div class="row">
                                    <div class="col-md-12">
                                        <label>Your Bio</label><br/>
                                        <p>Your detail description</p>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </form>           
        </div>
		
	</div>
	</div>

	<script>function toggleSideBar(){
				document.getElementById("sidebar").classList.toggle("active");
			}	
	</script>
	<script>function togglePage(){
				document.getElementById("page").classList.toggle("active");
			}	
	</script>	
	<script>function toggleTopBar(){
				document.getElementById("topBar").classList.toggle("active");
			}	
	</script>	
	
</body>
</html>