

import solutions.*;

public class Main {
    public static void main(String[] args) {
        System.out.println("Question a:");
        //get the adjacency matrix
        //notes: to save the storage used, points with value 0 in matrix are not stored in the data structure
        AdjacencyMatrix adjacencyMatrix = new AdjacencyMatrix();
        adjacencyMatrix.setAdjacencyMatrix();
        adjacencyMatrix.printMatrix();
        System.out.println("---------------------------------------------------------");

        System.out.println("Question b:");
        //judge whether the graph is connected
        Connectivity.judgeConnectivity(adjacencyMatrix.getAdjacencyMatrix());


        //calculate the median degree of nodes
        CalculateMedianDegree.calculateMedianDegree(adjacencyMatrix.getAdjacencyMatrix());
        System.out.println("---------------------------------------------------------");

        System.out.println("Question c:");
        //calculate the number of triangles
        CalculateTriangle.calTriangle(adjacencyMatrix.getAdjacencyMatrix());
        System.out.println("---------------------------------------------------------");

        System.out.println("Question d:");
        KruskalAlgorithm.getMinSpanningTree(adjacencyMatrix.getAdjacencyMatrix());
        System.out.println("---------------------------------------------------------");

        System.out.println("Question e:");
        PrimAlgorithm.getMinSpanningTree(adjacencyMatrix.getAdjacencyMatrix());
        System.out.println("---------------------------------------------------------");


        //adjacencyMatrix.getMatrix();
    }

}