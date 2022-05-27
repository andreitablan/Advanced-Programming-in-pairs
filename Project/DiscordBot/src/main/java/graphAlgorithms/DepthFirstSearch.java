package graphAlgorithms;

import java.util.Iterator;
import java.util.LinkedList;

public class DepthFirstSearch {
    private int numberOfVertex;
    String nodes="";
    private LinkedList<Integer> listOfNeighbours[];

    public DepthFirstSearch(String input)
    {
        //4 2 0-1 0-2 1-2 2-0 2-3
        this.numberOfVertex = Character.getNumericValue(input.charAt(0));

        listOfNeighbours = new LinkedList[numberOfVertex];
        for (int index = 0; index < numberOfVertex; ++index)
            listOfNeighbours[index] = new LinkedList();

        for(int index = 1; index < input.length(); index++)
        {
            if(input.charAt(index) == '-')
            {
                this.addEdge(Character.getNumericValue(input.charAt(index-1)), Character.getNumericValue(input.charAt(index+1)));
            }
        }
        this.DFS(Character.getNumericValue(input.charAt(2)));
    }

    private void addEdge(int vertex1, int vertex2)
    {
        listOfNeighbours[vertex1].add(vertex2);
        listOfNeighbours[vertex2].add(vertex1);
    }

    private void DFSUtil(int vertex, boolean visited[])
    {
        visited[vertex] = true;
        String node=String.valueOf(vertex);
        nodes=nodes+ node + " ";

        Iterator<Integer> iterator = listOfNeighbours[vertex].listIterator();
        while (iterator.hasNext()) {
            int next = iterator.next();
            if (!visited[next])
                DFSUtil(next, visited);
        }
    }
    private void DFS(int vertex)
    {
        boolean visited[] = new boolean[numberOfVertex];
        DFSUtil(vertex, visited);
    }

    public String getNodes() {
        return nodes;
    }
}
