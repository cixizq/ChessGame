var chess = new Object();

chess.messages = new Array();

chess.messages['your_turn'] = "C'est a vous de jouer";
chess.messages['receive_giveup'] = "Le joueur vient d'abandonner ! Vous avez gagné !";
chess.messages['please_wait'] = "En attente du mouvement de l'autre joueur ...";

chess.running = false;

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
chess.giveup = function()
{
    if (board.running && confirm('Voulez-vous vraiment abandonner ?')) {
        $.get('giveup', function() {
            chess.stopGame();
            alert('Vous avez perdu !');
        });
    }
}

/**
 * Change le joueur courant
 */
chess.switchPlayer = function()
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

/**
 * Met à jour le jeu d'échec
 */
chess.update = function()
{
    $.get('update', function(xml) {
        // Comme le .get est asynchrome, il est possible que la fonction soit
        // executée quand le jeu est déjà terminé
        if (!chess.running) {
            return;
        }

        var xmlDoc = $(xml);
        var state = parseInt(xmlDoc.find('state').text());

        switch (state) {
            case 3:
                chess.receiveGiveup();
                break;
        }
    });

    if (chess.running) {
        setTimeout(function() {chess.update();}, 1000);
    }
}

/**
 * Termine le jeu. C'est-à-dire désactive le plateau de jeu
 * et annule la récupération des updates en ajax vers le serveur
 */
chess.stopGame = function()
{
    // On enlève la couleur du bouton abandonner
    $('#informations > a.red').removeClass('red');

    board.disable();
    chess.running = false;
}

/**
 * On vient de recevoir un abandon de l'autre joueur
 */
chess.receiveGiveup = function()
{
    chess.stopGame();
    chess.setMessage('receive_giveup');
}

/**
 * Initilisation du jeu d'échec
 */
chess.initialize = function()
{
    chess.running = true;

    board.initialize();
    chess.update();
}

/**
 * Evenements
 */
$(document).ready(function() {
    chess.initialize();
});