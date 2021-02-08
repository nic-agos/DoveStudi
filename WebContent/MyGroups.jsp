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

//	check if the user is logged
	if(person != null){
		
		accBean.setCf(person.getAccount().getCf());
		persBean.setId(person.getId());
		
		try{
	
	adminGroups = gContr.getAdministeredGroups(accBean);
	
	if(!adminGroups.isEmpty()) {

//				add participants list to every group
		for(Group g : adminGroups){
	
	try{
		
		groupBean.setName(g.getName());
		groupBean.setAdmin(g.getAdmin().getCf());
		g.setParticipants(gContr.getGroupParticipants(groupBean));
	
	}catch(DatabaseException de){
		de.printStackTrace();
	}
		}
	}
	
	request.setAttribute("adminGroups", adminGroups);
	
	partGroups = gContr.getParticipatingGroups(persBean);
	
	if(!partGroups.isEmpty()){

//			add participants to the group
		for(Group g : partGroups){
	
	try {
		
		groupBean.setName(g.getName());
		groupBean.setAdmin(g.getAdmin().getCf());
		g.setParticipants(gContr.getGroupParticipants(groupBean));
	
	}catch(DatabaseException de){
		de.printStackTrace();
	}
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
		
//		method to handle clikc on group owner of participating groups
		for(Group g :part2Groups){
	
	if(request.getParameter(g.getAdmin().getPerson().getUsername()) != null) {
		
		tempPersBean.setUsername(g.getAdmin().getPerson().getUsername());
		session.setAttribute("othAccUsername", tempPersBean);

//				redirect
		String site = new String("OtherAccount.jsp");
	    response.setStatus(response.SC_MOVED_TEMPORARILY);
	    response.setHeader("Location", site);
	}
		}

//		method to handle click on room participant for participanting groups
		for(Group g :part2Groups){
		
//			iterate over room's participants
	for(Person p: g.getParticipants()){
	
		if(request.getParameter(p.getUsername()) != null){
							    	
	tempPersBean.setUsername(p.getUsername());
	session.setAttribute("othAccUsername", tempPersBean);

//					redirect
	String site = new String("OtherAccount.jsp");
		    response.setStatus(response.SC_MOVED_TEMPORARILY);
		    response.setHeader("Location", site);
		}
	}
		}

//		method to handle click on room participant for administered groups
		for(Group g : adminGroups){
	
//			iterate over room's participants
	for(Person p: g.getParticipants()){
		
		if(request.getParameter(p.getUsername()) != null){
								    	
	tempPersBean.setUsername(p.getUsername());
	session.setAttribute("othAccUsername", tempPersBean);

//					redirect
	String site = new String("OtherAccount.jsp");
	response.setStatus(response.SC_MOVED_TEMPORARILY);
	response.setHeader("Location", site);
		}
	}
		}

//		method to handle click on book room for groups
		for(Group g : adminGroups){
	
	String s = "book"+g.getName();
	
	if(request.getParameter(s) != null) {
		
		tempGroupBean.setAdmin(person.getAccount().getCf());
		tempGroupBean.setName(g.getName());
		
		try{
	
	group = gContr.getSpecificAdministeredGroup(tempGroupBean);
	session.setAttribute("groupBook", group);

//					redirect
	String site = new String("SearchRoomForGroups.jsp");
	        response.setStatus(response.SC_MOVED_TEMPORARILY);
	        response.setHeader("Location", site);
	
	
		}catch(DatabaseException de){
	de.printStackTrace();
		}
	}
		}

//		method to handle click on create group button		
		if(request.getParameter("createBtn") != null){

//			redirect
	String site = new String("CreateGroup.jsp");
	        response.setStatus(response.SC_MOVED_TEMPORARILY);
	        response.setHeader("Location", site);

		}

//		methods to handle click on leave from participating group
		for(Group g : partGroups){
	
	String s = "leave"+g.getName();
	
	if(request.getParameter(s) != null) {
		
		tempGroupBean.setAdmin(g.getAdmin().getCf());
		tempGroupBean.setName(g.getName());
		tempGroupBean.setParticipant(person.getId());
		
		try{
	
	gContr.leaveGroup(tempGroupBean);
	
	String site = new String("MyGroups.jsp");
	        response.setStatus(response.SC_MOVED_TEMPORARILY);
	        response.setHeader("Location", site);
	
	
		}catch(DatabaseException de){
	de.printStackTrace();
		
		}catch(AccountException ae){
	ae.printStackTrace();
		}
	}
		}

//		methods to handle click on delete administered group
		for(Group g : adminGroups){
	
	String s = "delete"+g.getName();
	
	if(request.getParameter(s) != null) {
		
		tempGroupBean.setAdmin(person.getAccount().getCf());
		tempGroupBean.setName(g.getName());
		
		try{
	
	gContr.deleteGroup(tempGroupBean);
	
	String site = new String("MyGroups.jsp");
	        response.setStatus(response.SC_MOVED_TEMPORARILY);
	        response.setHeader("Location", site);
	
	
		}catch(DatabaseException de){
	de.printStackTrace();
		}
	}
		}

//		methods to handle click on add participant to administered group
		for(Group g : adminGroups){
	
	String s = "add"+g.getName();
	
	if(request.getParameter(s) != null) {
		

		tempGroupBean.setAdmin(person.getAccount().getCf());
 				tempGroupBean.setName(g.getName());
		
		try{
	
	group = gContr.getSpecificAdministeredGroup(tempGroupBean);
	session.setAttribute("groupAdd", group);
	
	String site = new String("AddGroupParticipants.jsp");
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
<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>

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

	<div class="container" style="text-align:center; margin-top:20px; font-weight:600;">
    	<h1 style="font-weight:600;">My Groups</h1>  		
	</div> <br/>
	<div class="container" style="text-align:center;">
		<form method="get">
	    	<button type="submit" class="btn btn-outline-warning" id="createBtn" name="createBtn">Create Group</button>
	    </form>
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
									<label>Number of participants:</label>
								</div>
								<div class="col -md-4">
									<p>${adminGroups.numParticipants}
								</div>
							</div>
							<div class="row"id="line">
	                        	<div class="col-md-2">
	                        		<label>Participants:</label>
	                        	</div>	                        	
                        		<c:forEach items="${adminGroups.participants}" var="person">	              
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
		                   			<button class="btn btn-outline-warning" id="book${adminGroups.name}" name="book${adminGroups.name}" style="margin-bottom:10px;margin-left:30px;">Book Room</button>
			                    	<button class="btn btn-outline-warning" id="delete${adminGroups.name}" name="delete${adminGroups.name}" style="margin-bottom:10px;margin-left:30px;">Delete Group</button>
			                    	<button class="btn btn-outline-warning" id="add${adminGroups.name}" name="add${adminGroups.name}" style="margin-bottom:10px;margin-left:30px;">Add Person</button>
		                   		</form>			                    	
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
									<p>${partGroups.numParticipants}
								</div>
							</div>
							<div class="row"id="line">
	                        	<div class="col-md-2">
	                        		<label>Participants:</label>
	                        	</div>	                        	
	                        	<c:forEach items="${partGroups.participants}" var="person">	              
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
		                    		<button class="btn btn-outline-warning" id="leave${partGroups.name}" name="leave${partGroups.name}" style="margin-bottom:10px;margin-left:30px;">Leave Group</button>
		                		</form>
		                	</div>
		             	</div>
	        		</div>
        	</c:forEach>
	     </div>
	</div>
  
  <script>function toggleSideBar(){
				document.getElementById("sidebar").classList.toggle("active");
			}	
	</script>
	
</body>
</html>