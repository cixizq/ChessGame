var chess = new Object();

chess.messages = new Array();

chess.messages['your_turn'] = "C'est a vous de jouer";
chess.messages['please_wait'] = "En attente du mouvement de l'autre joueur ...";

/**
 * Change le message présent dans le header
 */
chess.setMessage = function(key)
{
    $('#header > span').html(this.messages[key]);
}

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

/**
 * Change le joueur courant
 */
function switchPlayer()
{
    if (board.canPlay) {
        board.disableDragAndDrop();
        chess.setMessage('please_wait');

        board.canPlay = false;
    } else {
        board.enableDragAndDrop();
        chess.setMessage('your_turn');

        board.canPlay = true;
    }
}