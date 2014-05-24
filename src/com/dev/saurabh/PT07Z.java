package com.dev.saurabh;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

/**
 * Created by ssaurab on 5/24/14.
 */
public class PT07Z {

    private static int [] dist;

    public static void main(String [] args){

        try{

            Map<String, GraphNode> graph = new HashMap<String, GraphNode>();

            Set<GraphNode> nodes = new HashSet<GraphNode>();


            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            int numberOfNodes = Integer.parseInt(br.readLine());
            int maxLength = 0;

            dist = new int[numberOfNodes];
            Arrays.fill(dist, -1);

            GraphNode src = null;
            GraphNode end;

            for(int i=0; i<numberOfNodes-1; i++){
                String [] pair = br.readLine().split(" ");

                src = new GraphNode(pair[0]);
                end = new GraphNode(pair[1]);

                if(graph.containsKey(src.id)){
                    src = graph.get(src.id);
                }
                else{
                    graph.put(src.id, src);
                }

                if(graph.containsKey(end.id)){
                    end = graph.get(end.id);
                }
                else{
                    graph.put(end.id, end);
                }

                src.neighbours.add(end);
                end.neighbours.add(src);

            }

            dfs(src, 0, 0);

            int maxDepth = Integer.MIN_VALUE;
            int nodeId = -1;

            for(int i=0; i<dist.length; i++){
                if(dist[i] > maxDepth){
                    maxDepth = dist[i];
                    nodeId = i+1;
                }
            }

            Arrays.fill(dist, -1);

            dfs(graph.get(String.valueOf(nodeId)), 0, 1);

            for(int i=0; i<dist.length; i++){
                if(dist[i] > maxDepth){
                    maxDepth = dist[i];
                    nodeId = i+1;
                }
            }

            System.out.println(maxDepth);
        }
        catch (Exception exc){
            exc.printStackTrace();
        }
    }

    private static void dfs(GraphNode src, int n, int booleanIndex) {

        dist[src.intId-1] = n;
        src.visited[booleanIndex] = true;

        for(GraphNode graphNode : src.neighbours){
            if(!graphNode.visited[booleanIndex]){
                dfs(graphNode, n+1, booleanIndex);
            }
        }
    }

    private static class GraphNode {

        private String id;
        private int intId;
        private boolean []visited = new boolean[2];


        public List<GraphNode> neighbours = new ArrayList<GraphNode>();

        public GraphNode(String id){
            intId = Integer.parseInt(id);
            this.id = id;
        }

        @Override
        public int hashCode(){
            return Integer.parseInt(id);
        }

        @Override
        public boolean equals(Object obj){
            return ((GraphNode) obj).id.equals(id);
        }

    }
}