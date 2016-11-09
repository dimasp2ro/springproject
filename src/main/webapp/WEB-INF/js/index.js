$(document).ready(function() {
	if ($.cookie('uuid') == null) {
		window.location = "login";
	} else {
		$.ajax({
			url : "/notelist/getbyname",
			type : "POST",
			dataType : "text",
			contentType : "text/plain",
			data : $.cookie('uuid'),
			success : function(data, textStatus, xhr) {
				new Success(data).isSuccess();
			},
			error : function(xhr, ajaxOptions, thrownError) {
				console.log(xhr.status);
				console.log(thrownError);
			}
		});
	}
});

$("#add-note").click(function() {
	var myJson = {
		"id" : "0",
		"name" : $('#add-name').val(),
		"surname" : $('#add-surname').val(),
		"lastname" : $('#add-lastname').val(),
		"phone" : $('#add-phone').val(),
		"homephone" : $('#add-homephone').val(),
		"address" : $('#add-address').val(),
		"email" : $('#add-email').val(),
		"uuid" : $.cookie('uuid')
	};
	send(myJson)

});
function send(myJson) {
	$.ajax({
		url : "/notes/add",
		type : "POST",
		dataType : "text",
		contentType : "application/json",
		data : JSON.stringify(myJson),
		success : function(data, textStatus, xhr) {
			parseInTable($.parseJSON(data));
			clearAdd();
		},
		error : function(xhr, ajaxOptions, thrownError) {
			console.log(xhr.status);
			console.log(thrownError);
		}
	});
}

function clearAdd() {
	$('#add-name').val('');
	$('#add-surname').val('');
	$('#add-lastname').val('');
	$('#add-phone').val('');
	$('#add-homephone').val('');
	$('#add-address').val('');
	$('#add-email').val('');
}

function Success(data) {
	this.isSuccess = function() {
		var json = $.parseJSON(data);
		if (json.success) {
			success(json);
		} else {

		}
	}
	function success(json, war) {
		for (var i = 0; i < json.notes.length; i++) {
			parseInTable(json.notes[i]);
		}
	}

}

function parseInTable(json) {

	$('#notes-table tr:last')
			.after(
					'<tr id=' + "row"
							+ json.id
							+ '>'
							+ '<th>'
							+ '<div class="form-group" >'
							+ '<div class="input-group">'
							+ '<input type="text" class="form-control" name="name" value="'
							+ json.name
							+ '" onkeypress=""/>'
							+ '<span class="glyphicon glyphicon-ok form-control-feedback" aria-hidden="true"></span>'
							+ '</div>'
							+ '<span class="help-block"></span>'
							+ '</div>'
							+ '</th>'
							+ '<th>'
							+ '<div class="form-group">'
							+ '<div class="input-group">'
							+ '<input type="text" class="form-control" name="surname" value="'
							+ json.surname
							+ '" />'
							+ '<span class="glyphicon glyphicon-ok form-control-feedback" aria-hidden="true"></span>'
							+ '</div>'
							+ '<span class="help-block"></span>'
							+ '</div>'
							+ '</th>'
							+ '<th>'
							+ '<div class="form-group">'
							+ '<div class="input-group">'
							+ '<input type="text" class="form-control" name="lastname" value="'
							+ json.lastname
							+ '" />'
							+ '<span class="glyphicon glyphicon-ok form-control-feedback" aria-hidden="true"></span>'
							+ '</div>'
							+ '<span class="help-block"></span>'
							+ '</div>'
							+ '</th>'
							+ '<th>'
							+ '<div class="form-group">'
							+ '<div class="input-group">'
							+ '<input type="text" class="form-control" name="phone" value="'
							+ json.phone
							+ '" />'
							+ '<span class="glyphicon glyphicon-ok form-control-feedback" aria-hidden="true"></span>'
							+ '</div>'
							+ '<span class="help-block"></span>'
							+ '</div>'
							+ '</th>'
							+ '<th>'
							+ '<div class="form-group">'
							+ '<div class="input-group">'
							+ '<input type="text" class="form-control" name="homephone" value="'
							+ json.homephone
							+ '" />'
							+ '<span class="glyphicon glyphicon-ok form-control-feedback" aria-hidden="true"></span>'
							+ '</div>'
							+ '<span class="help-block"></span>'
							+ '</div>'
							+ '</th>'
							+ '<th>'
							+ '<div class="form-group">'
							+ '<div class="input-group">'
							+ '<input type="text" class="form-control" name="address" id="name" value="'
							+ json.address
							+ '" />'
							+ '<span class="glyphicon glyphicon-ok form-control-feedback" aria-hidden="true"></span>'
							+ '</div>'
							+ '<span class="help-block"></span>'
							+ '</div>'
							+ '</th>'
							+ '<th>'
							+ '<div class="form-group">'
							+ '<div class="input-group">'
							+ '<input type="text" class="form-control" name="email" value="'
							+ json.email
							+ '" />'
							+ '<span class="glyphicon glyphicon-ok form-control-feedback" aria-hidden="true"></span>'
							+ '</div>'
							+ '<span class="help-block"></span>'
							+ '</div>'
							+ '</th>'
							+ '<th><button class="btn btn-default" id="setting-note" onclick="onEdit('
							+ json.id
							+ ')"><i class="glyphicon glyphicon-cog"></i></button></th>'
							+ '<th><button class="btn btn-default" id="delete-note-row" onclick="onDelete('
							+ json.id
							+ ')"><i class="glyphicon glyphicon-remove"></i></button></th>'
							+ '</tr>');
}

function onDelete(d) {
	var phone = $('#row' + d + ' input[name="phone"]').val();
	$.ajax({
		url : "/notes/delete",
		type : "POST",
		dataType : "text",
		contentType : "text/plain",
		data : "+" + phone,
		success : function(data, textStatus, xhr) {
			$('#row' + d).remove();
		},
		error : function(xhr, ajaxOptions, thrownError) {
			console.log(xhr.status);
			console.log(thrownError);
		}
	});
}

function onEdit(d) {
	
	var myJson = {
		"id" : d,
		"name" : $('#row' + d + ' input[name="name"]').val(),
		"surname" : $('#row' + d + ' input[name="surname"]').val(),
		"lastname" : $('#row' + d + ' input[name="lastname"]').val(),
		"phone" : $('#row' + d + ' input[name="phone"]').val(),
		"homephone" : $('#row' + d + ' input[name="homephone"]').val(),
		"address" : $('#row' + d + ' input[name="address"]').val(),
		"email" : $('#row' + d + ' input[name="email"]').val(),
		"uuid" : $.cookie('uuid')
	};
	$.ajax({
		url : "/notes/update",
		type : "POST",
		dataType : "text",
		contentType : "application/json",
		data : JSON.stringify(myJson),
		success : function(data, textStatus, xhr) {
			alert(data)
//			$('#row' + d).remove();
		},
		error : function(xhr, ajaxOptions, thrownError) {
			console.log(xhr.status);
			console.log(thrownError);
		}
	});
}

$("#log-out").click(function() {
	$.removeCookie("uuid");
	if ($.cookie('uuid') == null) {
		window.location = "login";
	}
});

$('#phone-filter').donetyping(function() {
	var myJson = {
		"name" : "",
		"surname" : "",
		"lastname" : "",
		"phone" : $('#phone-filter').val(),
		"homephone" : "",
		"address" : "",
		"email" : "",
		"uuid" : $.cookie('uuid')
	};
	
	$.ajax({
		url : "/notesfiltring/filterbyphone",
		type : "POST",
		dataType : "text",
		contentType : "application/json",
		data : JSON.stringify(myJson),
		success : function(data, textStatus, xhr) {
			alert(data)
		},
		error : function(xhr, ajaxOptions, thrownError) {
			console.log(xhr.status);
			console.log(thrownError);
		}
	});
});

$('#name-filter').donetyping(function() {
	$('#notes-table').children( 'tr:not(:first)' ).remove();
	var myJson = {
		"id" : "0",
		"name" : $('#name-filter').val(),
		"surname" : "",
		"lastname" : "",
		"phone" : "",
		"homephone" : "",
		"address" : "",
		"email" : "",
		"uuid" : $.cookie('uuid')
	};
	
	$.ajax({
		url : "/notesfiltring/filterbyname",
		type : "POST",
		dataType : "text",
		contentType : "application/json",
		data : JSON.stringify(myJson),
		success : function(data, textStatus, xhr) {
			alter(data)
		},
		error : function(xhr, ajaxOptions, thrownError) {
			console.log(xhr.status);
			console.log(thrownError);
		}
	});
});

$('#surname-filter').donetyping(function() {
	$('#notes-table').children( 'tr:not(:first)' ).remove();
	var myJson = {
		"id" : "0",
		"name" : "",
		"surname" : $('#surname-filter').val(),
		"lastname" : "",
		"phone" : "",
		"homephone" : "",
		"address" : "",
		"email" : "",
		"uuid" : $.cookie('uuid')
	};
	
	$.ajax({
		url : "/notesfiltring/filterbysurname",
		type : "POST",
		dataType : "text",
		contentType : "application/json",
		data : JSON.stringify(myJson),
		success : function(data, textStatus, xhr) {
			alter(data)
		},
		error : function(xhr, ajaxOptions, thrownError) {
			console.log(xhr.status);
			console.log(thrownError);
		}
	});
});
