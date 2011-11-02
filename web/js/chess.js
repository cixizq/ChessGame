$(document).ready(function() {
    $(".form .button").click(function() {
        $(this).parents().filter("form").trigger("submit");
    });
});

/**
 * Quand l'utilisateur souhaite abandonner
 */
function giveup()
{
    if (board.running && confirm('Voulez-vous vraiment abandonner ?')) {
        $.get('giveup', function() {
            board.giveup();
            alert('Vous avez perdu !');
        });
    }
}