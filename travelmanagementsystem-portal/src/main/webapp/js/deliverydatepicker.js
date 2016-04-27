$(function() {
	$('#datetimepicker1').datetimepicker({
		viewMode : 'years',
		format : 'DD/MM/YYYY'
	});
	$('#datetimepickerstart').datetimepicker({
		viewMode : 'years',
		format : 'DD/MM/YYYY'
	});
	$('#datetimepickerend').datetimepicker({
		viewMode : 'years',
		format : 'DD/MM/YYYY'
	});

});
$(function() {
	$("#formID\\:carcompelete").removeClass("form-control");
	$("#formID\\:carcompelete_input").addClass("form-control");

	$("#formID\\:employeecompelete").removeClass("form-control");
	$("#formID\\:employeecompelete_input").addClass("form-control");
});