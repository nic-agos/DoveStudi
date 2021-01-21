<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>My Account</title>
<link href="css/account.css" rel="stylesheet"/>
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
		 	<li><a href="AccountMyReservations.jsp">My Reservations</a></li>
		 	<li><a href="AccountMyReviews.jsp">My Reviews</a></li>
		 	<li><a href="AccountMyRooms.jsp">My Rooms</a></li>
		 	<li><a href="PostRoom.jsp">Post a Room</a></li>
		 	<li><a href="index.jsp">Log out</a></li>
		 </ul>
	</div>
	
	<div class= "container head-profile">
		<form method="post">
			<div class="row">
			
				<div class="col-md-4">
					<div class="profile-img">
						<img src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcS52y5aInsxSm31CvHOFHWujqUx_wWTS9iM6s7BAm21oEN_RiGoog" alt=""/>
						<div class="file btn btn-lg btn-warning">
							Change Photo
							<input type="file" name="file"/>
						</div>
					</div>
				</div>
				
				<div class="col-md-6">
					<div class="profile-head">
						<h5>My name</h5>
						<p class="profile-rating">HOST RATE : <span>8/10</span> </p>
						<p class="profile-rating">GUEST RATE : <span>8/10</span> </p>
					</div>
					<!-- <div class="profile-tokens">
						<p class="profile-rating">EARNED TOKENS: <span>7</span></p>
						<p class="profile-rating">SPENT TOKENS: <span>7</span></p>
					</div> -->
				</div>
				<div class="col-md-2">
                    <input type="submit" class="profile-edit-btn" name="btnAddMore" value="Edit Profile"/>
                </div>
				
			</div>
			
			<div class="row" id="tab">
				<ul class="nav nav-tabs" id="myTab" role="tablist">
					<li class="nav-item">
                    	<a class="nav-link active" id="home-tab" data-toggle="tab" href="AccountPublicInfo.jsp" role="tab" aria-controls="home" aria-selected="true">Public Info</a>
                    </li>
                    <li class="nav-item">
                     	<a class="nav-link" id="profile-tab" data-toggle="tab" href="AccountPersonalInfo.jsp" role="tab" aria-controls="profile" aria-selected="false">Personal Info</a>
                    </li>
				</ul>
			</div>
			
			<div class="col-md-12">
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
                              	<label>Email</label>
                            </div>
                            <div class="col-md-6">
                        		<p>kshitighelani@gmail.com</p>
                            </div>
                        </div>
                        <div class="row">
                        	<div class="col-md-6">
                              	<label>Birthdate</label>
                            </div>
                            <div class="col-md-6">
                        		<p>02/07/72</p>
                            </div>
                        </div>
                        <div class="row">
                        	<div class="col-md-6">
                              	<label>Study grade</label>
                            </div>
                            <div class="col-md-6">
                        		<p>High school Diploma</p>
                            </div>
                        </div>
               			<div class="row">
                        	<div class="col-md-6">
                              	<label>School</label>
                            </div>
                            <div class="col-md-6">
                        		<p>Methuen Highschool</p>
                            </div>
                        </div>
					</div>
				</div>
			</div>
		</form>
	</div>
	
	<div class="curved">
		<svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 1440 319"><path fill="#FF5500" fill-opacity="1" d="M0,64L48,96C96,128,192,192,288,224C384,256,480,256,576,245.3C672,235,768,213,864,181.3C960,149,1056,107,1152,106.7C1248,107,1344,149,1392,170.7L1440,192L1440,320L1392,320C1344,320,1248,320,1152,320C1056,320,960,320,864,320C768,320,672,320,576,320C480,320,384,320,288,320C192,320,96,320,48,320L0,320Z"></path></svg>
	</div>
	
	
	<script>function toggleSideBar(){
				document.getElementById("sidebar").classList.toggle("active");
			}	
	</script>
</body>
</html>