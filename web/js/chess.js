var chess = new Object();

chess.messages = new Array();

chess.messages['your_turn'] = "C'est a vous de jouer !";
chess.messages['please_wait'] = "C'est à l'autre joueur de jouer";

$(document).ready(function() {
    $(".form .button").click(function() {
        $(this).parents().filter("form").trigger("submit");
    });

    $("#game_link > input").focus(function(){
        this.select();
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

function switchPlayer()
{
    board.disableDragAndDrop();

    // Affiche un message au joueur pour l'avertir du changement de joueur
}