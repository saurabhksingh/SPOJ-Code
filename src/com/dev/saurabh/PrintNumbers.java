package com.dev.saurabh;

import java.util.*;

/**
 * Created by ssaurab on 5/23/14.
 */
public class PrintNumbers {

    private static Map<String,  List<List<Integer>>> cache = new HashMap<String,  List<List<Integer>>>();

    public static void main(String [] args) throws Exception {
        int sum = 6;

        List<List<Integer>> result = getAllDistinctConstituentsForGivenSum(1, sum);

        for(List<Integer> list : result){
            Collections.sort(list);
            StringBuilder sb = new StringBuilder();
            for(int i=0; i<list.size()-1; i++){
                sb.append(list.get(i)).append(" + ");
            }
            sb.append(list.get(list.size()-1));

            System.out.println(sb);
        }
    }

    static List<List<Integer>> getAllDistinctConstituentsForGivenSum(int start, int target) {

        List<List<Integer>> resultArray = new ArrayList<List<Integer>>();
        String key = start + ":" + target;
        if(cache.containsKey(key)){
            //System.out.println("returning from cache for start:"+start + " and target:"+target);
            return cache.get(key);
        }
        if (target == 0) {
            resultArray.add(new ArrayList<Integer>());
            return resultArray;
        }

        for (int i = start; i <= target; i++) {
            List<List<Integer>> intermediateResult = getAllDistinctConstituentsForGivenSum(i + 1, target - i);
            cache.put((i + 1)+":"+(target - i), intermediateResult);
            for (List<Integer> subSolution : intermediateResult) {

                List<Integer> intermediateSequence = new ArrayList<Integer>(subSolution);
                intermediateSequence.add(i);
                resultArray.add(intermediateSequence);
            }

        }

        return resultArray;
    }
}
