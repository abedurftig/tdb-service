$(document).ready(function () {
    $("#btnSubmit").click(function (event) {
        event.preventDefault();
        ajaxSubmit();
    });
});

function ajaxSubmit() {

    var form = $('#uploadForm')[0];
    var data = new FormData(form);

    var type = $('input[name=type]:checked').val();
    var url = type === 'normal' ?
        "/api/upload/junit4-xml" :
        "/api/upload/junit4-xml-wrapped";

    $("#btnSubmit").prop("disabled", true);

    $.ajax({
        type: "POST",
        headers: {'Content-Type': undefined},
        url: url,
        data: data,
        processData: false,
        contentType: false,
        cache: false,
        timeout: 600000,
        success: function (data) {
            var message = data.message;
            $("#result").text(message);
            $("#btnSubmit").prop("disabled", false);
        },
        error: function (e) {
            $("#result").text(e.statusText);
            console.log("ERROR : ", e);
            $("#btnSubmit").prop("disabled", false);
        }
    });
}