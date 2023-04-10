/**
 * 
 */
 function historyofPatient(){
	var dataView;
	var grid;
	var columnFilters = {};

	var data = document.getElementById("appList").value

	/*<![CDATA[*/
	//var data = /*[[${managerEmplist}]]*/'Emp';//

	console.log(data);

	var data1 = JSON.parse(data);
	var history=[];
	    for(var i=0; i<data1.length; i++){
	history.push(data1[i]['sno']);
     console.log(data1[i]['sno']);
	
}
     console.log(history);
	/* set unique it to array */
	var nextId = 1;
	data1.forEach(function(item) {
		// Generate a unique id using the nextId value
		var itemId = "item_" + nextId;

		// Set the id property of the item object
		item.id = itemId;

		// Increment the nextId value
		nextId++;
	});
	// Use the updated items array list with unique ids
	/* unique id end */
	var options = {
		editable: true,
		enableAddRow:false,
		enableCellNavigation: true,
		asyncEditorLoading: true,
		forceFitColumns: false,
		showHeaderRow: true,
		headerRowHeight: 30,
		explicitInitialization: true,
		topPanelHeight: 25,
		suppressCssChangesOnHiddenInit: true
	};
	var columnFilters = {};
	function checkboxFormatter(row, cell, value, columnDef, dataContext) {
		let a=dataContext.patientid;
		let b=dataContext.regid;
		let c=dataContext.appdate;
		let d=dataContext.doctorid;
		return '<input type="checkbox" value="'+b+','+d+','+a+'" name="checkName" id="checkBox"' + (value ? 'checked="checked"' : '') + '/>';
	}
function dateFormatter(row, cell, value, columnDef, dataContext) {
  const date = new Date(value);
  var getDay = date.toLocaleString("default", { day: "2-digit" });
  var getMonth = date.toLocaleString("default", { month: "2-digit" });
  var getYear = date.toLocaleString("default", { year: "numeric" }); // Set the desired date format
  const formattedDate =  getDay + "-" + getMonth + "-" + getYear;// Format the date as a string
  return formattedDate; // Return the formatted date string
}
//var check= document.getElementById("checkBox");
   var check = document.querySelectorAll('input[type="checkbox"]:checked');
console.log(check);
	function buttonFormatter(row, cell, value, columnDef, dataContext) {
			let a=dataContext.sno;
		
			//return '<input type="checkbox" value="'+a+','+b+'" name="checkName" id="checkBox"' + (value ? 'checked="checked"' : '') + '/>';
			return '<form action="/view" method="post" th:object="${sid}"><button type="submit" name="doctorId" value="'+a+'">view</button></form>';
			
		}
var sortcol = "title";
	var sortdir = 1;
	var percentCompleteThreshold = 0;
	var searchString = "";

	function requiredFieldValidator(value) {
		if (value == null || value == undefined || !value.length) {
			return { valid: false, msg: "This is a required field" };
		}
		else {
			return { valid: true, msg: null };
		}
	}

	function myFilter(item, args) {
		if (item["percentComplete"] < args.percentCompleteThreshold) {
			return false;
		}

		if (args.searchString != "" && item["title"].indexOf(args.searchString) == -1) {
			return false;
		}

		return true;
	}

	function percentCompleteSort(a, b) {
		return a["percentComplete"] - b["percentComplete"];
	}

	function comparer(a, b) {
		var x = a[sortcol], y = b[sortcol];
		return (x == y ? 0 : (x > y ? 1 : -1));
	}

	function toggleFilterRow() {
		grid.setTopPanelVisibility(!grid.getOptions().showTopPanel);
	}
	var columns = [ {
			id : "appno",
			name : "visitdate",
			field : "appno",
			width : 130
			
		}, {
			id : "did",
			name : "Doctorcode",
			field : "did",
			width : 120
		}, {
			id : "docname",
			name : "doctorname",
			field : "docname",
			width : 120
		}, {
			id : "stat",
			name : "appointmentstatus",
			field : "stat",
			width : 180
		}, {
			id : "sno",
			name : "medicine",
			field : "sno",
			width : 130,
			formatter:buttonFormatter
		}];

		$(".grid-header .ui-icon")
		.addClass("ui-state-default ui-corner-all")
		.mouseover(function(e) {
			$(e.target).addClass("ui-state-hover")
		})
		.mouseout(function(e) {
			$(e.target).removeClass("ui-state-hover")
		});

		/* filter start */
		
	$(function() {
		/* filter start */
		function filter(item) {
			for (var columnId in columnFilters) {
				if (columnId !== undefined && columnFilters[columnId] !== "") {
					var column = grid.getColumns()[grid.getColumnIndex(columnId)];

					if (item[column.field] !== undefined) {
						var filterResult = typeof item[column.field].indexOf === 'function'
							? (item[column.field].indexOf(columnFilters[columnId]) === -1)
							: (item[column.field] != columnFilters[columnId]);

						if (filterResult) {
							return false;
						}
					}
				}
			}
			return true;
		}
			/* filter end */

		dataView = new Slick.Data.DataView();
		grid = new Slick.Grid("#myGrid", dataView, columns, options);
		 grid.setSelectionModel(new Slick.RowSelectionModel());

		var pager = new Slick.Controls.Pager(dataView, grid, $("#pager"));
		var columnpicker = new Slick.Controls.ColumnPicker(columns, grid, options);

		/* filter start */
		dataView.onRowCountChanged.subscribe(function(e, args) {
			grid.updateRowCount();
			grid.render();
		});

		dataView.onRowsChanged.subscribe(function(e, args) {
			grid.invalidateRows(args.rows);
			grid.render();
		});

		$(grid.getHeaderRow()).delegate(":input", "change keyup",
			function(e) {
				var columnId = $(this).data("columnId");
				if (columnId != null) {
					columnFilters[columnId] = $.trim($(this).val());
					dataView.refresh();
				}
			});

		grid.onHeaderRowCellRendered.subscribe(function(e, args) {
			$(args.node).empty();
			$("<input type='text'>").data("columnId", args.column.id).val(
				columnFilters[args.column.id]).appendTo(args.node);
		});
		 grid.setSelectionModel(new Slick.RowSelectionModel());

		var pager = new Slick.Controls.Pager(dataView, grid, $("#pager"));
		var columnpicker = new Slick.Controls.ColumnPicker(columns, grid, options);

		grid.init();

		dataView.beginUpdate();
		dataView.setItems(data1);
		dataView.setFilter(filter);
		dataView.endUpdate();
	    dataView.syncGridSelection(grid, true);

	})

}