$(document).ready(function() {
	if ($.cookie('uuid') != null) {
		alert("Please log out for use login")
		window.location = "index";
	}
})
$("#login-button").click(function() {
	var myJson = {
		"login" : $('#login').val(),
		"password" : $('#password').val()
	}
	$.ajax({
		url : "/logining/logining",
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

$('#login').donetyping(function() {
	check("login");
});
$('#password').donetyping(function() {
	check("password");
});

function check(el) {
	var value = $("#" + el).val();
	var url = "/logining/" + el;
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
