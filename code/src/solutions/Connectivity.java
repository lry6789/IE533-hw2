package solutions;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class Connectivity {
    public static void judgeConnectivity(List<HashMap<Integer, Integer>> adjacencyMatrix){
        HashSet<Integer> connectedNodes = new HashSet<>();
        for(int i =0; i < 112; i++){
            //System.out.println("curidx:"+i);

            HashMap<Integer, Integer> curRow = adjacencyMatrix.get(i);
            for(int j : curRow.keySet()){
                    //System.out.println(j);
                    connectedNodes.add(i);
                    connectedNodes.add(j);

            }
        }
        //System.out.println(connectedNodes);
        //System.out.println(connectedNodes.size());

        if(connectedNodes.size() == 112){
            System.out.println("The graph is connected");
        }
        else{
            System.out.println("The graph is not connected");
        }
        }



}
