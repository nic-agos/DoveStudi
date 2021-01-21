<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix = "c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix = "sql"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>My Rooms</title>

</head>
<body>
	<sql:setDataSource var = "snapshot" driver = "com.mysql.jdbc.Driver"
         url = "jdbc:mysql://localhost:3306/dovestudi"
         user = "root" password = "password"/>
 
      <sql:query dataSource = "${snapshot}" var = "result">
         SELECT * from room;
      </sql:query>
 
      <table border = "1" width = "100%">
         <tr>
            <th>CF</th>
            <th>First Name</th>
            <th>Last Name</th>
            <th>Email</th>
         </tr>
         
         <c:forEach var = "row" items = "${result.rows}">
            <tr>
               <td><c:out value = "${row.CF}"/></td>
               <td><c:out value = "${row.Name}"/></td>
               <td><c:out value = "${row.Surname}"/></td>
               <td><c:out value = "${row.Email}"/></td>
            </tr>
         </c:forEach>
      </table>
</body>
</html>