<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
   
<%@ page import="logic.bean.AccountBean" %>
<%@ page import="logic.model.dao.AccountDAOImpl" %>
<%@ page import="java.sql.SQLException"%>
<%@ page import="logic.exception.DatabaseException"%>
<%@ page import="logic.controller.RegistrationController" %>
<%@ page import="logic.exception.AccountException" %>
<%@ page import="logic.exception.PersonException" %>

<jsp:useBean id="accountBean" scope="request" class="logic.bean.AccountBean"/>
<jsp:useBean id="personBean" scope="request" class="logic.bean.PersonBean"/>
<jsp:setProperty name="accountBean" property="*"/>
<jsp:setProperty name="personBean" property="*"/>

<%
boolean res1 = false;
	boolean res2 = false;
	if(request.getParameter("registerBtn")!=null){
	
		try{
	res1 = accountBean.validate();
		
		}catch (AccountException ae){
	ae.printStackTrace();
		}
		try{
	res2 = personBean.validate();
		
		}catch (PersonException pe){
	pe.printStackTrace();
		}
		
		if (res1 && res2){
	RegistrationController rContr = RegistrationController.getInstance();
	try{
		rContr.register(accountBean, personBean);
		
		String redirectURL = "http://localhost:8080/DoveStudi.git/Login.jsp";
		response.sendRedirect(redirectURL);	
	
	}catch (DatabaseException de){
				de.printStackTrace();
			}	
		}
	}

%>


<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Registration</title>
<link href="css/btn1.css" rel="stylesheet"/>
<link href="css/registration.css" rel="stylesheet"/>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
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
						<input type="text" class="form-control" id="studyGrade" name="studyGrade" placeHolder="Study Grade" required/>
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
				<button type="submit" class="btn btn-outline-warning" id="registerBtn" name="registerBtn" style="margin-top:30px;">Register</a></button>
			</div>
			</form>
	</div>	
</body>
</html>