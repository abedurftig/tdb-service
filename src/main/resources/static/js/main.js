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
        url: "/api/upload-junit4-xml",
        data: data,
        processData: false,
        contentType: false,
        cache: false,
        timeout: 600000,
        success: function (data) {
            var message = "Testsuite '" + data.name + "' has been uploaded with id " + data.id + " in Testrun " + data.testRunId;
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