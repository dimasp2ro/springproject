$(document).ready(function(){
	if ($.cookie('uuid') != null) {
		alert("Please log out for use registration")
		window.location = "index";
	}
})

$("#registration-button").click(function() {
	var myJson = {
		"login" : $('#login').val(),
		"fio" : $('#fio').val(),
		"password" : $('#password').val(),
		"passwordConfirm" : $('#passwordConfirm').val()
	}
	$.ajax({
		url : "/registr/newuser",
		type : "POST",
		dataType : "text",
		contentType : "application/json",
		data : JSON.stringify(myJson),
		success : function(data, textStatus, xhr) {
			new Success(data).isSuccess();
		},
		error : function(xhr, ajaxOptions, thrownError) {
			console.log(xhr.status);
			console.log(thrownError);
		}
	});
});

;
(function($) {
	$.fn.extend({
		donetyping : function(callback, timeout) {
			timeout = timeout || 1e3; // 1 second default timeout
			var timeoutReference, doneTyping = function(el) {
				if (!timeoutReference)
					return;
				timeoutReference = null;
				callback.call(el);
			};
			return this.each(function(i, el) {
				var $el = $(el);
				$el.is(':input') && $el.on('keyup keypress paste', function(e) {
					if (e.type == 'keyup' && e.keyCode != 8)
						return;
					if (timeoutReference)
						clearTimeout(timeoutReference);
					timeoutReference = setTimeout(function() {
						doneTyping(el);
					}, timeout);
				}).on('blur', function() {
					doneTyping(el);
				});
			});
		}
	});
})(jQuery);

$('#login').donetyping(function() {
	check("login");
});
$('#fio').donetyping(function() {
	check("fio");
});
$('#password').donetyping(function() {
	check("password");
	check("passwordConfirm");
});
$('#passwordConfirm').donetyping(function() {
	check("passwordConfirm");
	check("password");
});

function check(el) {
	var value = $("#" + el).val();
	var url = "/registr/" + el;
	$.ajax({
		url : url,
		type : "POST",
		dataType : "text",
		contentType : "text/plain",
		data : value,
		success : function(data, textStatus, xhr) {
			new FieldSuccess(data, el).isSuccess()
		},
		error : function(xhr, ajaxOptions, thrownError) {
			console.log(xhr.status);
			console.log(thrownError);
		}
	});
}
function FieldSuccess(data, key) {

	this.isSuccess = function() {
		if (data == 'success') {
			success(data);
		} else {
			insertError(data)
		}
	}

	function success(data) {
		$("#" + key + "-unsaccess").removeClass("has-error")
		$("#" + key + "-unsaccess").addClass("has-success")
		$("#" + key + "-msg").text("");
	}

	function insertError(data) {
		var obj = $.parseJSON(data);
		$("#" + key + "-unsaccess").addClass("has-error")
		$("#" + key + "-msg").text(obj[key]);

	}
}
function Success(data) {

	this.isSuccess = function() {
		var json = $.parseJSON(data);
		if (json['success'] != null) {
			success(json['success']);
		} else {
			insertError(data)
		}
	}

	function success(v) {
		$.cookie('uuid', v);
		document.location.href = "index"
	}

	function insertError(data) {
		var obj = $.parseJSON(data);
		for (key in obj) {
			$("#" + key + "-unsaccess").addClass("has-error")
			$("#" + key + "-msg").text(obj[key]);
		}
	}
}
