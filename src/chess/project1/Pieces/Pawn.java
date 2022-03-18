/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chess.project1.Pieces;

import chess.project1.Alliance;
import chess.project1.Board.Board;
import chess.project1.Board.Move;
import chess.project1.Utilization;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

/**
 *
 * @author ZIAD
 */
public class Pawn extends Piece{

    private final static int[] allPossibleMoves = {8, 16, 7, 9};
    
    public Pawn(final Alliance alliance, final int position) {
        super(PieceType.PAWN, position, alliance);
    }

    @Override
    public Collection<Move> calculatePossibleMoves(final Board board) {
        
        final ArrayList<Move> possibleMoves = new ArrayList<>(); 
        
        for(final int currentMove : allPossibleMoves){
           final int nextTile = this.position + (this.getPieceAlliance().getDirection() * currentMove);
            
            if(!Utilization.isValidTileCoordinate(nextTile)){
                continue;
            }
            if(currentMove == 8 && !board.getTile(nextTile).isTileOccupied()){
                possibleMoves.add(new Move.NonAttackingMove(board, this, nextTile));
            }
            else if(currentMove == 16 && this.isFirstMove() && (Utilization.secondRow[this.position] && this.getPieceAlliance().isBlack()) || (Utilization.seventhRow[this.position] && this.getPieceAlliance().isWhite())){
                final int behindNextTile = this.position + (this.getPieceAlliance().getDirection() * 8);
                
                if(!board.getTile(behindNextTile).isTileOccupied() && !board.getTile(nextTile).isTileOccupied()){
                    possibleMoves.add(new Move.NonAttackingMove(board, this, nextTile));
                }
            }
            
            else if(currentMove == 7 && !((Utilization.eighthColumn[this.position] && this.getPieceAlliance().isWhite()) || (Utilization.firstColumn[this.position] && this.getPieceAlliance().isBlack()))){
                if(board.getTile(nextTile).isTileOccupied()){
                    final Piece piece = board.getTile(nextTile).getPiece();
                    if(this.alliance != piece.getPieceAlliance()){
                        possibleMoves.add(new Move.NonAttackingMove(board, this, nextTile));
                    }
                }
            }
            
            else if(currentMove == 9 && !((Utilization.firstColumn[this.position] && this.getPieceAlliance().isWhite()) || (Utilization.eighthColumn[this.position] && this.getPieceAlliance().isBlack()))){
                if(board.getTile(nextTile).isTileOccupied()){
                    final Piece piece = board.getTile(nextTile).getPiece();
                    if(this.alliance != piece.getPieceAlliance()){
                        possibleMoves.add(new Move.NonAttackingMove(board, this, nextTile));
                    }
                }     
            }  
        }
        return Collections.unmodifiableList(possibleMoves);
    }
    
    @Override
    public Piece movePiece(final Move move){
       return new Pawn(move.getMovingPiece().getPieceAlliance(), move.getDestination()); 
    }

    
    @Override
    public String toString(){
        return Piece.PieceType.PAWN.toString();
    }
}
