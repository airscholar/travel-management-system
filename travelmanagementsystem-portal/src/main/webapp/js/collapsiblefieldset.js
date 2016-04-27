$('#datetimepicker1').datetimepicker({
	viewMode : 'years',
	format : 'DD/MM/YYYY'
});
$('#datetimepicker2').datetimepicker({
	viewMode : 'years',
	format : 'DD/MM/YYYY'
});
$('#datetimepicker3').datetimepicker({
	viewMode : 'years',
	format : 'DD/MM/YYYY'
});
$('#datetimepicker4').datetimepicker({
	viewMode : 'years',
	format : 'DD/MM/YYYY'
});
$(document).ready(function() {
	$("fieldset.collapsible").collapse();
	$("fieldset.startClosed").collapse( { closed: true } );
});
jQuery.fn.collapse = function(options) {
	var defaults = {
		closed : false
	};
	settings = jQuery.extend({}, defaults, options);

	return this.each(function() {
		var obj = jQuery(this);
		obj.find("legend:first").addClass('collapsible').click(
				function() {
					if (obj.hasClass('collapsed'))
						obj.removeClass('collapsed').addClass('collapsible');

					jQuery(this).removeClass('collapsed');

					obj.children().not('legend').toggle(
							"slow",
							function() {

								if (jQuery(this).is(":visible"))
									obj.find("legend:first").addClass(
											'collapsible');
								else
									obj.addClass('collapsed').find("legend")
											.addClass('collapsed');
							});
				});
		if (settings.closed) {
			obj.addClass('collapsed').find("legend:first")
					.addClass('collapsed');
			obj.children().not("legend:first").css('display', 'none');
		}
	});
};