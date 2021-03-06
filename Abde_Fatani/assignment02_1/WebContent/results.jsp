<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
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
	<c:set var = "booksList" value = "${booksList}"/>
	<c:set var = "discsList" value = "${discsList}"/>
	<h1>McGill Book Store Inventory System</h1>
	
	<h2>Books</h2>
	<table>
	  <tr>
	  	<th>UID</th>
	  	<th>QTY</th>
	  	<th>Price</th>
	  	<th>Author</th>
	  </tr>	
	  <c:choose>
	  	<c:when test="${not empty booksList}">
	  		<c:forEach items="${booksList}" var="bookEntry" varStatus="status">
			  	<c:set var="book" value="${bookEntry.product}" />
			    <tr>
			      <td><c:out value="${book.id}" /></td>
			      <td><c:out value="${bookEntry.quantity}" /></td>
			      <td><c:out value="${book.price}" /></td>
			      <td><c:out value="${book.author}" /></td>
			    </tr>
		  </c:forEach>
	  	</c:when>
	  	<c:otherwise>
	  		<tr>
			    <td><c:out value="No books found"/></td>
			</tr>
	  	</c:otherwise>
	  </c:choose>
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
	  <c:choose>
	  	<c:when test="${not empty discsList}">
	  		<c:forEach items="${discsList}" var="discEntry" varStatus="status">
			  	<c:set var="disc" value="${discEntry.product}" />
			    <tr>
			      <td><c:out value="${disc.id}" /></td>
			      <td><c:out value="${discEntry.quantity}" /></td>
			      <td><c:out value="${disc.price}" /></td>
			      <td><c:out value="${disc.artist}" /></td>
			    </tr>
		  </c:forEach>
	  	</c:when>
	  	<c:otherwise>
	  		<tr>
			    <td><c:out value="No discs found"/></td>
			</tr>
	  	</c:otherwise>
	  </c:choose>
	</table>
	<c:if test="${not empty errorMsg}">
  			 <p style="color: #D8000C; font-weight: bold;"><c:out value="${errorMsg}"/></p>
	</c:if>
	<p>Back to the <a href="form.html">form</a>.</p>
</body>
</html>