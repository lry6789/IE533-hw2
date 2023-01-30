package solutions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class KruskalAlgorithm {

    static List<HashMap<Integer, Integer>> curMatrix = new ArrayList<>();

    static HashSet<Integer> curNodes = new HashSet<>();

    public static void getMinSpanningTree(List<HashMap<Integer, Integer>> adjacencyMatrix){
        double startTime = System.currentTimeMillis();

        for(int i = 0; i < 112; i ++){
            HashMap<Integer, Integer> curmap = new HashMap<>();
            curMatrix.add(curmap);
        }
        //System.out.println(curMatrix);


        for(int i = 1; i < 112; i++){
            find(i,adjacencyMatrix);
            if(curNodes.size() == 112){
                break;
            }
        }
        //System.out.println(curNodes);
        double endTime = System.currentTimeMillis();
        double usedTime = (endTime-startTime)/1000;
        System.out.println("The minimum spanning tree(undirected graph) found by Kruskal’s algorithm is ");
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

    private static void find(int curCost, List<HashMap<Integer, Integer>> adjacencyMatrix){
        for(int i = 0; i < 112 - curCost; i++){

            int j = i + curCost;
            //find edge
            if(adjacencyMatrix.get(i).getOrDefault(j,0) !=0||adjacencyMatrix.get(j).getOrDefault(i,0) !=0){
//                List<HashMap<Integer, Integer>> pastMatrix = new ArrayList<>(curMatrix);
                HashSet<Integer> pastNodes = new HashSet<>(curNodes);
//                System.out.println("past matirx");
//                System.out.println(curMatrix);
                //add to graph(undirected graph)
                curMatrix.get(i).put(j,1);
                curMatrix.get(j).put(i,1);
                curNodes.add(i);
                curNodes.add(j);
                //judge whether there are cycles or not
                //System.out.println(isCyclicDFS());
                if(isCyclicDFS()){
//                    System.out.println("resotre");
                    //curMatrix = new ArrayList<>(pastMatrix);
                    curMatrix.get(i).remove(j);
                    curMatrix.get(j).remove(i);
                    curNodes = new HashSet<>(pastNodes);
                }
//                System.out.println("The node num:"+curNodes.size());
                if(curNodes.size() == 112){
                    return;
                }
//                System.out.println("new matirx");
//                System.out.println(curMatrix);
            }


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
        for (int i: curNodes)
            if (!visited[i]) //Do not recurse on already visited vertices
                if (isCyclicUtilDFS(i, visited, -1))
                    return true;
        return false;
    }



}
