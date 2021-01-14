<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link href="css/registrationForm.css" rel="stylesheet"/>
</head>
<body>
	<div class="curved upper">
		<h2>#WhereToStudy</h2>
		<svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 1440 319"><path fill="#ffffff" fill-opacity="1" d="M0,64L48,96C96,128,192,192,288,224C384,256,480,256,576,245.3C672,235,768,213,864,181.3C960,149,1056,107,1152,106.7C1248,107,1344,149,1392,170.7L1440,192L1440,320L1392,320C1344,320,1248,320,1152,320C1056,320,960,320,864,320C768,320,672,320,576,320C480,320,384,320,288,320C192,320,96,320,48,320L0,320Z"></path></svg>
		<div class="regTitle">
			<h1>Register</h1>
			<p>Please fill in this form to create an account.</p>
		</div>
	</div>
	<div class="curved">
		<svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 1440 319"><path fill="#FF5500" fill-opacity="1" d="M0,64L48,96C96,128,192,192,288,224C384,256,480,256,576,245.3C672,235,768,213,864,181.3C960,149,1056,107,1152,106.7C1248,107,1344,149,1392,170.7L1440,192L1440,320L1392,320C1344,320,1248,320,1152,320C1056,320,960,320,864,320C768,320,672,320,576,320C480,320,384,320,288,320C192,320,96,320,48,320L0,320Z"></path></svg>
	<div class="wrapper">
		
		<form class="regform" method="post" onsubmit="return validate();"><!-- action="AccountPage.jsp"* -->
			
			<div class="container">
				<div id ="line" class="name">
					<input id="first_name" type="text" name="first_name" placeholder="First Name" value="" required>
					<input id="last_name" type="text" name="last_name" placeholder="Last Name" value="" required><br/>
				</div>
				<div id ="line">
					<input type="date" id="birthdate" name="birthdate" placeholder="Birth date" value="" required><!-- style="width:100%; font-size:16px; padding: 8px 0; margin: 8px 0; border-bottom: 1px solid #000000;" -->
					<select class="option" name="subject"> <!-- style="padding: 8px 0; margin: 8px 0; border-bottom: 1px solid #000000; -->
						<option disabled="disabled" selected="selected"></option>
						<option>Male</option>
						<option>Female</option>
					</select> <br/>
				</div>
				<div id="line">	
					<input type="email" id="email" name="email" placeholder="Email address">
					<input type="tel" id="phone" name="phone" placeholder="Phone number"><br/>
				</div>
				<div id="line">	
					<input type="password" id="password" name="password" placeholder="Password">
					<input type="password" id="verpassword" name="verpassword" placeholder="Repeat password"><br/>
				</div>
				<button class="registerbtn"><a href="Login.jsp">Register</a></button>
			</div>
		</form>
	</div>
	
	</div>
</body>
</html>