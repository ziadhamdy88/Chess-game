/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chess.project1.Player;

import chess.project1.Board.Board;
import chess.project1.Board.Move;

/**
 *
 * @author ZIAD
 */
public class MoveTransition {
    
    private final Board transitionBoard;
    private final Move move;
    private final MoveStatus moveStatus;
    
    public MoveTransition(final Board transitionBoard, final Move move, final MoveStatus moveStatus){
        this.transitionBoard = transitionBoard;
        this.move = move;
        this.moveStatus = moveStatus;
    }
    
    public MoveStatus getMoveStatus(){
        return this.moveStatus;
    }
}
