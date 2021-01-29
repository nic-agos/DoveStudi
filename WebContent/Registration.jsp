<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ page import="java.sql.*"%>
<%@ page import="logic.bean.AccountBean" %>
<%@ page import="logic.model.dao.AccountDAOImpl" %>
<%@ page import="java.sql.SQLException" %>
<%@ page import="logic.controller.RegistrationController" %>
<%@ page import="logic.exception.RegistrationAccountException" %>
<%@ page import="logic.exception.RegistrationPersonException" %>

<jsp:useBean id="accountBean" scope="request" class="logic.bean.AccountBean"/>
<jsp:useBean id="personBean" scope="request" class="logic.bean.PersonBean"/>
<jsp:setProperty name="accountBean" property="*"/>
<jsp:setProperty name="personBean" property="*"/>

<%
	boolean res1 = false;
	boolean res2 = false;
	if(request.getParameter("registerbtn")!=null){
	
		try{
			res1 = accountBean.validate();
		
		}catch (RegistrationAccountException ae){
			ae.printStackTrace();
		}
//			da aggiungere una pagina che visualizza gli errori ed un bottone per riprovare a compiere la registrazione
		try{
			res2 = personBean.validate();
		
		}catch (RegistrationPersonException pe){
			pe.printStackTrace();
		}
		
		if (res1 && res2){
			RegistrationController rContr = RegistrationController.getInstance();
			try{
				accountBean.setNumberToken(0);
				rContr.registerAccount(accountBean);
				personBean.setAccount(accountBean.getCf());
				personBean.setGuestRating(0);
				personBean.setHostRating(0);
				rContr.registerPerson(personBean);
%>
				<jsp:forward page="Login.jsp"/>
<%	
			}catch (SQLException se){
				se.printStackTrace();
//			pagina che visualizza errori?
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
	<!-- <div class="curved upper" style="margin-bottom:0px;">
		<svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 1440 319"><path fill="#ffffff" fill-opacity="1" d="M0,64L48,96C96,128,192,192,288,224C384,256,480,256,576,245.3C672,235,768,213,864,181.3C960,149,1056,107,1152,106.7C1248,107,1344,149,1392,170.7L1440,192L1440,320L1392,320C1344,320,1248,320,1152,320C1056,320,960,320,864,320C768,320,672,320,576,320C480,320,384,320,288,320C192,320,96,320,48,320L0,320Z"></path></svg>
		
	</div>  -->
	
	<!-- <div class="curved"> -->
		<!-- <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 70 1440 320"><path fill="#FF5500" fill-opacity="1" d="M0,64L48,96C96,128,192,192,288,224C384,256,480,256,576,245.3C672,235,768,213,864,181.3C960,149,1056,107,1152,106.7C1248,107,1344,149,1392,170.7L1440,192L1440,320L1392,320C1344,320,1248,320,1152,320C1056,320,960,320,864,320C768,320,672,320,576,320C480,320,384,320,288,320C192,320,96,320,48,320L0,320Z"></path></svg> -->
	<!-- <div class="wrapper">
		
		<form class="regform" method="POST" onsubmit="return validate();">action="AccountPage.jsp"*
			
			<div class="container">
			
				<input id="name" type="text" name="name" placeholder="First Name" value="" required>
					<input id="surname" type="text" name="surname" placeholder="Last Name" value="" required><br/>
			</div>
			
			<div id ="line">
				<input type="date" id="dateBirth" name="dateBirth" placeholder="Birth date" value="" required>style="width:100%; font-size:16px; padding: 8px 0; margin: 8px 0; border-bottom: 1px solid #000000;"
			</div>
			
			<div id="line">	
				<input type="email" id="email" name="email" placeholder="Email address">
				<input type="text" id="cf" name="cf" placeholder="Fiscal Code"><br/>
			</div>
			
			<div id="line">	
				<input type="password" id="password" name="password" placeholder="Password"><br/>
			
			<div id ="line" class="name">
				<input type="text" id="username" name="username" placeholder="Username" value="" required>
				<br/>
			</div>
			
			<div id ="line">
				<input type="text" id="studyGrade" name="studyGrade" placeholder="Study Grade" value="" required>
				<input type="text" id="school" name="school" placeholder="School" value="" required>
			</div>
			
			<button class="registerbtn" name="registerbtn" type="submit"><a href="Login.jsp">Register</a></button>
			</div>
		</form>
	</div> -->
		<!-- <div class="d-flex justify-content-xl-center">
			<h1 style="font-weight:600; font-family:sans-serif;font-size:50px; ">Registration</h1>
		</div> -->
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
				<button type="submit" class="btn btn-outline-warning" id="btn" style="margin-top:30px;"><a href="Login.jsp">Register</a></button>
			</div>
			</form>
		</div>
	
</body>
</html>