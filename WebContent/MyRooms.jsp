<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix = "c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix = "sql"%>
<%@ page import="logic.model.dao.RoomDAOImpl"%>
<%@ page import="logic.bean.RoomBean"%>
<%@ page import="java.util.*"%>
<%@ page import="java.util.ArrayList"%>

<%
	RoomDAOImpl dao = new RoomDAOImpl();
	List<RoomBean> listRoom;
	listRoom = dao.getAllRooms();
	request.setAttribute("listRoom", listRoom);
%>


<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>My Rooms</title>

</head>
<body>
    <c:forEach items="${listRoom}" var="listRoom">
    	${listRoom.id}
    	<br>
   		${listRoom.name}
    	<br>
    	${listRoom.address}
    	<br>
    	${listRoom.numPartecipants}
    	<br>
    	${listRoom.numAvailableSeats}
    	<br>
    	${listRoom.owner}
    	<br>
    	${listRoom.specification}
    	<br>
    	<br>
    <%-- <c:forEach items="${listRoom.name}" var="String">
        ${String}
    </c:forEach> --%>
	</c:forEach>
</body>
</html>