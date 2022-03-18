/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chess.project1.Board;

import chess.project1.Board.Board.Builder;
import chess.project1.Pieces.Pawn;
import chess.project1.Pieces.Piece;
import chess.project1.Pieces.Rook;

/**
 *
 * @author ZIAD
 */
public abstract class Move {
    
    final Board board;
    final Piece movingPiece;
    final int destination;
    //public static final Move INVALID_MOVE = new InvalidMove();

    private Move(final Board board, final Piece movingPiece, final int destination) {
        this.board = board;
        this.movingPiece = movingPiece;
        this.destination = destination;
    }
    
    
    
    public int getCurrentCoordinate(){
        return this.getMovingPiece().getPiecePosition();
    }
    
    public int getDestination(){
        return this.destination;
    }
    
    public boolean isAttack(){
        return false;
    }
    
    public boolean isCastlingMove(){
        return false;
    }
    
    public Piece getAttackedPiece(){
        return null;
    }
    
    public Piece getMovingPiece(){
        return this.movingPiece;
    }
    
    public Board execute(){
        final Board.Builder builder = new Builder();
        for(final Piece piece : this.board.currentPlayer().getActivePieces()){
            if(!this.movingPiece.equals(piece)){
                builder.setPiece(piece);
            }
        }
        for(final Piece piece : this.board.currentPlayer().getOpponent().getActivePieces()){
            builder.setPiece(piece);
        }
        builder.setPiece(this.movingPiece.movePiece(this)); // move the moving piece
        builder.setMoveMaker(this.board.currentPlayer().getOpponent().getAlliance()); // sets the next move maker to the opponent
        return builder.build();
    }
    
    public static class AttackingMove extends Move{

        final Piece attackedPiece;
        
        public AttackingMove(final Board board, final Piece movingPiece, final int destination, final Piece attackedPiece) {
            super(board, movingPiece, destination);
            this.attackedPiece = attackedPiece;
        }
        
        @Override
        public boolean isAttack(){
            return true;
        }
        
        @Override
        public Piece getAttackedPiece(){
            return this.attackedPiece;
        }
        
        
    }
    
    public static final class NonAttackingMove extends Move{
        
        public NonAttackingMove(final Board board, final Piece movingPiece, final int destination) {
            super(board, movingPiece, destination);
        }
    }
    
    public static final class PawnMove extends Move{
        public PawnMove(final Board board, final Piece movingPiece, final int destination) {
            super(board, movingPiece, destination);
        }
    }
    
    public static class PawnAttackMove extends AttackingMove{
        public PawnAttackMove(final Board board, final Piece movingPiece, final int destination, final Piece attackedPiece) {
            super(board, movingPiece, destination, attackedPiece);
        }
    }
    
    public static final class PawnEnPassantAttackMove extends PawnAttackMove{
        public PawnEnPassantAttackMove(final Board board, final Piece movingPiece, final int destination, final Piece attackedPiece) {
            super(board, movingPiece, destination, attackedPiece);
        }
    }
    
    public static final class PawnJump extends Move{
        public PawnJump(final Board board, final Piece movingPiece, final int destination) {
            super(board, movingPiece, destination);
        }
        
        @Override
        public Board execute(){
            final Builder builder = new Builder();
            for(final Piece piece : this.board.currentPlayer().getActivePieces()){
                if(!(this.movingPiece.equals(piece))){
                    builder.setPiece(piece);
                }
            }
            for(final Piece piece : this.board.currentPlayer().getOpponent().getActivePieces()){
                builder.setPiece(piece);
            }
            final Pawn movedPawn = (Pawn) this.movingPiece.movePiece(this);
            builder.setPiece(movedPawn);
            builder.setEnPassantPawn(movedPawn);
            builder.setMoveMaker(this.board.currentPlayer().getOpponent().getAlliance());
            return builder.build();
        }
    }
    
    static abstract class CastleMove extends Move{
        protected final Rook castleRook;
        protected final int castleRookStart;
        protected final int castleRookDestination;
        
        public CastleMove(final Board board, final Piece movingPiece, final int destination, final Rook castleRook, final int castleRookStart, final int castleRookDestination) {
            super(board, movingPiece, destination);
            this.castleRook = castleRook;
            this.castleRookStart = castleRookStart;
            this.castleRookDestination = castleRookDestination;
        }
        
        public Rook getCastleRook(){
            return this.castleRook;
        }
        
        @Override
        public boolean isCastlingMove(){
            return true;
        }
        
        @Override
        public Board execute(){
            final Builder builder = new Builder();
            for(final Piece piece : this.board.currentPlayer().getActivePieces()){
                if(!(this.movingPiece.equals(piece)) && !this.castleRook.equals(piece)){
                    builder.setPiece(piece);
                }
            }
            for(final Piece piece : this.board.currentPlayer().getOpponent().getActivePieces()){
                builder.setPiece(piece);
            }
            builder.setPiece(this.movingPiece.movePiece(this));
            builder.setPiece(new Rook(this.castleRook.getPieceAlliance(), this.castleRookDestination));
            builder.setMoveMaker(this.board.currentPlayer().getOpponent().getAlliance());
            return builder.build();
        }
    } 
    public static final class KingSideCastleMove extends CastleMove{
        public KingSideCastleMove(final Board board, final Piece movingPiece, final int destination, final Rook castleRook, final int castleRookStart, final int castleRookDestination) {
            super(board, movingPiece, destination, castleRook, castleRookStart, castleRookDestination);
        }
        
        @Override
        public String toString(){
            return "0-0";
        }
    }
        
    public static final class QueenSideCastleMove extends CastleMove{
        public QueenSideCastleMove(final Board board, final Piece movingPiece, final int destination, final Rook castleRook, final int castleRookStart, final int castleRookDestination) {
            super(board, movingPiece, destination, castleRook, castleRookStart, castleRookDestination);
        }
        
        @Override
        public String toString(){
            return "0-0-0";
        }
    }
    
    public static final class InvalidMove extends Move{
        public InvalidMove(final Board board, final Piece movingPiece, final int destination) {
            super(board, movingPiece, destination);
        }
        @Override
        public Board execute(){
            throw new RuntimeException("cannot execute the invalid move");
        }
        
        @Override
        public int getCurrentCoordinate(){
            return -1;
        }
    }
    
    public static class MoveFactory{
        private MoveFactory(){
            throw new RuntimeException("Not instantiable");
        }
        
        public static Move createMove(final Board board, final int currentCoordinate, final int destinationCoordinate){
            
            for(final Move move : board.currentPlayer().getLegalMoves()){
                if(move.getCurrentCoordinate() == currentCoordinate && move.getDestination() == destinationCoordinate){
                    return move;
                }
            }
          //  return INVALID_MOVE;
          return null;
        }
    }

}