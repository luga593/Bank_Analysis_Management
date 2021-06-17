<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
    <style>
table,th,td {
  border : 1px solid black;
  border-collapse: collapse;
}
th,td {
    text-align: left;
  padding: 10px;
}

tr:hover {background-color: #f5f5f5;}

th {
    background-color: #3b4465 ;
}

#table


</style>
    <link href="https://unpkg.com/tabulator-tables@4.9.3/dist/css/tabulator.min.css" rel="stylesheet">
    <script type="text/javascript" src="https://unpkg.com/tabulator-tables@4.9.3/dist/js/tabulator.min.js"></script>
    <script type="text/javascript" src="https://oss.sheetjs.com/sheetjs/xlsx.full.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jspdf/1.3.5/jspdf.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jspdf-autotable/3.0.5/jspdf.plugin.autotable.js"></script>
</head>

<div>
    <button id="download-csv">Download CSV</button>
    <button id="download-json">Download JSON</button>
    <button id="download-xlsx">Download XLSX</button>
    <button id="download-pdf">Download PDF</button>
    <button id="download-html">Download HTML</button>
    <button id="lang-french">French</button>
    <button id="lang-dutch">Dutch</button>
    <button id="lang-default">Default (English)</button>
</div>
<body>

<button type="button" onclick="loadXMLDoc()">Get table</button>
<br><br>
<div id = "table"></div>

<script>

function loadXMLDoc() {
  var xmlhttp = new XMLHttpRequest();
  xmlhttp.onreadystatechange = function() {
    if (this.readyState === 4 && this.status === 200) {
      myFunction(this);
    }
  };
  xmlhttp.open("GET", "http://localhost:8080/Topicus_war_exploded/tempxml", true);
  xmlhttp.send();
}
function myFunction(xml) {
  var i;
  var xmlDoc = xml.responseXML;
  // var table="<tr><th>Trusted</th><th><tr><th>Account_id</th><th>IBAN</th><th>Description</th><th>Date</th><th>Value_Date</th>"
  // 				+ "<th>Opening_Amount</th><th>Transaction_Amount</th><th>Closing_Amount</th></tr>";
  var x = xmlDoc.getElementsByTagName("transactionfile");
  let tableData = []
  for (i = 0; i <x.length; i++) {
	  for (j = 0; j <x[i].getElementsByTagName("process").length; j++) { 
		// table += "<tr><td>" +
		// x[i].getAttribute("accid") + "</td><td>" +
	    // x[i].getElementsByTagName("process")[j].getAttribute("iban") + "</td><td>" +
	    // x[i].getElementsByTagName("process")[j].getAttribute("description")  + "</td><td>" +
		// x[i].getAttribute("date") + "</td><td>" +
		// x[i].getElementsByTagName("process")[j].getAttribute("valuedate") + "</td><td>" +
		// x[i].getAttribute("startingamount") + "</td><td>" +
		// x[i].getElementsByTagName("process")[j].getAttribute("transactionammount") + "</td><td>" +
		// x[i].getAttribute("closingammount") +
	    // "</td></tr>";
          let temp;
          temp = {accid: x[i].getAttribute("accid"),
          IBAN: x[i].getElementsByTagName("process")[j].getAttribute("iban"),
          Description: x[i].getElementsByTagName("process")[j].getAttribute("description"),
          Date: x[i].getAttribute("date"),
          ValueDate: x[i].getElementsByTagName("process")[j].getAttribute("valuedate"),
          StartingAmount: x[i].getAttribute("startingamount"),
              TransactionAmount: x[i].getElementsByTagName("process")[j].getAttribute("transactionammount"),
              ClosingAmount: x[i].getAttribute("closingammount"),
              isTrusted: x[i].getElementsByTagName("process")[j].getAttribute("iban") != null && (x[i].getAttribute("startingamount") - x[i].getAttribute("closingammount") < 600)
          }
          tableData.push(temp);
	  }
  }
  let table = new Tabulator("#table",{
      data: tableData,
      pagination:"local",
      paginationSize: 10,
      layout: "fitDataTable",
      langs:{
          "fr-fr":{ //French Definition Language
              "columns":{
                  "Account-ID" : "identifiant de compte",
                  "IBAN" : "IBAN",
                  "Description" : "la description",
                  "Date" : "Date",
                  "ValueDate" : "Date de valeur",
                  "StartingAmount" : "Solde d'ouverture",
                  "TransactionAmount" : "Montant traité",
                  "ClosingAmount" : "Solde de clôture",
                  "isTrusted" : "Transaction de confiance",
              },
              "pagination":{
                  "first":"Premier",
                  "last":"Dernier",
                  "prev":"Précédent",
                  "next":"Suivant",
              }
          },

          "nl-nl": {
              "columns":{
                  "Account-ID" : "Account ID",
                  "IBAN" : "IBAN",
                  "Description" : "Omschrijving",
                  "Date" : "Datum",
                  "Value Date" : "Waardedatum",
                  "StartingAmount" : "openingsbalans",
                  "TransactionAmount" : "verhandeld bedrag",
                  "ClosingAmount" : "eindsaldo",
                  "isTrusted" : "vertrouwde transactie",
              },
              "pagination":{
                  "first":"eerste",
                  "last":"laatste",
                  "prev":"vorige",
                  "next":"De volgende",

              }
          }
      },

      columns: [
          {title: "Account ID", field: "accid"},
          {title: "IBAN", field: "IBAN"},
          {title: "Description", field: "Description"},
          {title: "Date", field: "Date"},
          {title: "Value Date", field: "ValueDate"},
          {title: "Opening Balance", field: "StartingAmount"},
          {title: "Transacted Amount", field: "TransactionAmount"},
          {title: "Closing Balance", field: "ClosingAmount"},
          {title: "Trusted Transaction", field: "isTrusted", formatter: "tickCross", sorter: "boolean"}
      ]
  })

    document.getElementById("download-csv").addEventListener("click", function(){
        table.download("csv", "data.csv");
    });

    document.getElementById("download-json").addEventListener("click", function(){
        table.download("json", "data.json");
    });

    document.getElementById("download-xlsx").addEventListener("click", function(){
        table.download("xlsx", "data.xlsx", {sheetName:"My Data"});
    });

    document.getElementById("download-pdf").addEventListener("click", function(){
        table.download("pdf", "data.pdf", {
            orientation:"portrait", //set page orientation to portrait
            title:"Bank Statement Topicus", //add title to report
        });
    });

    document.getElementById("download-html").addEventListener("click", function(){
        table.download("html", "data.html", {style:true});
    });

    //set locale to French
    document.getElementById("lang-french").addEventListener("click", function(){
        table.setLocale("fr-fr");
    });

//set locale to Dutch
    document.getElementById("lang-dutch").addEventListener("click", function(){
        table.setLocale("nl-nl");
    });

//set default locale
    document.getElementById("lang-default").addEventListener("click", function(){
        table.setLocale("");
    });


}

</script>

</body>
</html>