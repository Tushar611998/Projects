<%@page import="org.hibernate.Transaction"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@page import="com.enrtities.Note"%>
<%@page import="java.util.List"%>
<%@page import="org.hibernate.Query"%>
<%@page import="org.hibernate.Session"%>
<%@page import="org.hibernate.SessionFactory"%>
<%@page import="com.helper.FactoryProvider"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<%@include file="all_js_css.jsp"%>
</head>
<body>

<div class="container">
<%@include file="Navbar.jsp"%>
	<br>
	</div>
	
	<%
	
	SessionFactory sf= FactoryProvider.getFactory();
	Session s = sf.openSession();
	
	int noteId = Integer.parseInt(request.getParameter("noteId").trim());
	Transaction t = s.beginTransaction();
	Note n = s.get(Note.class,noteId);
	
	s.delete(n);
	t.commit();
	
	
	s.close();
	
	response.sendRedirect("allNotes.jsp");
	
	
	
	
		
	%>

</body>
</html>