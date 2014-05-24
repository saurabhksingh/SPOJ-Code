package com.dev.saurabh;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

/**
 * Created by ssaurab on 3/18/14.
 */
public class SAMToyCars {

    private static class Car{
        public int occurrence;
        public String id;
    }

    static class PQsort implements Comparator<Car> {

        public int compare(Car one, Car two) {
            return one.occurrence - two.occurrence;
        }
    }

    public static void main(String [] args){
        try{

            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

            int testCases = Integer.valueOf(br.readLine());

            for(int i=0; i<testCases; i++){
                String [] metaData = br.readLine().trim().split(" ");
                int n = Integer.valueOf(metaData[0]);
                int numberOfCarsOnFloor = Integer.valueOf(metaData[1]);
                int p = Integer.valueOf(metaData[2]);

                List<String> carsSequence = new ArrayList<String>(p-numberOfCarsOnFloor);

                PriorityQueue<Car> pq = new PriorityQueue<Car>(p, new PQsort());
                PriorityQueue<Car> pqOnFloor = new PriorityQueue<Car>(p, new PQsort());

                int [] carIds = new int[p];
                Map<String, Integer> map = new HashMap<String, Integer>();
                Set<String> carsOnFloor = new HashSet<String>();
                for(int k=0; k<numberOfCarsOnFloor; k++){
                    carsOnFloor.add(br.readLine().trim());
                }
                for(int j=0; j<(p-numberOfCarsOnFloor); j++){
                    String id = br.readLine().trim();
                    if(map.get(id) == null){
                        map.put(id, 0);
                    }
                    carsSequence.add(id);
                    map.put(id, map.get(id)+1);
                }
                Set<Map.Entry<String, Integer>> entries = map.entrySet();
                for(Map.Entry<String, Integer> entry : entries){
                    Car car = new Car();
                    car.id = entry.getKey();
                    car.occurrence = entry.getValue();
                    pq.add(car);
                    if(carsOnFloor.contains(car.id)){
                        pqOnFloor.add(car);
                    }
                }

                for(String id : carsOnFloor){
                    if(!map.containsKey(id)){
                        Car car = new Car();
                        car.id = id;
                        car.occurrence = 0;
                        pqOnFloor.add(car);
                    }
                }

                int replacementCount = numberOfCarsOnFloor;

                for(String id : carsSequence){
                    if(!carsOnFloor.contains(id)){
                        replacementCount++;
                        Car car = pqOnFloor.poll();
                        carsOnFloor.remove(car.id);
                        carsOnFloor.add(id);
                        if(car.occurrence > 0){
                            car.occurrence = car.occurrence-1;
                            pqOnFloor.add(car);
                        }
                    }
                }

                System.out.println(replacementCount);
            }
        }
        catch(Exception exc){
            exc.printStackTrace();
        }
    }
}
