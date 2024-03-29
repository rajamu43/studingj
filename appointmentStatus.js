/**
 * 
 */
 function approvalList(){
	
	var dataView;
	var grid;
	var columnFilters = {};

	var data = document.getElementById("doctorapproval").value
    console.log(data);
    var data1=JSON.parse(data);
    
         var approvalList=[];
     for(var i=0; i<data1.length; i++){
	approvalList.push(data1[i]['appno']);
     console.log(data1[i]['appno']);
	}
    console.log(approvalList);
    
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
		console.log(data);
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
		let a=dataContext.appno;
		let b=dataContext.docid;
		let c=dataContext.patientid;
		return '<input type="checkbox" value="'+a+','+b+','+c+'" name="checkName" id="checkBox"' + (value ? 'checked="checked"' : '') + '/>';
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
			let a=dataContext.appno;
		let b=dataContext.docid;
		let c=dataContext.pid;
			//return '<input type="checkbox" value="'+a+','+b+'" name="checkName" id="checkBox"' + (value ? 'checked="checked"' : '') + '/>';
			return '<form action="/givemedicine" method="post" th:object="${checkbox}"><button type="submit" name="doctorGender" value="'+a+','+b+','+c+'">Add Medicine</button></form>';
			
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
	var columns = [{
			
			id : "appno",
			name : "AppointmentNo",
			field : "appno",
			width : 140
	        
		}, {
			id : "docid",
			name : "DoctorCode",
			field : "docid",
			width : 140
		}, {
			id : "pid",
			name : "PatientCode",
			field : "pid",
			width : 120
		}, {
			id : "appdate",
			name : "visitdate",
			field : "appdate",
			width : 120,
			formatter :dateFormatter
		}, {
			id : "status",
			name : "status",
			field : "status",
			width :120
		},{
			id : "action",
			name :"Action",
			field:"action",
			formatter :buttonFormatter,
			width :140
		} ]; 
		
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
		var columnpicker = new Slick.Controls.ColumnPicker(columns, grid, options);			/* filter start */
			
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
			// move the filter panel defined in a hidden div into grid top panel
		$("#inlineFilterPanel")
			.appendTo(grid.getTopPanel())
			.show();

		grid.onCellChange.subscribe(function(e, args) {
			dataView.updateItem(args.item.id, args.item);
		});
			grid.onAddNewRow.subscribe(function(e, args) {
			var item = { "num": data.length, "id": "new_" + (Math.round(Math.random() * 10000)), "title": "New task", "duration": "1 day", "percentComplete": 0, "start": "01/01/2009", "finish": "01/01/2009", "effortDriven": false };
			$.extend(item, args.item);
			dataView.addItem(item);
		});

		grid.onKeyDown.subscribe(function(e) {
			// select all rows on ctrl-a
			if (e.which != 65 || !e.ctrlKey) {
				return false;
			}

			var rows = [];
			for (var i = 0; i < dataView.getLength(); i++) {
				rows.push(i);
			}

			grid.setSelectedRows(rows);
			e.preventDefault();
		});

		grid.onSort.subscribe(function(e, args) {
			sortdir = args.sortAsc ? 1 : -1;
			sortcol = args.sortCol.field;

			if ($.browser.msie && $.browser.version <= 8) {
				// using temporary Object.prototype.toString override
				// more limited and does lexicographic sort only by default, but can be much faster

				var percentCompleteValueFn = function() {
					var val = this["percentComplete"];
					if (val < 10) {
						return "00" + val;
					} else if (val < 100) {
						return "0" + val;
					} else {
						return val;
					}
				};

				// use numeric sort of % and lexicographic for everything else
				dataView.fastSort((sortcol == "percentComplete") ? percentCompleteValueFn : sortcol, args.sortAsc);
			} else {
				// using native sort with comparer
				// preferred method but can be very slow in IE with huge datasets
				dataView.sort(comparer, args.sortAsc);
			}
		});

		// wire up model events to drive the grid
		dataView.onRowCountChanged.subscribe(function(e, args) {
			grid.updateRowCount();
			grid.render();
		});

		dataView.onRowsChanged.subscribe(function(e, args) {
			grid.invalidateRows(args.rows);
			grid.render();
		});

		dataView.onPagingInfoChanged.subscribe(function(e, pagingInfo) {
			var isLastPage = pagingInfo.pageNum == pagingInfo.totalPages - 1;
			var enableAddRow = isLastPage || pagingInfo.pageSize == 0;
			var options = grid.getOptions();

			if (options.enableAddRow != enableAddRow) {
				grid.setOptions({ enableAddRow: enableAddRow });
			}
		});


		var h_runfilters = null;
		/*
				// wire up the slider to apply the filter to the model
				$("#pcSlider,#pcSlider2").slider({
					"range": "min",
					"slide": function(event, ui) {
						Slick.GlobalEditorLock.cancelCurrentEdit();
		
						if (percentCompleteThreshold != ui.value) {
							window.clearTimeout(h_runfilters);
							h_runfilters = window.setTimeout(updateFilter, 10);
							percentCompleteThreshold = ui.value;
						}
					}
				});
		
		
				// wire up the search textbox to apply the filter to the model
				$("#txtSearch,#txtSearch2").keyup(function(e) {
					Slick.GlobalEditorLock.cancelCurrentEdit();
		
					// clear on Esc
					if (e.which == 27) {
						this.value = "";
					}
		
					searchString = this.value;
					updateFilter();
				});
		*/
		function updateFilter() {
			dataView.setFilterArgs({
				percentCompleteThreshold: percentCompleteThreshold,
				searchString: searchString
			});
			dataView.refresh();
		}

		$("#btnSelectRows").click(function() {
			if (!Slick.GlobalEditorLock.commitCurrentEdit()) {
				return;
			}

			var rows = [];
			for (var i = 0; i < 10 && i < dataView.getLength(); i++) {
				rows.push(i);
			}

			grid.setSelectedRows(rows);
		});

			grid.init();

			dataView.beginUpdate();
			dataView.setItems(data1);
			dataView.setFilter(filter);
			dataView.endUpdate();
			dataView.syncGridSelection(grid, true);

		})
		/* filter end */
	
	
}