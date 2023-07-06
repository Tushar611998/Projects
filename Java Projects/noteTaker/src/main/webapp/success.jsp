<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Success page</title>
<%@include file="all_js_css.jsp"%>
</head>
<body>
	<%@include file="Navbar.jsp"%>
	<br>

	<main class="">

		<div clss="container text-center ">

			<div class="row">

				<div class="col-md-4 offset-4 mt-2">
					<div class="card">
						<img class="card-img-top" src="img/success.png"
							alt="Card image cap">
						<div class="card-body">
							<h5 class="card-title text-center">Successfully Added</h5>
							<p class="card-text text-center">Your note has been added to
								your todo list Succcessfully..!!!</p>

							<div class="container text-center">
								<a href="allNotes.jsp" class="btn btn-primary">View All
									Notes</a>
							</div>
						</div>
					</div>

				</div>
			</div>



		</div>


	</main>



</body>
</html>