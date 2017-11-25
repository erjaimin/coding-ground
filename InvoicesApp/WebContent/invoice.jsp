<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="tp1.css">
</head>
<body>
	<c:set var="details" value="${requestScope.invoice}" />
	<table>
		<tr>
			<td class='logo'><img height='40px' src='at.png' /><td>
			<td class='adresse-co'>Technologies AT
				<br>3000 Ch. Côte-Ste-Catherine
				<br>Montréal (Québec) Canada
				<br>H3T 3A7
			</td>
			<td class='no-facture'>FACTURE ${details.invoiceNo}
				<br>${details.invoiceDate}
			</td>
		</tr>
</table>
<table>
<tr>
	<th class='titre-adresse-cl'>FACTURÉ À</th>
	<th class='titre-adresse-cl'>LIVRÉ À</th>
</tr>
<tr>
	<td class='adresse-cl'>${details.factAddress.nomClient}
	    <br>${details.factAddress.adresse}
	    <br>${details.factAddress.ville} (${details.factAddress.province}) ${details.factAddress.pays}
	    <br>${details.factAddress.codePostal}
    </td>
	<td class='adresse-cl'>${details.livrAddress.nomClient}
	    <br>${details.livrAddress.adresse}
	    <br>${details.livrAddress.ville} (${details.livrAddress.province}) ${details.livrAddress.pays}
	    <br>${details.livrAddress.codePostal}
    </td>
</tr>
</table>

<table class='details'>
	<tr class='gris'>
		<th class='qte blanc'>Qté</th>
		<th class='desc blanc'>Description</th>
		<th class='prix blanc'>Prix Unit.</th>
		<th class='total blanc'>Total</th>
	</tr>

	<c:forEach items="${details.items}" var="item">
		<tr class='gris'>
    		<td class='droite qte'>${item.quantité}</td>
    		<td class='desc'>${item.produit}</td>
    		<td class='droite prix'>${item.prixUnitaire}</td>
    		<td class='droite total'>${item.formattedSubtotal}</td>
   		 </tr>
	</c:forEach>
	<tr>
		<td colspan='3' class='droite final'>Sous-Total</td><td class='droite total'>${details.formattedSubtotal}</td>
	</tr>
	<tr>
		<td colspan='3' class='droite final'>TPS/TVH</td><td class='droite total'>${details.formattedTax1}</td>
	</tr>
	<tr>
		<td colspan='3' class='droite final'>TVP</td><td class='droite total'>${details.formattedTax2}</td>
	</tr>
	<tr>
		<th colspan='3' class='droite final'>Total</th><th class='droite total'>${details.formattedTotal}</th>
	</tr>
</table>
<p>Payable 30 jours</p>
<p>
	Numéro TPS/TVH : 12345678910
	<br>Numéro TVP : 12345678910
</p>
</body>
</html>