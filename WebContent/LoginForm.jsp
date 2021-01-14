<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.sql.*" %>    
    
    
<%
	if (session.getAttribute("login")!=null){
		response.sendRedirect("LoggedHomePage.jsp");
	}
%>

<%
	try{
		Class.forName("com.mysql.jdbc.Driver");
		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/dovestudi?useSSL=false", "root", "Pomello99");
	
		if (request.getParameter("btn_login")!=null){
			String email, password;
			String inputEmail, inputPsw;
			
			inputEmail=request.getParameter("txt_usr");
			inputPsw=request.getParameter("txt_psw");
			
			PreparedStatement pstmt=null;
			
			pstmt= conn.prepareStatement("select * from account where email=? AND password=?");
			pstmt.setString(1, inputEmail);
			pstmt.setString(2, inputPsw);
			ResultSet rs=pstmt.executeQuery();
			if(rs.next()){
				email = rs.getString("email");
				password=rs.getString("password");
			
				if(inputEmail.equals(email) && inputPsw.equals(password)){
					session.setAttribute("login", email);
					response.sendRedirect("LoggedHomePage.jsp");
				}
			}else{
				request.setAttribute("errorMsg", "invalid email or password");
			}
			conn.close();
		}
	}catch(Exception e){
		out.println(e);
	}
%>    
    
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Login Form</title>
	<link rel="stylesheet" href="css/loginStyle.css">
</head>

<body>
	<div class="curved upper">
		<h1>Log in</h1>
		<svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 1440 320"><path fill="#ffffff" fill-opacity="1" d="M0,64L48,96C96,128,192,192,288,224C384,256,480,256,576,245.3C672,235,768,213,864,181.3C960,149,1056,107,1152,106.7C1248,107,1344,149,1392,170.7L1440,192L1440,320L1392,320C1344,320,1248,320,1152,320C1056,320,960,320,864,320C768,320,672,320,576,320C480,320,384,320,288,320C192,320,96,320,48,320L0,320Z"></path></svg>
	</div>
	<div class="login-box">
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
		<h2><a href="RegistrationPage.jsp">Don't have an account yet? Register now</a></h2>
		<input type="submit" name="btn_login" class="btn_login" value="Login">
		</form>
	</div>

</body>
</html>