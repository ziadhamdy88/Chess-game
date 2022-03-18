/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chess.project1.Player;

import chess.project1.Alliance;
import chess.project1.Board.Board;
import chess.project1.Board.Move;
import chess.project1.Board.Move.KingSideCastleMove;
import chess.project1.Board.Move.QueenSideCastleMove;
import chess.project1.Board.Tile;
import chess.project1.Pieces.Piece;
import chess.project1.Pieces.Rook;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

/**
 *
 * @author ZIAD
 */
public class WhitePlayer extends Player{

    public WhitePlayer(final Board board, final Collection<Move> whiteStandardLegalMoves, final Collection<Move> blackStandardLegalMoves) {
        super(board, whiteStandardLegalMoves, blackStandardLegalMoves);
    }
    
    @Override
    public Collection<Piece> getActivePieces() {
        return this.board.getWhitePieces();
    }

    @Override
    public Alliance getAlliance() {
       return Alliance.WHITE;
    }

    @Override
    public Player getOpponent() {
        return this.board.blackPlayer();
    }

    @Override
    public Collection<Move> calculateKingCastles(final Collection<Move> playerlegals, final Collection<Move> opponentsLegals) {
        final ArrayList<Move> kingCastles = new ArrayList<>();
        
        if(this.playerKing.isFirstMove() && !this.check()){
            //king side castle
            if(!this.board.getTile(61).isTileOccupied() && !this.board.getTile(62).isTileOccupied()){
                final Tile rookTile = this.board.getTile(63);
                if(rookTile.isTileOccupied() && rookTile.getPiece().isFirstMove()){
                    if(Player.calculateAttackOnTile(61, opponentsLegals).isEmpty() && Player.calculateAttackOnTile(62, opponentsLegals).isEmpty() && rookTile.getPiece().getPieceType().isRook()){
                       kingCastles.add(new KingSideCastleMove(this.board, this.playerKing, 62, (Rook)rookTile.getPiece(), rookTile.getTileCoordinate(), 61));
                    }
                }
            }
            if(!this.board.getTile(59).isTileOccupied() && !this.board.getTile(58).isTileOccupied() && !this.board.getTile(57).isTileOccupied()){
                final Tile rookTile = this.board.getTile(56);
                if(rookTile.isTileOccupied() && rookTile.getPiece().isFirstMove() && Player.calculateAttackOnTile(58, opponentsLegals).isEmpty() && Player.calculateAttackOnTile(59, opponentsLegals).isEmpty() && rookTile.getPiece().getPieceType().isRook()){
                    //Queen side castle
                    kingCastles.add(new QueenSideCastleMove(this.board, this.playerKing, 58, (Rook)rookTile.getPiece(), rookTile.getTileCoordinate(), 59));
                }
            } 
        }
        return Collections.unmodifiableList(kingCastles);
    }
    
}
