$(document).ready(function () {
    $("#btnSubmit").click(function (event) {
        event.preventDefault();
        ajaxSubmit();
    });
});

function ajaxSubmit() {

    var form = $('#uploadForm')[0];
    var data = new FormData(form);

    $("#btnSubmit").prop("disabled", true);

    $.ajax({
        type: "POST",
        headers: {'Content-Type': undefined},
        url: "/api/upload",
        data: data,
        processData: false,
        contentType: false,
        cache: false,
        timeout: 600000,
        success: function (data) {
            $("#result").text(data.message);
            console.log("SUCCESS : ", data);
            $("#btnSubmit").prop("disabled", false);
        },
        error: function (e) {
            $("#result").text(e.statusText);
            console.log("ERROR : ", e);
            $("#btnSubmit").prop("disabled", false);
        }
    });
}