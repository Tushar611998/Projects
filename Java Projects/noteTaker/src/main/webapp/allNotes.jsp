<%@page import="com.enrtities.Note"%>
<%@page import="java.util.List"%>
<%@page import="org.hibernate.query.*"%>
<%@page import="org.hibernate.Session"%>
<%@page import="org.hibernate.SessionFactory"%>
<%@page import="com.helper.FactoryProvider"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>All Notes</title>
<%@include file="all_js_css.jsp"%>
</head>
<body>
<div class="container">
<%@include file="Navbar.jsp"%>
	<br>
	</div>
	
	<h1 class="container ">All Notes:</h1>
	
	<%
	
	SessionFactory sf= FactoryProvider.getFactory();
	Session s = sf.openSession();
	
	Query q = s.createQuery("from Note");
	
	List<Note> l = q.list();
	
	for(Note n : l){
		
	%>
		
		<main>

		<div class="container mb-3">
			<div class="row">
				<div class="col">
					<div class="card">
					<div class="container text-center mt-2"  style="max-width:120px">
						<img class="card-img-top" src="img/notes.png" alt="Card image cap">
						</div>
						<div class="card-body">
							<h2 class="card-title"><%= n.getTitle() %></h2>
							<p class="card-text"><%= n.getContent() %></p>
								<div class="container text-center">
							<a href="delete.jsp?noteId=<%= n.getId() %>" class="btn btn-danger">Delete</a>
							<a href="update.jsp?noteId=<%= n.getId() %>" class="btn btn-primary">Update</a>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>

	</main>
		
		<% 
	}
	
	s.close();
	
	%>
	

</body>
</html>