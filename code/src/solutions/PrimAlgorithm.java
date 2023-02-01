package solutions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class PrimAlgorithm {

    static List<HashMap<Integer, Integer>> curMatrix = new ArrayList<>();

    static HashSet<Integer> curNodes = new HashSet<>();

    public static void getMinSpanningTree(List<HashMap<Integer, Integer>> adjacencyMatrix){
        double startTime = System.currentTimeMillis();

        for(int i = 0; i < 112; i ++){
            HashMap<Integer, Integer> curmap = new HashMap<>();
            curMatrix.add(curmap);
        }
        //System.out.println(curMatrix);

        curNodes.add(1);

        while(true){
            find(adjacencyMatrix);
            if(curNodes.size() == 112){
                break;
            }
        }
        double endTime = System.currentTimeMillis();
        double usedTime = (endTime-startTime)/1000;
        //System.out.println(curNodes);
        System.out.println("The minimum spanning tree(undirected graph) found by Prim’s algorithm is ");
        System.out.println(curMatrix);
        System.out.println("Runtime:"+usedTime+"s");
        int cost = 0;
        for(int i = 0; i < 112; i++){
            for(int j : curMatrix.get(i).keySet()){
                cost += Math.abs(j-i);
            }
        }
        System.out.println("The optimal cost is: "+ cost/2);
    }



    private static void find(List<HashMap<Integer, Integer>> adjacencyMatrix){

        HashSet<Integer> pastNodes = new HashSet<>(curNodes);
        int best_i = -1;
        int best_j = -1;
        int best_cost = 200;
        for(int i : curNodes){
            //out nodes
            for(int j : adjacencyMatrix.get(i).keySet()){
                if(!curNodes.contains(j)){
                    if(Math.abs(j-i) < best_cost){
                        best_i = i;
                        best_j = j;
                    }
                }

            }
            //in nodes
            for(int j = 0; j < 112; j++){
                if(adjacencyMatrix.get(j).containsKey(i)){
                    if(!curNodes.contains(j)){
                        if(Math.abs(j-i) <= best_cost){
                            best_i = i;
                            best_j = j;
                        }
                    }

                }

            }
        }
        //System.out.println(best_i);
        //System.out.println(best_j);
        curMatrix.get(best_i).put(best_j,1);
        curMatrix.get(best_j).put(best_i,1);
        curNodes.add(best_i);
        curNodes.add(best_j);

        //judge whether there are cycles or not
        //System.out.println(isCyclicDFS());
        if(isCyclicDFS()){
            if(!pastNodes.contains(best_j))
                curMatrix.get(best_i).remove(best_j);
            if(!pastNodes.contains(best_i))
                curMatrix.get(best_j).remove(best_i);
            curNodes = new HashSet<>(pastNodes);
        }



    }

    //Whether there is a cycle in the subgraph reachable by vertex i
    static boolean isCyclicUtilDFS(int i, boolean[] visited, int parent) {
        visited[i] = true;
        //Iterate over all neighbors of the current vertex

        for(int j : curMatrix.get(i).keySet()){
            //visit unvisited nodes
            if (!visited[j]) {

                //If there is a cycle, return true
                if (isCyclicUtilDFS(j, visited, i)){
                    return true;

                }

            }
            //This adjacent point is not the parent node of i on the DFS tree, and has been visited, then there is a cycle
            else if (j != parent){
                return true;
            }

        }
        return false;
    }
    //Returns true if there is a cycle in the undirected graph, otherwise returns false
    static boolean isCyclicDFS() {
        //mark all vertices as unvisited
        boolean[] visited = new boolean[112];
        for (int i = 0; i < 112; ++i) {
            visited[i] = false;
        }

        //For each unvisited vertex, call isCyclicUtil to determine whether the cycle exists
        for (int i = 0; i < 112; ++i)
            if (!visited[i]) //Do not recurse on already visited vertices
                if (isCyclicUtilDFS(i, visited, -1))
                    return true;
        return false;
    }



}