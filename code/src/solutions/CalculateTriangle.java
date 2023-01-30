package solutions;

import java.util.HashMap;
import java.util.List;

public class CalculateTriangle {

    public static void calTriangle(List<HashMap<Integer, Integer>> adjacencyMatrix){
        int numOfTriangle = 0;
        for(int i = 0; i < 112; i++){
            for(int j = i + 1; j < 112; j++){
                for(int k = j + 1; k < 112; k++){
                    if(adjacencyMatrix.get(i).getOrDefault(j, 0) == 1 || adjacencyMatrix.get(j).getOrDefault(i, 0) == 1){
                        if(adjacencyMatrix.get(j).getOrDefault(k, 0) == 1 || adjacencyMatrix.get(k).getOrDefault(j, 0) == 1){
                            if(adjacencyMatrix.get(i).getOrDefault(k, 0) == 1 || adjacencyMatrix.get(k).getOrDefault(i, 0) == 1){
//                                System.out.println("i:"+ i +" j:" + j + " k:" + k);
                                numOfTriangle++;
                            }
                        }
                    }
                }
            }
        }
        System.out.println("The number of triangle subgraph is "+ numOfTriangle);
    }
}
