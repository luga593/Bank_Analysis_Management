<!DOCTYPE html>
<html>
<head>
<title>Loan Decision</title>
<link rel="stylesheet" type="text/css" href="WEB-INF/MyStyle.css">
<link rel="stylesheet"
	href="https://fonts.googleapis.com/css?family=Poppins">
	<script src="html2pdf.bundle.min.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/jspdf/1.3.2/jspdf.min.js"></script>
</head>
<body>
	<jsp:include page="base.jsp" />
	<h2>Loan Decision Result</h2>
	<div id="GetLoanDecision">
		<button type="submit" onclick="myFunction()">Get loan decision</button>
	</div>
	<div id="Decision">
	<div id="PositiveDecision"></div>
	<div id="NegativeDecision"></div>
	</div>
	<div id="DownloadAsPDF">
		<button type="submit" onclick="downloadPDF()">Download as PDF</button>
	</div>
</body>
<style>
<jsp:include page ="WEB-INF/CSS/baseStyle.css"/>

h2 {
	color: white;
	text-align: center;
	letter-spacing: 3px;
	margin-bottom: 20px;
}

#NegativeDecision {
	margin-top: 100px;
	color: red;
	text-align: center;
	font-family: Arial, Helvetica, sans-serif;
	font-size: 40px;
}
#PositiveDecision {
	margin-top: 100px;
	color: green;
	text-align: center;
	font-family: Arial, Helvetica, sans-serif;
	font-size: 40px;
}

#GetLoanDecision {
	text-align: center;
}
</style>
<script>
	<jsp:include page="WEB-INF/JS/darkTheme.js"/>
	<jsp:include page="WEB-INF/JS/sideNav.js"/>
	function myFunction() {
		var request = new XMLHttpRequest();
		request.onreadystatechange = function() {
			if (request.readyState == 4 && request.status == 200) {
				if (request.responseText.includes("high")) {
					document.getElementById("NegativeDecision").innerHTML = request.responseText;
				} else {
				document.getElementById("PositiveDecision").innerHTML = request.responseText;
				}	
			}	
		}
		request.open("GET",
				"http://localhost:8080/Topicus/LoanDecisionServlet", true); //CHANGE PARAM TO IBAN
		request.send();
	}
	function downloadPDF() {
		var doc = new jsPDF();
		doc.fromHTML('<html><head><title>${title}</title></head><body>' + document.getElementById("Decision").innerHTML + '</body></html>');
		doc.save('Decision.pdf');
		html2pdf(element);
	}
</script>
</html>
