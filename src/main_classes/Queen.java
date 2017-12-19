package main_classes;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.paint.Color;
import utilities_classes.AbstractGraph;
import utilities_classes.UnweightedGraph;

public class Queen {

    public Queen() {
        main();
    }
    
    private static UnweightedGraph g;
    private static final Color[] colors = {Color.CYAN, Color.GRAY, Color.MAGENTA, Color.LIGHTGRAY, Color.BLACK, Color.BLUE, Color.GREEN, Color.PURPLE, Color.YELLOW, Color.ORANGE,
        Color.RED, Color.PINK, Color.ALICEBLUE, Color.AQUA, Color.BROWN, Color.FUCHSIA, Color.MISTYROSE};
    private static ArrayList<Color> Vcolors;
    public static void main() {
        String path = "C:\\Users\\EDAAT\\Desktop\\queen11_11.col";
        g = (UnweightedGraph) readUnweightedGraphFromFile(path);
       //  g.printEdges();
        Vcolors = heuristic(g);
      //  Queen q = new Queen();
        //int[][]temp = q.getEdgeList();
        System.out.println("    ");
    }

    public static UnweightedGraph readUnweightedGraphFromFile(String path) {
        try {
            int verticesNu = -1;
            String temp;
            String[] line;
            ArrayList<AbstractGraph.Edge> edge = new ArrayList<>();
            Scanner scanner = new Scanner(new File(path));
            while (scanner.hasNext()) {
                temp = scanner.nextLine();
                if (temp.startsWith("p")) {
                    verticesNu = Integer.parseInt(temp.split(" ")[2]); // number of vertices 
                }
                if (temp.startsWith("e")) {
                    line = temp.split(" ");
                    edge.add(new AbstractGraph.Edge(Integer.parseInt(line[1]) - 1, Integer.parseInt(line[2]) - 1));
                    //the file start from 1 but our abstract class is programmed to start from zero which cause an java.lang.IndexOutOfBoundsException
                }
            }
            return new UnweightedGraph(edge, verticesNu);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Queen.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public static ArrayList<Color> heuristic(UnweightedGraph g) {
        boolean[][] colorVer = new boolean[g.getSize()][g.getSize()];
        int j = 0;
        //int[] c = new int[g.getSize()];
        ArrayList<Color> verticesColor = new ArrayList<>();
        for (int i = 0; i < g.getSize(); i++) {
            for (int k = 0; k < g.getSize(); k++) {
                colorVer[i][j] = false;
            }
        }

        for (int i = 0; i < g.getSize(); i++) {
            j = 0;
            while (colorVer[i][j]) {
                j++;
            }
            for (Object v : g.getNeighbors(i)) {
                colorVer[(int) v][j] = true;
            }
            //c[i] = (Integer) j;
            System.out.println(i + "   i >>>>>>>>>> j " + j);
            verticesColor.add(i, colors[j]);// i get strengly an ArrayIndexOutOfBoundsException 

        }
        return verticesColor;
    }

    public int getSize() {
        return g.getSize();
    }

    public  Color getColor(int i) {
        return Vcolors.get(i);
    }

    public int[][] getEdgeList(){
        return g.getEdgeList();
    }
}
