package solutions;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class CalculateMedianDegree {
    public static void calculateMedianDegree(List<HashMap<Integer, Integer>> adjacencyMatrix){
        int res = 0;
        HashMap<Integer, Integer> map = new HashMap<>();

        for(int i =0; i < 112; i++){

            HashMap<Integer, Integer> curRow = adjacencyMatrix.get(i);
            map.put(i,map.getOrDefault(i,0) + curRow.keySet().size());
            for(int j : curRow.keySet()){
                map.put(j,map.getOrDefault(j,0) + 1);
            }
        }
        //System.out.println(map);
        List<Integer> list = new ArrayList<>(map.values());

        Collections.sort(list);
        //System.out.println(list);
        System.out.println("The median degree is "+(list.get(56) + list.get(57))/2);
    }
}
