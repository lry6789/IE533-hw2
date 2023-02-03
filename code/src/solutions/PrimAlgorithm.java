package solutions;


import java.util.*;

public class PrimAlgorithm {
    private static Map<Integer, Map<Integer, Integer>> graph = new HashMap<>();
    private static boolean[] visited;
    private static int[] parent;
    private static int[] weight;

    private static List<HashMap<Integer,Integer>> matrix = new ArrayList<>();
    private static void prims(int start) {
        visited = new boolean[graph.size()];
        parent = new int[graph.size()];
        weight = new int[graph.size()];

        for (int i = 0; i < graph.size(); i++) {
            weight[i] = Integer.MAX_VALUE;
        }

        weight[start] = 0;
        parent[start] = -1;

        PriorityQueue<Pair> pq = new PriorityQueue<>(graph.size(), (a, b) -> a.weight - b.weight);
        pq.add(new Pair(start, weight[start]));

        while (!pq.isEmpty()) {
            int vertex = pq.poll().vertex;

            visited[vertex] = true;

            for (Map.Entry<Integer, Integer> edge : graph.get(vertex).entrySet()) {
                int to = edge.getKey();
                int weight = edge.getValue();

                if (!visited[to] && weight < PrimAlgorithm.weight[to]) {
                    PrimAlgorithm.weight[to] = weight;
                    parent[to] = vertex;
                    pq.add(new Pair(to, PrimAlgorithm.weight[to]));
                }
            }
        }
    }

    private static class Pair {
        int vertex;
        int weight;

        Pair(int vertex, int weight) {
            this.vertex = vertex;
            this.weight = weight;
        }
    }

    public static void getMinSpanningTree(List<HashMap<Integer, Integer>> adjacency_matrix) {


        double startTime = System.currentTimeMillis();
        for (int i = 0; i < 112; i++) {
            graph.put(i, new HashMap<>());
        }

        for (int i = 0; i < 112; i++) {
            for(int key : adjacency_matrix.get(i).keySet()) {


                graph.get(i).put(key, Math.abs(i-key));
                graph.get(key).put(i, Math.abs(i-key));
            }
        }

        prims(0);
        double endTime = System.currentTimeMillis();
        double usedTime = (endTime-startTime)/1000;

        System.out.println("The minimum spanning tree(undirected graph) found by Kruskal’s algorithm is ");
        HashMap<Integer, Integer> map = new HashMap<>();
        for(int i = 0; i < 112; i ++){
            HashMap<Integer, Integer> curmap = new HashMap<>(map);
            matrix.add(curmap);
        }

        for (int i = 1; i < 112; i++) {
            matrix.get(i).put(parent[i], 1);
            matrix.get(parent[i]).put(i, 1);
        }
        System.out.println(matrix);
        System.out.println("Runtime:"+usedTime+"s");



        int cost = 0;
        for (int i = 0; i < 112; i++) {
            cost += weight[i];
        }
        System.out.println("The optimal cost is: "+ cost);
    }
}

