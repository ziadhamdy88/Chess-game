/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chess.project1;

import chess.project1.Board.Board;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.Control;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class ChessProject1 extends Application {
    
    static GridPane root;
    static StackPane square;
    static Board board;
    
    public void basicBoard(GridPane root, StackPane square) throws FileNotFoundException{
         for (int r = 0; r < 8; r++) {
            for (int c = 0; c < 8; c ++) {
                square = new StackPane();
                String color ;
                if ((r + c) % 2 == 0) {
                    color = "white";
                } else {
                    color = "black";
                }
                square.setStyle("-fx-background-color: "+color+";");
                root.setStyle("-fx-background-color: "+color+";");
                root.add(square, c, r);
            }
        }
        for (int i = 0; i < 8; i++) {
            root.getColumnConstraints().add(new ColumnConstraints(5, Control.USE_COMPUTED_SIZE, Double.POSITIVE_INFINITY, Priority.ALWAYS, HPos.CENTER, true));
            root.getRowConstraints().add(new RowConstraints(5, Control.USE_COMPUTED_SIZE, Double.POSITIVE_INFINITY, Priority.ALWAYS, VPos.CENTER, true));
        }
        //FileInputStream bp = new FileInputStream("C:\\Users\\ZIAD\\Desktop\\Chess Game\\Pieces\\white pawn.png");
        Image blackPawn = new Image("Pics/black pawn.png");
        ImageView blackpawn = new ImageView(blackPawn);
        //square.getChildren().add(blackpawn);
        root.add(blackpawn, 1, 0);
        root.add(blackpawn, 1, 1);
        root.add(blackpawn, 1, 2);
        root.add(blackpawn, 1, 3);
        root.add(blackpawn, 1, 4);
        root.add(blackpawn, 1, 5);
        root.add(blackpawn, 1, 6);
        root.add(blackpawn, 1, 7);
        
        
        Image whitePawn = new Image("Pics/white pawn.png");
        ImageView whitepawn = new ImageView(whitePawn);
        //square.getChildren().add(whitepawn);
        root.add(whitepawn, 6, 0);
        root.add(whitepawn, 6, 1);
        root.add(whitepawn, 6, 2);
        root.add(whitepawn, 6, 3);
        root.add(whitepawn, 6, 4);
        root.add(whitepawn, 6, 5);
        root.add(whitepawn, 6, 6);
        root.add(whitepawn, 6, 7);
        
        Image blackQueen = new Image("Pics/black queen.png");
        ImageView blackqueen = new ImageView(blackQueen);
        //square.getChildren().add(blackqueen);
        root.add(blackqueen, 0, 3);
       
        Image whiteQueen = new Image("Pics/white queen.png");
        ImageView whitequeen = new ImageView(whiteQueen);
        //square.getChildren().add(whitequeen);
        root.add(whitequeen, 7, 3);
        
        Image blackBishop = new Image("Pics/black bishop.png");
        ImageView blackbishop = new ImageView(blackBishop);
        //square.getChildren().add(blackbishop);
        root.add(blackbishop, 0, 2);
        root.add(blackbishop, 0, 5);
        
        Image whiteBishop = new Image("Pics/white bishop.png");
        ImageView whitebishop = new ImageView(whiteBishop);
        //square.getChildren().add(whitebishop);
        root.add(whitebishop, 7, 2);
        root.add(whitebishop, 7, 5);
        
        Image blackKnight = new Image("Pics/black knight.png");
        ImageView blackknight = new ImageView(blackKnight);
        //.getChildren().add(blackknight);
        root.add(blackknight, 0, 1);
        root.add(blackknight, 0, 6);
        
        Image whiteKnight = new Image("Pics/white knight.png");
        ImageView whiteknight = new ImageView(whiteKnight);
        //square.getChildren().add(whiteknight);
        root.add(whiteknight, 7, 1);
        root.add(whiteknight, 7, 6);
       
        Image blackRook = new Image("Pics/black rook.png");
        ImageView blackrook = new ImageView(blackRook);
        //square.getChildren().add(blackrook);
        root.add(blackrook, 0, 0);
        root.add(blackrook, 0, 7);
        
        Image whiteRook = new Image("Pics/white rook.png");
        ImageView whiterook = new ImageView(whiteRook);
        //square.getChildren().add(whiterook);
        root.add(whiterook, 7, 0);
        root.add(whiterook, 7, 7);
      
        Image blackKing = new Image("Pics/black king.png");
        ImageView blackking = new ImageView(blackKing);
        //square.getChildren().add(blackking);
        root.add(blackking, 0, 4);
        
        Image whiteKing = new Image("Pics/white king.png");
        ImageView whiteking = new ImageView(whiteKing);
        //square.getChildren().add(whiteking);
        root.add(whiteking, 7, 4);
        
    }
    
    @Override
    public void start(Stage primaryStage) throws FileNotFoundException {
        root = new GridPane();
        square = new StackPane();
        basicBoard(root, square);
        primaryStage.setScene(new Scene(root, 400, 400));
        primaryStage.setTitle("Chess Game");
        primaryStage.show();
        
        File file = new File("file:C:\\Users\\ZIAD\\Desktop\\Chess Game\\Highscores.txt");
        PrintWriter output = new PrintWriter(file);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
    /**
  * @param args the command line arguments
     */
 //   public static void main(String[] args) {
   //     launch(args);
  //        Board board = Board.createStandardBoard();
   //       System.out.println(board);
   // } 
//}
