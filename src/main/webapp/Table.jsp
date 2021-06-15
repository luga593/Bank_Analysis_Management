<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<style>
table,th,td {
  border : 1px solid black;
  border-collapse: collapse;
}
th,td {
  padding: 5px;
}
</style>
<body>

<button type="button" onclick="loadXMLDoc()">Get table</button>
<br><br>
<table id="demo"></table>

<script>
function loadXMLDoc() {
  var xmlhttp = new XMLHttpRequest();
  xmlhttp.onreadystatechange = function() {
    if (this.readyState == 4 && this.status == 200) {
      myFunction(this);
    }
  };
  xmlhttp.open("GET", "http://localhost:8080/Topicus/tempxml", true);
  xmlhttp.send();
}
function myFunction(xml) {
  var i;
  var xmlDoc = xml.responseXML;
  var table="<tr><th>Account_id</th><th>IBAN</th><th>Description</th><th>Date</th><th>Value_Date</th>"
  				+ "<th>Opening_Amount</th><th>Transaction_Amount</th><th>Closing_Amount</th></tr>";
  var x = xmlDoc.getElementsByTagName("transactionfile");
  for (i = 0; i <x.length; i++) { 
	  
	   
	  for (j = 0; j <x[i].getElementsByTagName("process").length; j++) { 
		table += "<tr><td>" +
		x[i].getAttribute("accid") + "</td><td>" +
	    x[i].getElementsByTagName("process")[j].getAttribute("iban") + "</td><td>" + 
	    x[i].getElementsByTagName("process")[j].getAttribute("description")  + "</td><td>" +
		x[i].getAttribute("date") + "</td><td>" + 
		x[i].getElementsByTagName("process")[j].getAttribute("valuedate") + "</td><td>" + 
		x[i].getAttribute("startingamount") + "</td><td>" +
		x[i].getElementsByTagName("process")[j].getAttribute("transactionammount") + "</td><td>" + 
		x[i].getAttribute("closingammount") +
	    "</td></tr>";
	  }
  }
  document.getElementById("demo").innerHTML = table;
}
</script>

</body>
</html>