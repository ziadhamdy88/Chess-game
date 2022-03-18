/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chess.project1.Pieces;

import chess.project1.Alliance;
import chess.project1.Board.Board;
import chess.project1.Board.Move;
import java.util.Collection;
import java.util.List;

/**
 *
 * @author ZIAD
 */
public abstract class Piece {
    
    protected final int position;
    protected final Alliance alliance;
    protected final boolean isFirstMove;
    protected final PieceType pieceType;
    private final int cashedHashCode;
    
    public Piece(final PieceType pieceType ,int position, Alliance alliance) {
        this.position = position;
        this.alliance = alliance;
        this.isFirstMove = false;
        this.pieceType = pieceType;
        this.cashedHashCode = computeHashCode();
    }
    
    private int computeHashCode(){
        int result = pieceType.hashCode();
        result = 31 * result + alliance.hashCode();
        result = 31 * result + position;
        result = 31 * result + (isFirstMove ? 1 : 0);
        return result;
    }
    
    @Override
    public boolean equals(final Object other){
        if(this == other){
            return true;
        }
        if(!(other instanceof Piece)){
            return false;
        }
        final Piece otherPiece = (Piece) other;
        return position == otherPiece.getPiecePosition() && pieceType == otherPiece.getPieceType() && alliance == otherPiece.getPieceAlliance() && isFirstMove == otherPiece.isFirstMove();
    }
    
    @Override
    public int hashCode(){
       return this.cashedHashCode;
    }
    
    public PieceType getPieceType(){
        return this.pieceType;
    }
    
    public int getPiecePosition(){
        return this.position;
    }

    public Alliance getPieceAlliance(){
        return this.alliance;
    }
    
    public boolean isFirstMove(){ 
        return this.isFirstMove;
    }
    
    public abstract Collection<Move> calculatePossibleMoves(final Board board);
    public abstract Piece movePiece(Move move);
    
    public enum PieceType{
        
        PAWN("P") {
            @Override
            public boolean isKing() {
                return false;
            }

            @Override
            public boolean isRook() {
                return false;
            }
        },
        KNIGHT("N") {
            @Override
            public boolean isKing() {
                return false;
            }

            @Override
            public boolean isRook() {
                return false;
            }
        },
        BISHOP("B") {
            @Override
            public boolean isKing() {
                return false;
            }

            @Override
            public boolean isRook() {
                return false;
            }
        },
        ROOK("R") {
            @Override
            public boolean isKing() {
                return false;
            }

            @Override
            public boolean isRook() {
                return true;
            }
        },
        QUEEN("Q") {
            @Override
            public boolean isKing() {
                return false;
            }

            @Override
            public boolean isRook() {
                return false;
            }
        },
        KING("K") {
            @Override
            public boolean isKing() {
                return true;
            }

            @Override
            public boolean isRook() {
                return false;
            }
        };
        
        private String pieceName;
        
        PieceType(final String pieceName){
            this.pieceName = pieceName;
        }
        
        @Override
        public String toString(){
            return this.pieceName;
        }
        
        public abstract boolean isKing();

        public abstract boolean isRook();
        

    }
}