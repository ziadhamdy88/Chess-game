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
public class Knight extends Piece {
    
    private final static int[] allPossibleMoves = {-17, -15, -10, -6, 6, 10, 15, 17};

    public Knight(final Alliance alliance, final int position) {
        super(PieceType.KNIGHT, position, alliance);
    }
    
    @Override
    public Collection<Move> calculatePossibleMoves(final Board board){
        final ArrayList<Move> possibleMoves = new ArrayList<>();
        
        for(int currentMove : allPossibleMoves){
            final int nextTile = this.position + currentMove;
            if(Utilization.isValidTileCoordinate(nextTile)){
                if(isInFirstColumn(this.position, currentMove) || isInSecondColumn(this.position, currentMove) || isInSeventhColumn(this.position, currentMove) || isInEighthColumn(this.position, currentMove)){
                    continue;
                }
                
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
       return new Knight(move.getMovingPiece().getPieceAlliance(), move.getDestination()); 
    }

    
    private static boolean isInFirstColumn(final int currentPosition, final int offset){
       return Utilization.firstColumn[currentPosition] && (offset == -17 || offset == -10 || offset == 6 || offset == 15);
    }
    
    private static boolean isInSecondColumn(final int currentPosition, final int offset){
        return Utilization.secondColumn[currentPosition] && (offset == -10 || offset == 6);
    }
    
    private static boolean isInSeventhColumn(final int currentPosition, final int offset){
        return Utilization.seventhColumn[currentPosition] && (offset == -6 || offset == 10);
    }
    
    private static boolean isInEighthColumn(final int currentPosition, final int offset){
        return Utilization.eighthColumn[currentPosition] && (offset == -15 || offset == -6 || offset == 10 || offset == 17);
    }
    
    @Override
    public String toString(){
        return Piece.PieceType.KNIGHT.toString();
    }
}
