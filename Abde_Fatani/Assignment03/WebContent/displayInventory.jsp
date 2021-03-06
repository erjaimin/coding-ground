<!DOCTYPE html>
<%@page import="org.mcgill.ccs2_505.assignment02.inventory.InventoryEntry"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="org.mcgill.ccs2_505.assignment02.inventory.Inventory" %>
<%! Inventory inventory;
	public void jspInit() {
		inventory = (Inventory) getServletContext().getAttribute("inventory");
		if (inventory == null)
			System.out.println("Couldn’t load inventory.");
	}%>
<html>
<head>
	<style>
		table, th, td {
		    border: 1px solid black;
		}
	</style>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>McGill Book Store Inventory System</title>
</head>
<body>
	<h1>McGill Book Store Inventory System</h1>
	<c:if test="${not empty errorMsg}">
  			 <p style="color: #D8000C; font-weight: bold;"><c:out value="${errorMsg}"/></p>
	</c:if>
	<h2>Books</h2>
	<table>
	  <tr>
	  	<th>UID</th>
	  	<th>QTY</th>
	  	<th>Price</th>
	  	<th>Author</th>
	  </tr>	
	  <% if(inventory != null){ 
	  		for(String productId : inventory.getProductIDs()){
	  			InventoryEntry entry = inventory.get(productId); 
	  			%>
	  			<c:set var="inventoryEntry" value="<%=entry%>"/>
	  			<c:set var="productType" value="<%=entry.getProductType()%>"/>
	  			<c:if test="${productType eq 'BOOK'}">
	  				<c:set var="product" value="${inventoryEntry.product}" />
		  			<tr>
				      <td><c:out value="${product.ID}" /></td>
				      <td><c:out value="${inventoryEntry.quantity}" /></td>
				      <td><c:out value="${product.price}" /></td>
				      <td><c:out value="${product.author}" /></td>
				    </tr>
	  			</c:if>
	  			
	  	<%}}%>
	</table>
	<br>
	<h2>Compact Discs</h2>
	<table>
	  <tr>
	  	<th>UID</th>
	  	<th>QTY</th>
	  	<th>Price</th>
	  	<th>Artist</th>
	  </tr>	
	  <% if(inventory != null){ 
	  		for(String productId : inventory.getProductIDs()){
	  			InventoryEntry entry = inventory.get(productId); 
	  			%>
	  			<c:set var="inventoryEntry" value='<%=entry%>'/>
	  			<c:set var="productType" value="<%=entry.getProductType()%>"/>
	  			<c:if test="${productType eq 'COMPACTDISC'}">
	  				<c:set var="product" value="${inventoryEntry.product}" />
		  			<tr>
				      <td><c:out value="${product.ID}" /></td>
				      <td><c:out value="${inventoryEntry.quantity}" /></td>
				      <td><c:out value="${product.price}" /></td>
				      <td><c:out value="${product.artist}" /></td>
				    </tr>
	  			</c:if>
	  			
	  	<%}}%>
	</table>
	<p>Back to the <a href="form.html">form</a>.</p>
	<%
		if (request.getUserPrincipal().getName() != null) {
			HttpSession oldSession = request.getSession();
			String logout = request.getContextPath() + "/logout.jsp";
	%>
	<input type="button" id="logout" value="Logout" onclick="location.href = '<%=logout%>';" />
	<% } %>
</body>
</html>