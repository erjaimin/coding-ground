<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<link rel="stylesheet" type="text/css" href="tp1.css">
		<title>Facture</title>
	</head>
	<body>
		<form method="post" enctype="multipart/form-data" action="InvoicesController">
			Numéro première facture : <input type="text" name="noPremiereFacture"/><br>
			Date de facturation : <input type="text" name="dateFacture"/><br>
			Données facturation : <input type="file" name="donneesFacturation"/> <br>
			<input type="submit" value="Produire factures" />
		</form>
		<c:if test="${not empty errorMessage}">
  			 <p style="color: #D8000C;"><c:out value="${errorMessage}"/></p>
		</c:if>
	</body>
</html>