<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<div id="example-table"></div>
	<link href="https://unpkg.com/tabulator-tables/dist/css/tabulator.min.css" rel="stylesheet">
	<script type="text/javascript" src="https://unpkg.com/tabulator-tables/dist/js/tabulator.min.js"></script>
	<script>
	var table = new Tabulator("#example-table", {
	    data:tabledata,           //load row data from array
	    layout:"fitColumns",      //fit columns to width of table
	    responsiveLayout:"hide",  //hide columns that dont fit on the table
	    tooltips:true,            //show tool tips on cells
	    addRowPos:"top",          //when adding a new row, add it to the top of the table
	    history:true,             //allow undo and redo actions on the table
	    pagination:"local",       //paginate the data
	    paginationSize:7,         //allow 7 rows per page of data
	    movableColumns:true,      //allow column order to be changed
	    resizableRows:true,       //allow row order to be changed
	    initialSort:[             //set the initial sort order of the data
	        {column:"name", dir:"asc"},
	    ],
	    columns:[                 //define the table columns
	        {title:"Name", field:"name", editor:"input"},
	        {title:"Task Progress", field:"progress", hozAlign:"left", formatter:"progress", editor:true},
	        {title:"Gender", field:"gender", width:95, editor:"select", editorParams:{values:["male", "female"]}},
	        {title:"Rating", field:"rating", formatter:"star", hozAlign:"center", width:100, editor:true},
	        {title:"Color", field:"col", width:130, editor:"input"},
	        {title:"Date Of Birth", field:"dob", width:130, sorter:"date", hozAlign:"center"},
	        {title:"Driver", field:"car", width:90,  hozAlign:"center", formatter:"tickCross", sorter:"boolean", editor:true},
	    ],
	});
	</script>
</body>
</html>