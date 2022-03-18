/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chess.project1.Player;

import chess.project1.Alliance;
import chess.project1.Board.Board;
import chess.project1.Board.Move;
import chess.project1.Pieces.King;
import chess.project1.Pieces.Piece;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 *
 * @author ZIAD
 */
public abstract class Player {
    
    protected final Board board;
    protected final King playerKing;
    protected final Collection<Move> legalMoves;
   // protected final Collection<Move> allMoves;
    private final boolean isInCheck;
    
   public Player(final Board board, final Collection<Move> legalMoves, final Collection<Move> opponentMoves){
        this.board = board;
        this.playerKing = King();
        this.legalMoves = Collections.unmodifiableList(Stream.of(legalMoves, calculateKingCastles(legalMoves, opponentMoves)).flatMap(Collection::stream).collect(Collectors.toList()));
       // this.allMoves = Collections.unmodifiableList(Stream.of(legalMoves, calculateKingCastles(legalMoves, opponentMoves)).flatMap(Collection::stream).collect(Collectors.toList()));
        this.isInCheck = !Player.calculateAttackOnTile(this.playerKing.getPiecePosition(), opponentMoves).isEmpty();
    }
   
   public King getPlayerKing(){
       return this.playerKing;
   }
   
   public Collection<Move> getLegalMoves(){
       return this.legalMoves;
   }
   
   protected static Collection<Move> calculateAttackOnTile(int piecePosition, Collection<Move> moves){
       final ArrayList<Move> attackMoves = new ArrayList<>();
       
       for(final Move move : moves){    
           if(piecePosition == move.getDestination()){
               attackMoves.add(move);
           }
       }
       return Collections.unmodifiableList(attackMoves);
   }
    
    private King King(){
        for(final Piece piece : getActivePieces()){
            if(piece.getPieceType().isKing()){
                return (King) piece;
            }
        }
        throw new RuntimeException("Not a valid board");
    }
    
    
    public abstract Collection<Piece> getActivePieces();
    
    public abstract Alliance getAlliance();
    
    public abstract Player getOpponent();
    
    public abstract Collection<Move> calculateKingCastles(Collection<Move> playerlegals, Collection<Move> opponentsLegals);
    
    public boolean isMoveLegal(final Move move){
       return this.legalMoves.contains(move);
    }
    
    public boolean check(){
        return this.isInCheck;
    }
    
    public boolean checkMate(){
        return this.isInCheck && !hasEscapeMoves();
    }
    
    protected boolean hasEscapeMoves(){
        for(final Move move : this.legalMoves){
            final MoveTransition transition = makeMove(move);
            if(transition.getMoveStatus().isDone()){
                return true;
            }
        }
        return false;
    }
    
    public boolean staleMate(){
        return !this.isInCheck && !hasEscapeMoves();
    }
    
    public boolean castle(){
        return false;
    }
    
    public MoveTransition makeMove(final Move move){
        if(!isMoveLegal(move)){
            return new MoveTransition(this.board, move, MoveStatus.ILLEGAL_MOVE);
        }
        final Board transitionBoard = move.execute();
        final Collection<Move> kingAttacks = Player.calculateAttackOnTile(transitionBoard.currentPlayer().getOpponent().getPlayerKing().getPiecePosition(), transitionBoard.currentPlayer().getLegalMoves());
        
        if(!kingAttacks.isEmpty()){
            return new MoveTransition(this.board, move, MoveStatus.LEAVES_PLAYER_IN_CHECK);
        }
        return new MoveTransition(transitionBoard, move, MoveStatus.DONE);
    }
}
