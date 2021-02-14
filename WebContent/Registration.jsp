<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
   
<%@ page import="logic.bean.AccountBean" %>
<%@ page import="logic.model.dao.AccountDAOImpl" %>
<%@ page import="java.sql.SQLException"%>
<%@ page import="logic.exception.DatabaseException"%>
<%@ page import="logic.controller.RegistrationController" %>
<%@ page import="logic.exception.AccountException" %>

<jsp:useBean id="accountBean" scope="request" class="logic.bean.AccountBean"/>
<jsp:useBean id="personBean" scope="request" class="logic.bean.PersonBean"/>
<jsp:setProperty name="accountBean" property="*"/>
<jsp:setProperty name="personBean" property="*"/>

<%
	boolean registration;
	
	if(request.getParameter("registerBtn")!=null){
	
		try{

//			check if all data are correct
			accountBean.validate();
			personBean.validate();
			
			RegistrationController rContr = RegistrationController.getInstance();
			
			registration = rContr.register(accountBean, personBean);
				
			if(registration){

//				redirect
				String site = new String("Login.jsp");
			    response.setStatus(response.SC_MOVED_TEMPORARILY);
			    response.setHeader("Location", site);
			}	
		
		}catch (AccountException ae){
			out.println("<div class=\"alert alert-info\" style=\" text-align:center;position: fixed; bottom: 5px;left:2%;width: 96%;\"role=\"alert\"><strong>"+ae.getMessage()+"</strong><button type=\"button\" class=\"close\" data-dismiss=\"alert\"aria-label=\"Close\"> <span aria-hidden=\"true\">&times;</span></button></div>");	
		}catch (DatabaseException de){
			out.println("<div class=\"alert alert-info\" style=\" text-align:center;position: fixed; bottom: 5px;left:2%;width: 96%;\"role=\"alert\"><strong>"+de.getMessage()+"</strong><button type=\"button\" class=\"close\" data-dismiss=\"alert\"aria-label=\"Close\"> <span aria-hidden=\"true\">&times;</span></button></div>");
		}
	}
%>


<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Registration</title>

<link href="css/registration.css" rel="stylesheet"/>

<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">

<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>

</head>

<body>
<div class="d-flex justify-content-xl-center">
	<h1 style="font-weight:600; font-family:sans-serif;margin-top:30px;">Registration</h1>
</div>
	<div class="d-flex justify-content-xl-center">
			<form style="width:600px; margin-top:-60px;">
				<div class="row" id="line">
					<div class="col-md-6">
						<input type="text" class="form-control" name="name" id="name" placeHolder="Name" required/>
					</div>
					<div class="col-md-6">
						<input type="text" class="form-control" name="surname" id="surname" placeHolder="Surname" required/>
					</div>
				</div>
				<div class="row" id="line">
					<div class="col-md-6">
						<input type="date" class="form-control" name="dateBirth" id="dateBirth" placeHolder="Birth Date (DD/MM/YYYY)" required/>
					</div>
					<div class="col-md-6">
						<input type="text" class="form-control" name="cf" id="cf" placeHolder="Fiscal Code" required/>
					</div>
				</div>
				<div class="row" id="line">
					<div class="col-md-6">
            				<select class="custom-select" id="studyGrade" name="studyGrade" required>
							   <!-- <option selected>Study grade</option> -->
							    <option value="Elementary School">Elementary school</option>
							    <option value="Middle School">Middle school</option>
							    <option value="High School">High School</option>
							    <option value="University">University</option>
							    <option value="PhD">PhD</option>
							</select>
            			</div>
					<div class="col-md-6">
						<input type="text" class="form-control" id="school" name="school" placeHolder="School" required/>
					</div>
				</div>
				<div class="d-flex justify-content-xl-center" id="line">
					<div class="col-md-6">
						<input type="email" class="form-control" id="email" name="email" placeHolder="Email Address" required/>
					</div>
				</div>
				<div class="d-flex justify-content-xl-center" id="line">
					<div class="col-md-6">
						<input type="text" class="form-control" name="username" id="username" placeHolder="Username" required/>
					</div>
				</div>
				<div class="d-flex justify-content-xl-center" id="line">
					<div class="col-md-6">
						<input type="password" class="form-control" id="password" name="password" placeHolder="Password" required/>
					</div>
				</div>
				<div class="d-flex justify-content-xl-center">
				<button type="submit" class="btn btn-outline-warning" id="registerBtn" name="registerBtn" style="margin-top:30px;">Register</button>
			</div>
			</form>
	</div>	
</body>
</html>