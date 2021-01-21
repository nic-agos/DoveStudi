<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.sql.*" %>
<%@ page import="logic.model.dao.AccountDAOImpl" %>
<%@ page import="logic.bean.AccountBean" %>

<!-- File da eliminare -->

<%-- <%
try{
 	Class.forName("com.mysql.jdbc.Driver");
 	Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/dovestudi?useSSL=false", "root", "Pomello99"); 
 	if (request.getParameter("btn_register")!=null){
 		String firstname, lastname, email, password;
 		firstname=request.getParameter("firstname");
 		lastname=request.getParameter("lastname");
 		email=request.getParameter("email");
 		password=request.getParameter("password");
		String cf="";
		String dateBirth="";
		int num= 2;
		
 		AccountBean accountBean = new AccountBean(cf, firstname, lastname, email, password, dateBirth, num);
 		
 		PreparedStatement stmt = conn.prepareStatement("INSERT INTO account (CF, Name, Surname, Email, Password, Date_Birth, Number_Token) VALUES (?, ?, ?, ?, ?, ?, ?)");
 		stmt.setString(1, accountBean.getCf());
		stmt.setString(2, accountBean.getName());
		stmt.setString(3, accountBean.getSurname());
		stmt.setString(4, accountBean.getEmail());
		stmt.setString(5, accountBean.getPassword());
		stmt.setString(6, accountBean.getDateBirth());
		stmt.setInt(7, accountBean.getNumberToken());
		
		stmt.executeUpdate();
		conn.close();
 	}
}catch(Exception e){
 		
 	}
 
 %> --%>
 
 
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Register</title>
	<link href="css/registration.css" rel="stylesheet"/>
</head>

<body>
	<div class="curved upper">
		<h5>#WhereToStudy</h5>
		<svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 1440 319"><path fill="#ffffff" fill-opacity="1" d="M0,64L48,96C96,128,192,192,288,224C384,256,480,256,576,245.3C672,235,768,213,864,181.3C960,149,1056,107,1152,106.7C1248,107,1344,149,1392,170.7L1440,192L1440,320L1392,320C1344,320,1248,320,1152,320C1056,320,960,320,864,320C768,320,672,320,576,320C480,320,384,320,288,320C192,320,96,320,48,320L0,320Z"></path></svg>
	</div>
	<div class="curved">
	<div class="wrapper">
		<div><h1>Registration Form</h1>
			<p>Please fill in this form to create an account.</p></div>
		
		<form class="regform" method="post" onsubmit="return validate();"><!-- action="AccountPage.jsp"* -->
			
			<div class="container">
				<input id="first_name" type="text" name="first_name" placeholder="First Name" value="" required>
				<input id="last_name" type="text" name="last_name" placeholder="Last Name" value="" required>
				<input type="date" id="birthdate" name="birthdate" placeholder="Birth date" value="" style="width:100%; font-size:16px; padding: 8px 0; margin: 8px 0; border-bottom: 1px solid #000000;"required>
				<select class="option" name="subject" style="padding: 8px 0; margin: 8px 0; border-bottom: 1px solid #000000;">
				<option disabled="disabled" selected="selected"></option>
				<option>Male</option>
				<option>Female</option>
				</select>
				<input type="email" id="email" name="email" placeholder="Email address">
				<input type="tel" id="phone" name="phone" placeholder="Phone number">
				<input type="password" id="password" name="password" placeholder="Password">
				<input type="password" id="verpassword" name="verpassword" placeholder="Repeat password">
				<button class="registerbtn"><a>Register</a></button>
			</div>
		</form>
	</div>
	<svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 1440 319"><path fill="#FF5500" fill-opacity="1" d="M0,64L48,96C96,128,192,192,288,224C384,256,480,256,576,245.3C672,235,768,213,864,181.3C960,149,1056,107,1152,106.7C1248,107,1344,149,1392,170.7L1440,192L1440,320L1392,320C1344,320,1248,320,1152,320C1056,320,960,320,864,320C768,320,672,320,576,320C480,320,384,320,288,320C192,320,96,320,48,320L0,320Z"></path></svg>
	</div>
	

	
	<!-- <script>
		function validate()
		{
			var f_name= /^ [a-z A-Z]+$;
			var l_name= /^[a-z A-Z]+$;
			var valid_email= /^[\w\d\.]+\@[a-zA-Z\.]+\.[A-Za-z]{1,4}$/;
			var valid_password= /^[A-Z a-z 0-9 !@#%&*()<>]{6,12}$/;
			var valid_verpassword= /^[A-Z a-z 0-9 !@#%&*()<>]{6,12}$/;
			
			var first_name= document.getElementById("first_name");
			var last_name= document.getElementById("last_name");
			var email= document.getElementById("email");
			var password = document.getElementById("password");
			var verpassword = document.getElementById("verpassword");
		}
		if(!f_name.test(first_name.value) || first_name.value==''){
			alert("Enter Firstname Alphabet Only!");
			first_name.focus();
			return false;
		}
		var password = document.getElementById("password");
		
	</script> -->
 
</body>
</html>