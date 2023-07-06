<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="com.enrtities.Note"%>
<%@page import="org.hibernate.Transaction"%>
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
	
	SessionFactory sf = FactoryProvider.getFactory();
	Session s = sf.openSession();

	int noteId = Integer.parseInt(request.getParameter("noteId").trim());
	Note n = s.get(Note.class, noteId);
	
	%>
	<div class="container">


			<form action="updateServlet" method="post"> 
				<div class="form-group">
				<input type="hidden" name="id" value="<%= n.getId() %>"/>
					<label for="TitleName">Note Title</label> <input type="text"
						name="title" required class="form-control" id="title"
						aria-describedby="titlebody" placeholder="Enter Note Title"
						value="<%= n.getTitle() %>">

				</div>
				<div class="form-group">
					<label for="content">Note Content</label>
					<textarea name="content"  required class="form-control"
					
						style="height: 300px;" placeholder="Write your content here...."
						><%= n.getContent() %></textarea>
				</div>
				<div class="container text-center">
					<button type="submit" class="btn btn-success">Update</button>
				</div>
			</form>


		</div>

</body>
</html>