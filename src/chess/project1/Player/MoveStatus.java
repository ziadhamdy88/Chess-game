/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chess.project1.Player;

/**
 *
 * @author ZIAD
 */
public enum MoveStatus {
    DONE{
        @Override
        boolean isDone(){
            return true;
        }
    },
    ILLEGAL_MOVE{
        @Override
        boolean isDone() {
            return false;
        }
        
    }, 
    LEAVES_PLAYER_IN_CHECK{
        @Override
        boolean isDone(){
            return false;
        }
    };
    abstract boolean isDone();
}
