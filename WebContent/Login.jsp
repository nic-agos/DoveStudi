<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix = "c"%> 

<%@ page import="java.util.*"%>

<%@ page import="logic.model.*"%>
<%@ page import="logic.bean.*"%>
<%@ page import="logic.exception.*"%>
<%@ page import="logic.controller.*"%>

<jsp:useBean id="accountBean" scope="request" class="logic.bean.AccountBean"/>
<jsp:setProperty name="accountBean" property="*"/>

<%
	Person res;

	if(request.getParameter("loginBtn") != null){
		
		LoginController lContr = LoginController.getInstance();
		
		try{
			
			res = lContr.login(accountBean);
			
			if (res != null) {
				
				session.setAttribute("accPerson", res);
				String site = new String("AccountPersInfo.jsp");
		        response.setStatus(response.SC_MOVED_TEMPORARILY);
		        response.setHeader("Location", site);
			}
			
		}catch (DatabaseException de) {
			out.println("<div class=\"alert alert-info\" style=\" text-align:center;position: fixed; bottom: 5px;left:2%;width: 96%;\"role=\"alert\"><strong>"+de.getMessage()+"</strong><button type=\"button\" class=\"close\" data-dismiss=\"alert\"aria-label=\"Close\"> <span aria-hidden=\"true\">&times;</span></button></div>");
		}catch (LoginException le) {
			out.println("<div class=\"alert alert-info\" style=\" text-align:center;position: fixed; bottom: 5px;left:2%;width: 96%;\"role=\"alert\"><strong>"+le.getMessage()+"</strong><button type=\"button\" class=\"close\" data-dismiss=\"alert\"aria-label=\"Close\"> <span aria-hidden=\"true\">&times;</span></button></div>");
		}
	}
%>	

<!DOCTYPE html>
<html>
<head>

<meta charset="ISO-8859-1">
<title>Login</title>
<link href="css/loginView.css" rel="stylesheet"/>

<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">

<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>

</head>

<body>

	<div class="curved upper">
		<svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 1440 320"><path fill="#ffffff" fill-opacity="1" d="M0,64L48,96C96,128,192,192,288,224C384,256,480,256,576,245.3C672,235,768,213,864,181.3C960,149,1056,107,1152,106.7C1248,107,1344,149,1392,170.7L1440,192L1440,320L1392,320C1344,320,1248,320,1152,320C1056,320,960,320,864,320C768,320,672,320,576,320C480,320,384,320,288,320C192,320,96,320,48,320L0,320Z"></path></svg>
	</div>
	<div class="d-flex justify-content-center">
		<h1 style="font-weight:600; font-family:sans-serif; margin-top:-60px;">Login</h1>
	</div>
	<div class="d-flex justify-content-xl-center" style="margin-top:20px;">
		<form class="needs-validation" novalidate style="width:400px;">
			<div class="row">
	  			<div class="col xs-2">
	  				<div class="form-group">
	    				<input type="text" class="form-control" name="email" id="email" placeHolder="Enter Email" required="">
	  				</div>
	  			</div>
	 		</div>
	 		<div class="row">
	  			<div class="col">
	  				<div class="form-group">
	    				<input type="password" class="form-control" name="password" id="password" placeHolder="Enter Password" required>
	  				</div>
	  			</div>
	 		</div>  			
			<div class="d-flex justify-content-xl-center">
				<h5><a href="Registration.jsp">Don't have an account yet? Register now</a></h5>
			</div>	
			<div class="d-flex justify-content-xl-center">
				<button type="submit" class="btn btn-outline-warning" id="loginBtn" name="loginBtn" style="border:2px solid #ff5500; font-size:20px; margin-top:30px; font-weight:550;">Login </a></button>
			</div>
		</form>
	</div>
	
</body>
</html>