<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="daa.a2017.user29.modele.Voiture"%>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Inventaire</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/css/bootstrap.min.css" >
	<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.11.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/js/bootstrap.min.js"></script>
	<link rel="stylesheet" href="tp4.css">
</head>
<body>
	<div class="container">
		<nav class="navbar navbar-default navbar-expand-md fixed-top bg-white border">
			<div class="navbar-header">
				<button type="button" class="navbar-toggler" data-toggle="collapse" data-target="#myNavbar" aria-contros='myNavBar' aria-expanded='false'>
					<img src='menu.jpg' />
				</button>
				<a class="navbar-brand" href="Inventaire">
					Vendu!
				</a>
			</div>
			<div class="collapse navbar-collapse" id="myNavbar">
				<ul class="nav menu-bar">
					<li class='nav-item'>
						<a class='nav-link' href='Inventaire'>Inventaire</a>
					</li>
					<li class='nav-item'>
						<a class='nav-link' href='AjoutVoiture'>Ajouter voiture</a>
					</li>
				</ul>
			</div>
		</nav>
		<br>
		
		<div class="row">
			<div class="col-sm-12">
				<h3>Mise à jour</h3>
			</div>
		</div>
		<%if(request.getAttribute("errorMsg") != null) {%>
			<div>
				<p style="color: #D8000C; font-weight: bold;"><%=request.getAttribute("errorMsg").toString() %></p>
			</div>
		<%} %>
		<div class="row">
			<form action="MiseAJour" method="post" role="form">
			  <% Voiture voiture = (Voiture)request.getAttribute("voiture"); %>
			  <div class="form-group">
			    <label for="text">Marque:</label>
			    <input type="text" class="form-control" name="marque" id="marque" value="<%=(voiture != null) ? voiture.getMarque() : "" %>">
			  </div>
			  <div class="form-group">
			    <label for="text">Modèle:</label>
			    <input type="text" class="form-control" name="modele" id="modele" value="<%=(voiture != null) ? voiture.getModèle() : "" %>">
			  </div>
			  <div class="form-group">
			    <label for="text">Année:</label>
			    <input type="text" class="form-control" name="annee" id="annee" value="<%=(voiture != null) ? voiture.getAnnée(): "" %>">
			  </div>
			  <div class="form-group">
			    <label for="text">Nombre de porte:</label>
			    <input type="text" class="form-control" name="nbPorte" id="nbPorte" value="<%=(voiture != null) ? voiture.getNbPorte() : "" %>">
			  </div>
			  <input type="hidden" name="id" value="<%=(voiture != null) ? voiture.getId() : "" %>">
			  <button type="submit" class="btn btn-default">Mise à jour</button>
		</form>
		</div>
	</div>
</body>
</html>