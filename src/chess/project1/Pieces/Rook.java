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
public class Rook extends Piece{

    private final static int[] allPossibleMoves = {-8, -1, 1, 8};
    
    public Rook(final Alliance alliance, final int position) {
        super(PieceType.ROOK, position, alliance);
    }
@Override
    public Collection<Move> calculatePossibleMoves(final Board board) {
        
        final ArrayList<Move> possibleMoves = new ArrayList<>();
        
        for(final int currentMove : allPossibleMoves){
            
            int nextTile = this.position;
            
            while(Utilization.isValidTileCoordinate(nextTile)){
                
                if(isInFirstColumn(nextTile, currentMove) || isInEighthColumn(nextTile, currentMove)){
                    break;
                } 
                
                nextTile += currentMove;
                
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
                    break;
                    }
                }  
            }
        }
        return Collections.unmodifiableList(possibleMoves);
    }
    @Override
    public Piece movePiece(final Move move){
       return new Rook(move.getMovingPiece().getPieceAlliance(), move.getDestination()); 
    }

    
    private static boolean isInFirstColumn(final int currentPosition, final int offset){
        return Utilization.firstColumn[currentPosition] && offset == -1;
    }
    
    private static boolean isInEighthColumn(final int currentPosition, final int offset){
        return Utilization.firstColumn[currentPosition] && offset == 1;
    }
    
    @Override
    public String toString(){
        return Piece.PieceType.ROOK.toString();
    }
}
 