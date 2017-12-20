package main_classes;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.paint.Color;
import utilities_classes.AbstractGraph;
import utilities_classes.ColorUtils;
import utilities_classes.UnweightedGraph;

public class Queen {

    public Queen() {
        main();
    }
    private static Map<String, List<Integer>> colorMap 
            = new HashMap< String, List<Integer>>();
    private static UnweightedGraph g;
    private static final Color[] colors = {Color.CYAN, Color.GRAY, Color.MAGENTA, Color.LIGHTGRAY, Color.BLACK, Color.BLUE, Color.GREEN, Color.PURPLE, Color.YELLOW, Color.ORANGE,
        Color.RED, Color.PINK, Color.ALICEBLUE, Color.AQUA, Color.BROWN, Color.FUCHSIA, Color.MISTYROSE,Color.AZURE};
    private static ArrayList<Color> Vcolors; //to color the vertices , it should be passed to DisplayQueen Class 

    public static void main() {
        String path = "C:\\Users\\EDAAT\\Desktop\\queen11_11.col";
        g =readUnweightedGraphFromFile(path);
        Vcolors = heuristic(g);
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
        ColorUtils cUtils = new ColorUtils();
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
            String colorName = cUtils.getColorNameFromHex((int)(colors[j].getBlue()*255),(int)(colors[j].getGreen()*255),(int)(colors[j].getRed()*255)); // get the name of color using it hash number
            System.out.println(i + ".vertex is colored with     " + colorName);
            verticesColor.add(i, colors[j]);

            // sparet the vertices by them color and make a hashtable 
            List<Integer> temp = colorMap.get(colorName);
            if (temp == null) {
                temp = new ArrayList<>();
            }
            temp.add(i);
            colorMap.put(colorName, temp);

        }
        //print the hash table 
        for (String name : colorMap.keySet()) {
            String key = name.toString();
            List<Integer> value = colorMap.get(name);
            System.out.print(key+ "--->");
            for (int i = 0; i < value.size(); i++) {
                System.out.print(value.get(i)+"  ");
            }
            System.out.println("");
        }System.out.println("we used "+colorMap.size()+" colors to color the graph ");
        return verticesColor;
    }

    public int getSize() {
        return g.getSize();
    } //used in DisplayQueen to find the size of graph on Queen Class 

    public Color getColor(int i) { // used in DisplayQueen to find what the color of i.vertex 
        return Vcolors.get(i);
    }

    public int[][] getEdgeList() {
        return g.getEdgeList();
    } // inorder to init the graph on DisplayQueen 
}
