// Objet pièce

function Piece(type, color)
{
    this.color = color;
    this.type = type;
}

Piece.prototype.getPathImage = function()
{
    return 'images/pieces/' + this.color + '/' + this.type + '.png';
}

// Objet board

var board = new Object();

board.resize = function()
{
    var width = 0.8 * $(document).width();
    var height = 0.8 * $(document).height();

    var min = Math.min(width, height);

    // La taille doit être un multiple de 8
    if (min % 8 != 0) {
        min = min - (min % 8);
    }

    // Changement de la taille du plateau
    $('#board').width(min);
    $('#board').height(min);

    // Changement de la taille de chaque div dans le plateau
    $('#board > div').width(min / 8);
    $('#board > div').height(min / 8);
}

/**
 * Ajoute une pièce du plateau de jeu
 */
board.addPiece = function(position, piece)
{
    var _case = $('#_' + position);

    if (_case == null) {
        return;
    }

    if (_case.children().size() != 0) {
        //TODO Il y a déjà une pièce sur la case ...
    }

    var img = $(document.createElement('img'));

    img.attr({
        src:   piece.getPathImage(),
        title: piece.type
    });

    img.appendTo(_case);
}

board.initialize = function()
{
    this.pieces = new Array();

    // Black
    this.pieces['8A'] = new Piece('rook', 'black');
    this.pieces['8B'] = new Piece('knight', 'black');
    this.pieces['8C'] = new Piece('bishop', 'black');
    this.pieces['8D'] = new Piece('queen', 'black');
    this.pieces['8E'] = new Piece('king', 'black');
    this.pieces['8F'] = new Piece('bishop', 'black');
    this.pieces['8G'] = new Piece('knight', 'black');
    this.pieces['8H'] = new Piece('rook', 'black');

    this.pieces['7A'] = new Piece('pawn', 'black');
    this.pieces['7B'] = new Piece('pawn', 'black');
    this.pieces['7C'] = new Piece('pawn', 'black');
    this.pieces['7D'] = new Piece('pawn', 'black');
    this.pieces['7E'] = new Piece('pawn', 'black');
    this.pieces['7F'] = new Piece('pawn', 'black');
    this.pieces['7G'] = new Piece('pawn', 'black');
    this.pieces['7H'] = new Piece('pawn', 'black');

    // White
    this.pieces['2A'] = new Piece('pawn', 'white');
    this.pieces['2B'] = new Piece('pawn', 'white');
    this.pieces['2C'] = new Piece('pawn', 'white');
    this.pieces['2D'] = new Piece('pawn', 'white');
    this.pieces['2E'] = new Piece('pawn', 'white');
    this.pieces['2F'] = new Piece('pawn', 'white');
    this.pieces['2G'] = new Piece('pawn', 'white');
    this.pieces['2H'] = new Piece('pawn', 'white');

    this.pieces['1A'] = new Piece('rook', 'white');
    this.pieces['1B'] = new Piece('knight', 'white');
    this.pieces['1C'] = new Piece('bishop', 'white');
    this.pieces['1D'] = new Piece('queen', 'white');
    this.pieces['1E'] = new Piece('king', 'white');
    this.pieces['1F'] = new Piece('bishop', 'white');
    this.pieces['1G'] = new Piece('knight', 'white');
    this.pieces['1H'] = new Piece('rook', 'white');

    for (position in this.pieces) {
        board.addPiece(position, this.pieces[position]);
    }

    $('#board > div > img').draggable({
        opacity: 0.8,
        revert:  'invalid',
        scroll:  false,
        cursor: 'move',
        zIndex: 10
    });

    $("#board > div").droppable({
        drop: function(event, ui) {
            // On doit regarder s'il n'y a pas déjà une pièce
            $(this).append(ui.draggable);

            $(ui.draggable).css({
                position: 'relative',
                left: 0,
                top: 0
            });
        }
    });
}

// Evenements

$(document).ready(function() {
    board.resize();
    board.initialize();
});

$(window).resize(function () {
    board.resize();
});
