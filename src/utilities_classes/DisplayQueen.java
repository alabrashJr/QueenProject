package utilities_classes;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import main_classes.Queen;

public class DisplayQueen extends Application {

    
  @Override // Override the start method in the Application class
  public void start(Stage primaryStage) {
   
    Queen qq = new Queen();
    QueenVertex[] vertices=new QueenVertex[qq.getSize()];  
    int x,y ;
      for (int i = 0; i < qq.getSize(); i++) {
          x=(((int)(Math.random()*100000))%1150)+20;
          y=(((int)(Math.random()*100000))%550)+20;
          vertices[i]=new QueenVertex(x,y,qq.getColor(i));
      }
    UnweightedGraph<QueenVertex> q=new UnweightedGraph<>(vertices,qq.getEdgeList());
    Scene scene = new Scene(new GraphView(q), 1200, 600);
    primaryStage.setTitle("Queen"); // Set the stage title
    primaryStage.setScene(scene); // Place the scene in the stage
    primaryStage.show(); // Display the stage
  }
  
  
  /**
   * The main method is only needed for the IDE with limited
   * JavaFX support. Not needed for running from the command line.
   */
  public static void main(String[] args) {
    launch(args);
  }

    static class QueenVertex implements Displayable{
        private int x,y ;
        private Color color;

        public QueenVertex(int x, int y, Color color) {
            this.x = x;
            this.y = y;
            this.color = color;
        }
        
        @Override
        public int getX() {
            return x ; 
        }

        @Override
        public int getY() {
           return y;
        }

        @Override
        public Color getColor() {
           return color; 
        }
        
    }
}