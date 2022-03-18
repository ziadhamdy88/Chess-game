/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chess.project1.Pieces;

import chess.project1.Alliance;
import chess.project1.Board.Board;
import chess.project1.Board.Move;
import chess.project1.Board.Tile;
import chess.project1.Utilization;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

/**
 *
 * @author ZIAD
 */
public class King extends Piece{

    private final static int[] allPossibleMoves = {-9, -8, -7, -1, 1, 7, 8, 9};
    
    public King(final Alliance alliance, final int position) {
        super(PieceType.KING, position, alliance);
    }

    @Override
    public Collection<Move> calculatePossibleMoves(Board board) {
        final ArrayList<Move> possibleMoves = new ArrayList<>();
        for(final int nextMove : allPossibleMoves){
            final int nextTile = this.position + nextMove;
            if(isInFirstColumn(this.position, nextMove) || isInEighthColumn(this.position, nextMove)){
                continue;
            }
            
            if(Utilization.isValidTileCoordinate(nextTile)){
                final Tile destinationTile = board.getTile(nextTile);
                if(!(destinationTile.isTileOccupied())){
                    possibleMoves.add(new Move.NonAttackingMove(board, this, nextTile));
                }
                else{
                    final Piece pieceAtDestination = destinationTile.getPiece();
                    final Alliance pieceAlliance = pieceAtDestination.getPieceAlliance();
                    if(this.alliance != pieceAlliance){
                        possibleMoves.add(new Move.AttackingMove(board, this, nextTile, pieceAtDestination));
                    }
                }
            }
        }
        return Collections.unmodifiableList(possibleMoves);
    }
    
    @Override
    public Piece movePiece(final Move move){
       return new King(move.getMovingPiece().getPieceAlliance(), move.getDestination()); 
    }

    
    private static boolean isInFirstColumn(final int currentPosition, final int offset){
       return Utilization.firstColumn[currentPosition] && (offset == -9 || offset == -1 || offset == 7);
    }
    
    private static boolean isInEighthColumn(final int currentPosition, final int offset){
        return Utilization.eighthColumn[currentPosition] && (offset == -7 || offset == 1 || offset == 9);
    }
    
      @Override
    public String toString(){
        return Piece.PieceType.KING.toString();
    }  
}
