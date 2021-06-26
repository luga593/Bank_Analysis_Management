
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<title>Tables</title>

<head>
<link
	href="https://unpkg.com/tabulator-tables@4.9.3/dist/css/tabulator.min.css"
	rel="stylesheet">
<script type="text/javascript"
	src="https://unpkg.com/tabulator-tables@4.9.3/dist/js/tabulator.min.js"></script>
<script type="text/javascript"
	src="https://oss.sheetjs.com/sheetjs/xlsx.full.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/jspdf/1.3.5/jspdf.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/jspdf-autotable/3.0.5/jspdf.plugin.autotable.js"></script>
</head>

<body onload="loadXMLDoc()">
	<jsp:include page="base.jsp" />
	<div id="main">
		<h2>TABLE VIEW</h2>

		<div class = "column">
			<h2>Description</h2>
			<p>This section allows you to view your most recently uploaded file, this section has all features of the table
				in "Processes & Transactions". You can select what uploaded files you would like to view without
			having to re-upload it again.</p>
		</div>

		<div id="container-table"></div>
		<%--			languages--%>
		<ul>
			<button id="lang-french">French</button>
			<button id="lang-dutch">Dutch</button>
			<button id="lang-default">Default (English)</button>
		</ul>

		<div id="table"></div>
		<div id="ListOfFiles"></div>

		<div>
			<%--			downloads--%>
			<ul>
				<button class = "csv"  id="download-csv">Download CSV</button>
				<button class = "json" id="download-json">Download JSON</button>
				<button id="download-xlsx">Download XLSX</button>
				<button id="download-pdf">Download PDF</button>
				<button id="download-html">Download HTML</button>
				<button id="print-table">Print Table</button>

			</ul>
		</div>

		<br>
		<br>

	</div>
</body>

<<style>
	<jsp:include page ="baseStyle.css"/>
	<jsp:include page ="tableStyle.css"/>


	.column {
		margin: auto;
		justify-content: space-evenly;
		text-align: center;
		color: white;
		float: none;
		width: 50%;
		padding: 5px;
		border: 3px solid #F9E805;
		border-radius: 10px;
	}

	#table {
		left: 200px;
		width: 75%;
		max-width: 100%;
		border-radius: 15px;
		margin: auto;
	}


	.tabulator .tabulator-header {
		position: relative;
		box-sizing: border-box;
		width: 100%;
		border-bottom: 1px solid #999;
		background-color: #F9E805;
		color: #555;
		font-weight: bold;
		white-space: nowrap;
		overflow: hidden;
		-moz-user-select: none;
		-khtml-user-select: none;
		-webkit-user-select: none;
		-o-user-select: none;
	}

	button {
		display: none;
		border-radius: 15px;
		border: 3px solid #F9E805 ;
		background-color: var(--bkg-color);
		color: white;

	}

	button:hover{
		background-color: #F9E805;
		color: black;
		border: 3px solid #F9E805;
		transition: 0.4s;
	}

</style>

<script>
	<jsp:include page="WEB-INF/JS/darkTheme.js"/>
	<jsp:include page="WEB-INF/JS/sideNav.js"/>

	function showButtons() {
		var buttons = document.getElementsByTagName("button");

		for (var i = 0; i < buttons.length; i++) {
			buttons[i].style.display = "inline";
		}
	}

	function loadXMLDoc() {
		var xmlhttp = new XMLHttpRequest();
		xmlhttp.onreadystatechange = function() {
			if (this.readyState === 4 && this.status === 200) {
				myFunction(this);
			}
		};
		xmlhttp.open("GET", "http://localhost:8080/Topicus/singular", true);
		// xmlhttp.open("GET", "http://topicus-bank1.paas.hosted-by-previder.com/Topicus/singular", true);
		xmlhttp.send();

		showButtons();
	}
	function myFunction(xml) {
		var i;
		var xmlDoc = xml.responseXML;

		var x = xmlDoc.getElementsByTagName("transactionfile");
		//var a = []
		let tableData = []
		for (i = 0; i < x.length; i++) {
			for (j = 0; j < x[i].getElementsByTagName("process").length; j++) {
				let temp;
				temp = {
					accid : x[i].getAttribute("accid"),
					IBAN : x[i].getElementsByTagName("process")[j]
							.getAttribute("iban"),
					CreditDebit : x[i].getElementsByTagName("process")[j]
							.getAttribute("creditdebit"),
					Description : x[i].getElementsByTagName("process")[j]
							.getAttribute("description"),
					Date : x[i].getAttribute("date"),
					ValueDate : x[i].getElementsByTagName("process")[j]
							.getAttribute("valuedate"),
					StartingAmount : x[i].getAttribute("startingamount"),
					TransactionAmount : x[i].getElementsByTagName("process")[j]
							.getAttribute("transactionammount"),
					ClosingAmount : (parseFloat(x[i]
							.getAttribute("startingamount")) + parseFloat(x[i]
							.getElementsByTagName("process")[j]
							.getAttribute("transactionammount"))).toFixed(2),
					PartyName : x[i].getElementsByTagName("process")[j]
							.getAttribute("partyname"),
					isTrusted : x[i].getElementsByTagName("process")[j]
									.getAttribute("iban") != null
							&& (x[i].getAttribute("closingammount")
									- x[i].getAttribute("startinggamount") != x[i]
											.getElementsByTagName("process")[j]
											.getAttribute("transactionammount"))
				}
				tableData.push(temp);
			}

			//a.push([ x[i].getAttribute("filename"),
			//				x[i].getAttribute("time") ]);
			//console.log(a[i][0],a[i][1]);
		}
	//	var choose = document.getElementById("filter-value");
	//	let array = Array.from(new Set(a));
		//console.log(a.length);
	//	for (index = 0; index < array.length; index++) {
	//		choose.options[choose.options.length] = new Option(
	//				array[index][0], index);
	//}
		
		let table = new Tabulator("#table", {
			printAsHtml: true,
			data : tableData,
			pagination : "local",
			paginationSize : 10,
			layout : "fitDataTable",
			langs : {
				"fr-fr" : { //French Definition Language
					"columns" : {
						"Account-ID" : "identifiant de compte",
						"IBAN" : "IBAN",
						"CreditDebit" : "Crédit ou débit",
						"Description" : "la description",
						"Date" : "Date",
						"ValueDate" : "Date de valeur",
						"StartingAmount" : "Solde d'ouverture",
						"TransactionAmount" : "Montant traité",
						"ClosingAmount" : "Solde de clôture",
						"PartyName" : "Nom de la fête",
						"isTrusted" : "Transaction de confiance",
					},
					"pagination" : {
						"first" : "Premier",
						"last" : "Dernier",
						"prev" : "Précédent",
						"next" : "Suivant",
					}
				},

				"nl-nl" : {
					"columns" : {
						"Account-ID" : "Account ID",
						"IBAN" : "IBAN",
						"CreditDebit" : "Credit of debet",
						"Description" : "Omschrijving",
						"Date" : "Datum",
						"ValueDate" : "Waardedatum",
						"StartingAmount" : "openingsbalans",
						"TransactionAmount" : "verhandeld bedrag",
						"ClosingAmount" : "eindsaldo",
						"PartyName" : "Feest naam",
						"isTrusted" : "vertrouwde transactie",
					},
					"pagination" : {
						"first" : "eerste",
						"last" : "laatste",
						"prev" : "vorige",
						"next" : "De volgende",

					}
				}
			},

			columns : [ {
				title : "Account ID",
				field : "accid"
			}, {
				title : "IBAN",
				field : "IBAN"
			}, {
				title : "Credit Or Debit",
				field : "CreditDebit"
			}, {
				title : "Description",
				field : "Description"
			}, {
				title : "Date",
				field : "Date"
			}, {
				title : "Value Date",
				field : "ValueDate"
			}, {
				title : "Opening Balance",
				field : "StartingAmount"
			}, {
				title : "Transacted Amount",
				field : "TransactionAmount"
			}, {
				title : "Closing Balance",
				field : "ClosingAmount"
			}, {
				title : "Party Name",
				field : "PartyName"
			}, {
				title : "Trusted Transaction",
				field : "isTrusted",
				formatter : "tickCross",
				sorter : "boolean"
			} ]
		})
		document.getElementById("download-csv").addEventListener("click",
				function() {
					table.download("csv", "data.csv");
				});

		document.getElementById("download-json").addEventListener("click",
				function() {
					table.download("json", "data.json");
				});

		document.getElementById("download-xlsx").addEventListener("click",
				function() {
					table.download("xlsx", "data.xlsx", {
						sheetName : "My Data"
					});
				});

		document.getElementById("download-pdf").addEventListener("click",
				function() {
					table.download("pdf", "data.pdf", {
						orientation : "portrait", //set page orientation to portrait
						title : "Bank Statement Topicus", //add title to report
					});
				});

		document.getElementById("download-html").addEventListener("click",
				function() {
					table.download("html", "data.html", {
						style : true
					});
				});

		//set locale to French
		document.getElementById("lang-french").addEventListener("click",
				function() {
					table.setLocale("fr-fr");
				});

		//set locale to Dutch
		document.getElementById("lang-dutch").addEventListener("click",
				function() {
					table.setLocale("nl-nl");
				});

		//set default locale
		document.getElementById("lang-default").addEventListener("click",
				function() {
					table.setLocale("");
				});

		document.getElementById("print-table").addEventListener("click", function(){
			table.print(false, true);
		});

	}
	// function filterOnName(name) {
	// 	table.setFilter("filename", "=", name);
	// }
</script>

</html>