<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ page import="java.sql.*" %>   

<%
	try{
		
	}catch(Exception e){
		
	}
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Login</title>
<link href="css/loginView.css" rel="stylesheet"/>
</head>
<body>
	<div class="curved upper">
		<h5>#WhereToStudy</h5>
		<svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 1440 320"><path fill="#ffffff" fill-opacity="1" d="M0,64L48,96C96,128,192,192,288,224C384,256,480,256,576,245.3C672,235,768,213,864,181.3C960,149,1056,107,1152,106.7C1248,107,1344,149,1392,170.7L1440,192L1440,320L1392,320C1344,320,1248,320,1152,320C1056,320,960,320,864,320C768,320,672,320,576,320C480,320,384,320,288,320C192,320,96,320,48,320L0,320Z"></path></svg>
	</div>
	<div class="curved">
		<div class="login-box" style="width:40%; margin-left:450px;">
		<h1>Login</h1>
		<form method="post" action="AccountPage.jsp">
		<div class="textbox">
			<i class="fas fa-user"></i>
			<input type="text" placeholder="Username" name="txt_usr" value="" required>
		</div>
		<div class="textbox">
		    <i class="fas fa-lock"></i>
			<input type="password" placeholder="Password" name="txt_psw" value="" required>
		</div>
		<h2><a href="Registration.jsp">Don't have an account yet? Register now</a></h2>
		<button class="btn_login"><a href="LoggedHomePage.jsp">Sign in</a></button>
		</form>
	</div>
		<svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 1440 320"><path fill="#FF5500" fill-opacity="1" d="M0,64L48,96C96,128,192,192,288,224C384,256,480,256,576,245.3C672,235,768,213,864,181.3C960,149,1056,107,1152,106.7C1248,107,1344,149,1392,170.7L1440,192L1440,320L1392,320C1344,320,1248,320,1152,320C1056,320,960,320,864,320C768,320,672,320,576,320C480,320,384,320,288,320C192,320,96,320,48,320L0,320Z"></path></svg>
	</div>
</body>
</html>