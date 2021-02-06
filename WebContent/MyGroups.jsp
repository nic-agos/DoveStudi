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
	GroupController gContr = GroupController.getInstance();
	
	List<Group> adminGroups = new ArrayList<>();
	List<Group> partGroups = new ArrayList<>();
	List<Group> part2Groups = new ArrayList<>();
	
	AccountBean accBean = new AccountBean();
	PersonBean persBean = new PersonBean();
	Group group;
	GroupBean groupBean = new GroupBean();
	GroupBean tempGroupBean = new GroupBean();
	PersonBean tempPersBean = new PersonBean();
	
	if(person != null){
		
		accBean.setCf(person.getAccount().getCf());
		persBean.setId(person.getId());
		
		try{
			
			adminGroups = gContr.getAdministeredGroups(accBean);
	
		if(!adminGroups.isEmpty()) {
		
			for(Group g : adminGroups){
	
				groupBean.setName(g.getName());
				groupBean.setAdmin(g.getAdmin().getCf());
				g.setPartecipants(gContr.getGroupPartecipants(groupBean));
			}
		}
	
		request.setAttribute("adminGroups", adminGroups);
	
		partGroups = gContr.getParticipatingGroups(persBean);
	
		if(!partGroups.isEmpty()){

//			add participants to the group
			for(Group g : partGroups){
	
				groupBean.setName(g.getName());
				groupBean.setAdmin(g.getAdmin().getCf());
				g.setPartecipants(gContr.getGroupPartecipants(groupBean));
			}
		}

//			remove the groups of which the user is admin
			for(Group g: partGroups){
				if(g.getAdmin().getCf().compareTo(accBean.getCf()) != 0){
					part2Groups.add(g);
				}
			}
			request.setAttribute("partGroups", part2Groups);
	
		}catch(DatabaseException de){
			de.printStackTrace();
		}
		
//		method to handle clikc on group owner of partecipating groups
		for(Group g :part2Groups){
			
			if(request.getParameter(g.getAdmin().getPerson().getUsername()) != null) {
				
				tempPersBean.setUsername(g.getAdmin().getPerson().getUsername());
				session.setAttribute("othAccUsername", tempPersBean);
				
				String site = new String("OtherAccount.jsp");
			    response.setStatus(response.SC_MOVED_TEMPORARILY);
			    response.setHeader("Location", site);
			}
		}

//		method to handle click on room partecipant for partecipanting groups
		for(Group g :part2Groups){
				
//			iterate over room's partecipants
			for(Person p: g.getPartecipants()){
					
				if(request.getParameter(p.getUsername()) != null){
															    	
					tempPersBean.setUsername(p.getUsername());
					session.setAttribute("othAccUsername", tempPersBean);
								
					String site = new String("OtherAccount.jsp");
				    response.setStatus(response.SC_MOVED_TEMPORARILY);
				    response.setHeader("Location", site);
				}
			}
		}

//		method to handle click on room partecipant for administered groups
		for(Group g : adminGroups){
					
//			iterate over room's partecipants
			for(Person p: g.getPartecipants()){
						
				if(request.getParameter(p.getUsername()) != null){
																    	
					tempPersBean.setUsername(p.getUsername());
					session.setAttribute("othAccUsername", tempPersBean);
									
					String site = new String("OtherAccount.jsp");
					response.setStatus(response.SC_MOVED_TEMPORARILY);
					response.setHeader("Location", site);
				}
			}
		}

//		method to handle click on book for groups
		for(Group g : adminGroups){
			
			String s = "book"+g.getName();
			System.out.println(s);
			
			if(request.getParameter(s) != null) {
				
				tempGroupBean.setAdmin(person.getAccount().getCf());
				tempGroupBean.setName(g.getName());
				
				try{
					
					group = gContr.getSpecificAdministeredGroup(tempGroupBean);
					session.setAttribute("groupBook", group);
					
					String site = new String("SearchRoomForGroups.jsp");
			        response.setStatus(response.SC_MOVED_TEMPORARILY);
			        response.setHeader("Location", site);
					
					
				}catch(DatabaseException de){
					de.printStackTrace();
				}
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
<title>My Groups</title>
<link href="css/myGroups.css" rel="stylesheet"/>
<link href="css/btn2.css" rel="stylesheet"/>
<link href="css/btn1.css" rel="stylesheet"/>
<link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">
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
	<!-- <div class="curved">
		<svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 1440 319"><path fill="#FF5500" fill-opacity="1" d="M0,64L48,96C96,128,192,192,288,224C384,256,480,256,576,245.3C672,235,768,213,864,181.3C960,149,1056,107,1152,106.7C1248,107,1344,149,1392,170.7L1440,192L1440,320L1392,320C1344,320,1248,320,1152,320C1056,320,960,320,864,320C768,320,672,320,576,320C480,320,384,320,288,320C192,320,96,320,48,320L0,320Z"></path></svg>
	</div> -->
	<div class="container" style="text-align:center; margin-top:20px; font-weight:600;">
    		<h1 style="font-weight:600;">My Groups</h1>  		
	</div> <br/>
	
 
     
     <div class="container" style="text-align:center;">
     	<button type="button" class="btn btn-outline-warning" data-toggle="modal" data-target="#createGroup"id="btn"><a>Create Group</a></button>
     </div>
	
	<div class= "container head-profile" style="margin-bottom:30px;">
	
			<div class="col-md-10" style="margin-top:50px; margin-left:100px;">
				
				<c:forEach items="${adminGroups}" var="adminGroups">
					<div class="card">         
		         		<div class="card-header" id="myCard" style="font-weight: 600; font-size:20px;">${adminGroups.name}</div>
		  					
		  					<div class="card-body">						
		                        <div class="row"id="line">
		                        	<div class="col-md-1">
		                        		<label>Admin:</label>
		                        	</div>
		                        	<div class="col-md-4">
		                        		<p>Me</p>
		                        	</div>
									<div class="col-md-3">
										<label>Number of partecipants:</label>
									</div>
									<div class="col -md-4">
										<p>${adminGroups.numPartecipants}
									</div>
								</div>
								<div class="row"id="line">
		                        	<div class="col-md-2">
		                        		<label>Participants:</label>
		                        	</div>	                        	
		                        		<c:forEach items="${adminGroups.partecipants}" var="person">	              
	                        			<form method="get">
	                        				<button type="submit" style="border:none;backgroup:#ffffff" id="${person.username}" name="${person.username}">${person.username}</button>
	                        				&nbsp
	                        			</form>	      	                   	                  
	                        		</c:forEach>	                        	
								</div>
		                   	</div>
		                   	<div class="row">
		                   		<div class="col-md-12">
		                   		<form method="get">
		                   			<button class="btn btn-outline-warning" id="book${adminGroups.name}" name="book${adminGroups.name}" style="margin-bottom:10px;margin-left:30px;">Book Room</a></button>
			                    	<button class="btn btn-outline-warning" id="btn" style="margin-bottom:10px;margin-left:30px;">Delete Group</button>
			                    	<button class="btn btn-outline-warning"data-toggle="modal" data-target="#addPersonModal"id="btn" style="margin-bottom:10px;margin-left:30px;">Add Person</button>
		                   		</form>
			                    	
			                    	<!-- da vedere se mettere l'opzione di modificare il gruppo -->
									<!-- <button class="btn btn-outline-warning"data-toggle="modal" data-target="#modifyGroupModal"id="btn" style="margin-bottom:10px;margin-left:30px;">Modify Group</button> -->		                	
			                	</div>
			            
			             	</div>
		        	</div>
	        	</c:forEach>
	        	
	        	<c:forEach items="${partGroups}" var="partGroups">
		        	<div class="card">         
		         		<div class="card-header" id="myCard" style="font-weight: 600; font-size:20px;">${partGroups.name}</div>
		  					<div class="card-body">						
		                        <div class="row"id="line">
		                        	<div class="col-md-1">
		                        		<label>Admin:</label>
		                        	</div>
		                        	<div class="col-md-4">
			                        	<form method="get">
		                        			<button type="submit" style="border:none;backgroup:#ffffff" id="${partGroups.admin.person.username}" name="${partGroups.admin.person.username}">${partGroups.admin.person.username}</button>
		                        		</form>
		                        	</div>
									<div class="col-md-3">
										<label>Number of participants:</label>
									</div>
									<div class="col -md-4">
										<p>${partGroups.numPartecipants}
									</div>
								</div>
								<div class="row"id="line">
		                        	<div class="col-md-2">
		                        		<label>Participants:</label>
		                        	</div>	                        	
		                        		<c:forEach items="${partGroups.partecipants}" var="person">	              
	                        			<form method="get">
	                        				<button type="submit" style="border:none;backgroup:#ffffff" id="${person.username}" name="${person.username}">${person.username}</button>
	                        				&nbsp
	                        			</form>	      	                   	                  
	                        		</c:forEach>                         	
								</div>
		                   	</div>
		                   	<div class="row">
			                	<div class="col-md-3">
			                		<form method="get">
			                    		<button class="btn btn-outline-warning"data-toggle="modal" data-target="#exampleModalCenter"id="btn" style="margin-bottom:10px;margin-left:30px;"><a href="#">Leave Group</a></button>
			                		</form>
			                	</div>
			             	</div>
		        	</div>
	        	</c:forEach>
	     	</div>	
  		
	</div>
	
		<!-- Delete Room Modal -->
<div class="modal fade" id="deleteGroupModal" tabindex="-1" role="dialog" aria-labelledby="deleteGroupModal" aria-hidden="true">
  <div class="modal-dialog modal-dialog-centered" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalLongTitle">Delete Group</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
        Are you sure you want to delete this Group?
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-dismiss="modal">No</button>
        <button type="button" id="btn"class="btn btn-outline-warning" data-toggle="modal"><a href="MyGroups.jsp">Yes</a></button>
      </div>
    </div>
  </div>
</div>

<!-- Create Group Modal -->
<div class="modal fade bd-example-modal-lg" id="createGroup" tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel" aria-hidden="true">
  <div class="modal-dialog modal-lg" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalLabel">Create Group</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
        <form>
          <div class="form-group">
            <label for="recipient-name" class="col-form-label">Group name:</label>
            <input type="text" class="form-control" id="recipient-name">
          </div>
        </form>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
        <button type="button" class="btn btn-outline-warning" id="btn"><a href="MyGroups.jsp">Create Group</a></button>
      </div>
    </div>
  </div></div>
  
  <!-- Add Person Modal -->
<div class="modal fade bd-example-modal-lg" id="addPersonModal" tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel" aria-hidden="true">
  <div class="modal-dialog modal-lg" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalLabel">Add Person</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
        <form class="d-flex">
		        <input class="form-control me-2" type="search" placeholder="Username" aria-label="Search">
		</form>

      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
        <button type="button" class="btn btn-outline-warning" id="btn"><a href="MyGroups.jsp">Add</a></button>
      </div>
    </div>
  </div></div>
  	
	<script>function toggleSideBar(){
				document.getElementById("sidebar").classList.toggle("active");
			}	
	</script>
</body>
</html>