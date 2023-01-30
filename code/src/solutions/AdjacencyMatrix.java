package solutions;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
public class AdjacencyMatrix {
    public List<HashMap<Integer, Integer>> getAdjacencyMatrix() {
        return adjacencyMatrix;
    }

    public void setAdjacencyMatrix() {
        List<HashMap<Integer, Integer>> adjacency_matrix = new ArrayList<>();
        HashMap<Integer, Integer> map = new HashMap<>();
        for(int i = 0; i < 112; i ++){
            HashMap<Integer, Integer> curmap = new HashMap<>(map);
            adjacency_matrix.add(curmap);
        }

        try{
            Scanner scanner = new Scanner(new File("src/solutions/adjnoun.gml"));
            int i = 0;
            while (i < 425 && scanner.hasNextLine()) {
                String curLine = scanner.nextLine();
                //System.out.println(curLine);
                if(curLine.length() >= 10){

                    if(curLine.substring(4,10).equals("source")){
                        //System.out.println("ok");

                        int curIdx = Integer.parseInt(curLine.substring(11));
                        String line2 = scanner.nextLine();

                        int target = Integer.parseInt(line2.substring(11));
                        adjacency_matrix.get(curIdx).put(target,1);
                        i++;
                    }

                }




            }}
        catch(FileNotFoundException e) {

        }
        this.adjacencyMatrix = adjacency_matrix;
    }
    public void printMatrix(){
        System.out.println("The adjacency matrix is:");
        System.out.println(this.adjacencyMatrix);
    }


    private List<HashMap<Integer, Integer>> adjacencyMatrix;


    public void getMatrix(){
        List<List<Integer>> Matrix = new ArrayList<>();

        for(int i = 0; i < 112; i++){
            for(int j : this.adjacencyMatrix.get(i).keySet()){
                Matrix.add(new ArrayList<>());
                Matrix.get(Matrix.size()-1).add(i);
                Matrix.get(Matrix.size()-1).add(j);
                Matrix.get(Matrix.size()-1).add(Math.abs(i-j));

            }
        }
        System.out.println(Matrix);
    }
}
