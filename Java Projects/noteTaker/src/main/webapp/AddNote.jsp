<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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

		<div class="container">


			<form action="SaveServlet" method="post"> 
				<div class="form-group">
					<label for="TitleName">Note Title</label> <input type="text"
						name="title" required class="form-control" id="title"
						aria-describedby="titlebody" placeholder="Enter Note Title">

				</div>
				<div class="form-group">
					<label for="content">Note Content</label>
					<textarea name="content" required class="form-control"
						style="height: 300px;" placeholder="Write your content here...."></textarea>
				</div>
				<div class="container text-center">
					<button type="submit" class="btn btn-success">Add</button>
				</div>
			</form>


		</div>

	</div>

</body>
</html>