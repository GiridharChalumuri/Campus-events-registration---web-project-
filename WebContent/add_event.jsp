<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8">
    <meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">
    
    <!-- bootstrap general -->

	<!-- <link href="bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css" /> -->
	<script type="text/javascript" src="bootstrap/js/bootstrap.min.js"></script>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Add events</title>
<!-- Bootstrap core CSS -->
<link href="bootstrap/css/bootstrap.min.css" rel="stylesheet">

<!-- Custom styles for this template -->
<link href="bootstrap/css/shop-homepage.css" rel="stylesheet">

</head>
<body>
    <!-- Navigation -->
    <nav class="navbar navbar-expand-lg navbar-dark bg-dark fixed-top">
      <div class="container">
        <a class="navbar-brand" href="#">Reserve Tickets</a>
        
        
      </div>
    </nav>

<%
//allow access only if session exists
String user = null;
		if(session.getAttribute("user") == null || session.getAttribute("user") !="root"){
			response.sendRedirect("login.jsp");
		}
if(session.getAttribute("user") == null){
	response.sendRedirect("login.html");
}else user = (String) session.getAttribute("user");
String userName = null;
String sessionID = null;
Cookie[] cookies = request.getCookies();
if(cookies !=null){
for(Cookie cookie : cookies){
	if(cookie.getName().equals("user")) userName = cookie.getValue();
	if(cookie.getName().equals("JSESSIONID")) sessionID = cookie.getValue();
}
}
%>
<center>
<h4>Hi <%=userName %>, Login successful.</h4>
<br>
User=<%=user %>
<br>
<a href="logout.jsp">logout</a>

<form name="addform" action="AdminController" method="post" onsubmit="" >
	<br>
		
	
	Event topic: <input type="text" name="title" id="title"><br>
	Type: <input type="text" name="type" id="type"><br>
	Date: <input type="date" pattern="yyyy/MM/dd" name="date" id="date"><br>
	Location: <input type="text" name="loc" id="loc"><br>
	Price: <input type="text" name="price" id="price"><br>
	EventTime: <input type="text" name="time" id="time"><br>
	Description: <input type="text" name="desc" id="desc"><br>
	
	<input type="submit" name="submit" id="add_event" value="add_event"><br>
	
	
</form>
</center>

	<!-- Footer -->
    <footer class="py-5 bg-dark fixed-bottom">
      <div class="container">
        <p class="m-0 text-center text-white">Copyright &copy; Group:5</p>
      </div>
      <!-- /.container -->
    </footer>

    <!-- Bootstrap core JavaScript -->
    <script src="bootstrap/js/jquery.min.js"></script>
    <script src="bootstrap/js/bootstrap.bundle.min.js"></script> 

</body>
</html>