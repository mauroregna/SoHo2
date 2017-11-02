$(function () {
    $('section').fadeIn(1000);
});

$(document)
    .on('click', 'form input[type=submit]', function(e) {
        var isValid = validateEmail('#email');
        if(!isValid) {
            e.preventDefault();
        }
    });

function isEmail(email) {
    var regex = /^([a-zA-Z0-9_.+-])+@(([a-zA-Z0-9-])+\.)+([a-zA-Z0-9]{2,4})+$/;
    return regex.test(email);
}

function validateEmail(id) {
    var inputText = $(id).val();
    if (!isEmail(inputText)) {
        $(id).parent().parent().removeClass('has-success');
        $(id).parent().parent().addClass('has-error');
        $('.glyphicon-ok').hide();
        $('.glyphicon-remove').show();
        return false;
    }
    else {
        $(id).parent().parent().removeClass('has-error');
        $(id).parent().parent().addClass('has-success');
        $('.glyphicon-ok').show();
        $('.glyphicon-remove').hide();
        return true;
    }
}