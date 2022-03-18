/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chess.project1.Board;

import chess.project1.Pieces.Piece;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author ZIAD
 */
public abstract class Tile {
    
    protected final int TileCoordinate;
    private static final Map<Integer, EmptyTile> EMPTY_TILES_CACHE = createAllPossibleEmptyTiles();
    
    private static final Map<Integer, EmptyTile> createAllPossibleEmptyTiles(){    
        final Map<Integer, EmptyTile> emptyTileMap = new HashMap<>();
        for (int i = 0; i < 64; i++){
            emptyTileMap.put(i, new EmptyTile(i));
        }
        return Collections.unmodifiableMap(emptyTileMap);
    }
    public static Tile createTile(final int TileCoordinate, final Piece p){
        if(p != null){
            return new OccupiedTile(p, TileCoordinate);
        }
        else
            return EMPTY_TILES_CACHE.get(TileCoordinate);
        
    }
    private Tile(final int TileCoordinate) {
        this.TileCoordinate = TileCoordinate;
    }
    
    public int getTileCoordinate(){
        return this.TileCoordinate;
    }
    
    public abstract boolean isTileOccupied();
    
    public abstract Piece getPiece();
    
    public static class EmptyTile extends Tile{

        private  EmptyTile(final int TileCoordinate) {
            super(TileCoordinate);
        }
        public boolean isTileOccupied(){
            return false;
        }
        
        @Override
        public String toString(){
            return "-";
        }
        
        public Piece getPiece(){
            return null;
        }
    }
    
    public static class OccupiedTile extends Tile{
        private final Piece p;

        private OccupiedTile(final Piece p, final int TileCoordinate) {
            super(TileCoordinate);
            this.p = p;
        }
        
        public String toString(){
            if(getPiece().getPieceAlliance().isBlack())
                return getPiece().toString().toLowerCase();
            else
                return getPiece().toString();
        }
        
        public boolean isTileOccupied(){
            return true;
        }
        public Piece getPiece(){
            return this.p;
        }
    }
    
}
