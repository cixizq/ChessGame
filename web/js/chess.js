$(document).ready(function() {
    $(".form .button").click(function() {
        $(this).parents().filter("form").trigger("submit");
    });
});