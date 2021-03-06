<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.sql.*"%>
<%@ page import="java.util.Map"%>
<%@ page import="java.util.HashMap"%>
<%@ page import="java.util.LinkedHashMap"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%! String query1 = "SELECT CUSTOMER.CUSTOMER_ID, CUSTOMER.CUSTOMER_NAME "+ 
	    "FROM CUSTOMER INNER JOIN SALES_ORDER ON SALES_ORDER.CUSTOMER_ID = CUSTOMER.CUSTOMER_ID "+
	    "GROUP BY CUSTOMER.CUSTOMER_ID, CUSTOMER.CUSTOMER_NAME";
	String query2 = "SELECT SUM(SALES_ORDER_LINE_ITEM.QUANTITY) AS Quantity, "+ 
	    "BOM.OUTPUT_DESCRIPTION AS Product, SALES_ORDER.BILLING_DATE FROM SALES_ORDER_LINE_ITEM "+ 
	    "INNER JOIN sales_order ON SALES_ORDER_LINE_ITEM.SALES_ORDER_ID = SALES_ORDER.SALES_ORDER_ID "+ 
	    "INNER JOIN CUSTOMER ON CUSTOMER.CUSTOMER_ID = SALES_ORDER.CUSTOMER_ID "+
	    "INNER JOIN BOM ON BOM.EFFECTIVE_DATE = SALES_ORDER.DELIVERY_DATE "+ 
	    "AND BOM.OUTPUT_PRODUCT_ID = SALES_ORDER_LINE_ITEM.PRODUCT_ID "+ 
	    "WHERE CUSTOMER.CUSTOMER_ID = ? GROUP BY "+
	    "BOM.OUTPUT_DESCRIPTION, SALES_ORDER.BILLING_DATE";
	String query3 ="SELECT SIM_DATE.SIM_DATE FROM CURRENT_SIM_DATE "+
	    "INNER JOIN SIM_DATE ON CURRENT_SIM_DATE.CUR_NB_DAYS >= SIM_DATE.NB_DAYS "+
	    "WHERE VDAY <= MAX_DAYS_PER_ROUND";
%>
<% 	
	ResultSet clients = null;
	ResultSet products = null;
	ResultSet dates = null;
	String server = "avenger.hec.ca\\erpsim";
    String database = "ERPSIM";
    String password = "ERPSIM";
	String path = null;
	Map<String, Map<String, Integer>> productDateMap = new HashMap<>();
	Map<String, Integer> dateMap = new LinkedHashMap<>();;
	try{	
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        String connectionUrl = "jdbc:sqlserver://"+server+";databaseName="+database+";user=DAIRY1;password="+password+";";
        Connection connection = (Connection) DriverManager.getConnection(connectionUrl);
		Statement st = connection.createStatement();
		clients = st.executeQuery(query1);
		String client = request.getParameter("client");
		if(client != "" && client != null && !client.equals("&")){
			Statement st2 = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			products = st2.executeQuery(query2.replace("?", client));
			Statement st3 = connection.createStatement();
			dates =  st3.executeQuery(query3);
			if(dates != null && products != null){
				while(dates.next()){
					dateMap.put(dates.getString(1), 0);
				}
				while(products.next()){
					int quantity = products.getInt(1);
					String product = products.getString(2);
					String date = products.getString(3);
					Map<String, Integer> map = null;
					if(productDateMap.get(product) == null){
						productDateMap.put(product, new LinkedHashMap<>(dateMap));
						map = productDateMap.get(product);
						map.put(date, quantity);
					}else{
						map = productDateMap.get(product);
						if(map.get(date) != null){
							map.put(date, map.get(date)+quantity);
						}	
					}
					productDateMap.put(product, map);
				}
				products.beforeFirst();
			}	
		}
	}catch(Exception e){
		System.out.println("Could not connect to database");
		e.printStackTrace();
	}
%>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Sales Statistics</title>
	<style type="text/css">
		.header{
			position: fixed;
		    width: 100%;
		    height: 75px;
		    background-color: white;
		    padding: 0px;
		    margin: 0;
		}
		body{
			padding: 0px 10px;
			margin: 0px;
		}
		.spacer{
			width: 100%;
   		    height: 95px;
		}
		#customerDropDown{
			  -webkit-appearance: button;
			  -webkit-padding-end: 20px;
			  -webkit-padding-start: 2px;
			  background-image: url(http://i62.tinypic.com/15xvbd5.png),
			    -webkit-linear-gradient(#fafafa, #f4f4f4 40%, #e5e5e5);
			  background-position: 97% center;
			  background-repeat: no-repeat;
			  font-size: medium;
			  margin: 20px;
			  overflow: hidden;
			  padding: 5px 10px;
			  text-overflow: ellipsis;
			  white-space: nowrap;
			  width: 200px;
		}
		.button {
		    border: none;
		    padding: 16px 32px;
		    text-align: center;
		    text-decoration: none;
		    display: inline-block;
		    font-size: 16px;
		    margin: 4px 2px;
		    -webkit-transition-duration: 0.4s; /* Safari */
		    transition-duration: 0.4s;
		    cursor: pointer;
		    border-radius: 5px
		}
		.button1 {
		    background-color: white;
		    color: black;
		    border: 2px solid #e7e7e7;
		}
		.button1:hover {background-color: #e7e7e7;}
		.button2 {
		    background-color: #4CAF50;
		    color: white;
		    border: 2px solid #555555;
		}
		.button2:hover {
		    background-color: #555555;
		    color: white;
		}
		table {
		    border-collapse: collapse;
		    width: 100%;
		}
		th, td {
		    text-align: left;
		    padding: 8px;
		}
		th {
		    background-color: #4CAF50;
		    color: white;
		}
		tr:nth-child(even) {background-color: #f2f2f2;}
	</style>
	<script type="text/javascript">
		function init(){
			if(localStorage.getItem('selectedIndex') > 0){
				document.getElementById("customerDropDown").options[localStorage.getItem('selectedIndex')].selected = true;
				document.getElementById('btnTable').style.display = "inline-block";
				document.getElementById('btnGraph').style.display = "inline-block";
				if(localStorage.getItem('view')){
					graphView();
				}else{
					tableView();
				}
				var ctx = document.getElementById('myChart').getContext('2d');
				var options = {
						scales: {
							xAxes: [ { ticks: { autoSkip: false }
								 } ]
							}
				};
				var donnees = {
					    labels: [
					    	<%int noOfDates = dateMap.size(); 
					    	  int counter1 = 0;	
					    	  for(Map.Entry<String, Integer> date : dateMap.entrySet()){%>
					    	  	<%="'"+date.getKey()+"'"%>
					    	  	<%if(counter1 +1 < noOfDates){counter1++;%>
					    	  		,
					    	<%}}%>		
					    ],
					    datasets: [
					    	<%int noOfProducts = productDateMap.size(); 
					    	  int counter2 = 0;	
					    	  for(Map.Entry<String, Map<String,Integer>> product : productDateMap.entrySet()){%>
					    	  {
							      label: '<%=product.getKey()%>',
							      data:[
							    	  <% int counter3 = 0;
							    	  for(Map.Entry<String, Integer> date : product.getValue().entrySet()){
							    	  %>
							    	  <%=date.getValue()%>
							    	  <%if(counter3 +1 < noOfDates){counter3++;%>
							    	  ,
							    	  <%}}%>
							      ]}
					    	  	<%if(counter2 +1 < noOfProducts){counter2++;%>
					    	  		,
					    	<%}}%>
					    ]
					  };
				var myChart = new Chart(ctx, {
					  type: 'line',
					  data: donnees,
					  options: options
					}); 
			}else{
				document.getElementById('btnTable').style.display = "none";
				document.getElementById('btnGraph').style.display = "none";
			}
		}
		
		function fetchClientDetails(e){
			var selectedValue= e.options[e.selectedIndex].value;
			localStorage.setItem('selectedIndex',e.selectedIndex);
			if(e.selectedIndex > 0){
				window.location.replace("stats.jsp?client=" + selectedValue);
			}else{
				window.location.replace("stats.jsp?client=&");
			}
		}
		
		function tableView(){
			document.getElementById('tableView').style.display = "block";
			document.getElementById('graphView').style.display = "none";
			document.getElementById('btnTable').classList.add("button1");
			document.getElementById('btnGraph').classList.add("button2");
			document.getElementById('btnTable').classList.remove("button2");
			document.getElementById('btnGraph').classList.remove("button1");
			localStorage.removeItem('view');
		}
		
		function graphView(){
			document.getElementById('tableView').style.display = "none";
			document.getElementById('graphView').style.display = "block";
			document.getElementById('btnTable').classList.add("button2");
			document.getElementById('btnTable').classList.add("button1");
			document.getElementById('btnGraph').classList.add("button1");
			document.getElementById('btnGraph').classList.remove("button2");
			localStorage.setItem('view','graph');
		}
	</script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.6.0/Chart.min.js"></script>
</head>
<body onload="init();">
	<div class="header">
		<label for="customerDropDown" style="font-size: medium; font-weight: bold;">Client : </label>
		<select id="customerDropDown" name="customerDropDown" onchange="fetchClientDetails(this);">
			<option value="&" selected>--Choose Client--</option>
			<%if(clients != null){ 
	            while(clients.next()){%>
					<option value="<%=clients.getString(1) %>"><%=clients.getString(2) %></option>
			<%}} %>
		</select> 
		<input type="button" id="btnTable" value="Table" class="button" onclick="tableView();"/> 
		<input type="button" id="btnGraph" value="Graph" class="button" onclick="graphView();"/>
	</div>
	<div class="spacer">
    	&nbsp;
	</div>
	<%if(products != null){ %>
	<div id="tableView">
		<table>
			<tr>
				<th>Date</th>
				<th>Product</th>
				<th>Quantity</th>
			</tr>
	           <% while(products.next()){%>
			<tr>
				<td style="font-weight: bold; "><%=products.getString(3) %></td>
				<td><%=products.getString(2) %></td>
				<td><%=products.getString(1) %></td>
			</tr>
			<%}}%>
		</table>
	</div>
	<div id="graphView">
		<canvas id="myChart" width='600' height='400'></canvas>
	</div>
</body>
</html>