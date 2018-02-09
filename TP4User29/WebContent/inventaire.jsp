<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.sql.*"%>
<%@ page import="java.util.List"%>
<%@ page import="daa.a2017.user29.modele.Voiture"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/css/bootstrap.min.css" >
	<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.11.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/js/bootstrap.min.js"></script>
	<title>Inventaire</title>
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
				<h3>Notre inventaire</h3>
			</div>
		</div>
		<div class="row">
				<%
					List<Voiture> voitureListe = (List<Voiture>)request.getAttribute("inventaire");
					for(Voiture voiture : voitureListe){
				%>
					<div class="col-sm-4">
						<img src="http://images.clipartpanda.com/vehicle-clipart-RcdKLLoc9.png" width="100%">
						ID: <%=voiture.getId()%><br>Marque: <%=voiture.getMarque()%><br>Modèle: <%=voiture.getModèle()%><br>Année: <%=voiture.getAnnée()%><br>Nombre porte: <%=voiture.getNbPorte()%><br>
						<a href="MiseAJour?id=<%=voiture.getId()%>">Mise à jour</a> - 
						<a href="SupprimeVoiture?id=<%=voiture.getId()%>">Supprimer</a>
						<br/><br/>
					</div>
				<%} %>
		</div>
	</div>
</body>
</html>