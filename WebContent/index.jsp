<%--
  Created by IntelliJ IDEA.
  User: mastro
  Date: 21/11/20
  Time: 19:44
  To change this template use File | Settings | File Templates.
--%>
<%-- <%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!-- dichiarazione e instanziazione di un loginBean !-->
<jsp:useBean id="loginBean" scope="request" class="jspexample.LoginBean"/>

<!-- mappare gli attributi di un oggetto sui campi della form -->
<jsp:setProperty name="loginBean" property="*"/>

<%
    if (request.getParameter("login") != null) {
        if (loginBean.validate()) {
%>
        <jsp:forward page="RiassuntoLogin.jsp"/>
<%
        } else {
%>
<p style="color: red">Dati errati</p>
<%
        }
    }
%> --%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>WelcomePage</title>
	<link rel="stylesheet" href="css/homepage.css"/>
	<link href="css/menu2.css" rel="stylesheet"/>
</head>


<body>
	<div class="curved">
		<h1>Hello</h1>
	</div>
	<div class="wrapper">
		<div class="center">
			<h1>Welcome to WhereToStudy</h1>
			<div class="buttons">
				<button><a href="SearchPage.jsp">Start looking for Rooms</a></button>
				<button class="btn"><a href="LoginForm.jsp">Login</a></button>
				
			</div>
		</div>
	</div>
	
	
</body>

</html>
