package ch10.graphs;

import ch04.queues.LinkedQueue;
import ch04.queues.QueueInterface;

public class WeightedGraph<T> implements WeightedGraphInterface<T> {

    public static final int NULL_EDGE = 0;

    private static final int DEFCAP = 100;
    private int numVertices;
    private int maxVertices;
    private T[] vertices;
    private int[][] edges;
    private boolean[] marks;

    public WeightedGraph() {
        numVertices = 0;
        maxVertices = DEFCAP;
        vertices = (T[]) new Object[DEFCAP];
        edges = new int[DEFCAP][DEFCAP];
        marks = new boolean[DEFCAP];
    }

    public WeightedGraph(int maxV) {
        numVertices = 0;
        maxVertices = maxV;
        vertices = (T[]) new Object[maxV];
        edges = new int[maxV][maxV];
        marks = new boolean[maxV];
    }

    @Override
    public boolean isEmpty() {
        return (numVertices == 0);
    }

    @Override
    public boolean isFull() {
        return (numVertices == maxVertices);
    }

    @Override
    public void addVertex(T vertex) {
        vertices[numVertices] = vertex;
        for (int index = 0; index < numVertices; index++) {
            edges[numVertices][index] = NULL_EDGE;
            edges[index][numVertices] = NULL_EDGE;
        }
        numVertices++;
    }

    @Override
    public boolean hasVertex(T vertex) {//补充
        for (int index = 0; index < vertices.length; index++) {
            if (vertex.equals(vertices[index])) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void addEdge(T fromVertex, T toVertex, int weight) {
        int row;
        int column;

        row = indexIs(fromVertex);
        column = indexIs(toVertex);

        edges[row][column] = weight;
    }

    private int indexIs(T vertex) {
        int index = 0;
        while (!vertex.equals(vertices[index])) {
            index++;
        }
        return index;
    }

    @Override
    public int weightIs(T fromVertex, T toVertex) {
        int row;
        int column;

        row = indexIs(fromVertex);
        column = indexIs(toVertex);

        return edges[row][column];
    }

    @Override
    public QueueInterface<T> getToVertices(T vertex) {
        QueueInterface<T> adjVertices = new LinkedQueue<>();
        int fromIndex;
        int toIndex;
        fromIndex = indexIs(vertex);
        for (toIndex=0;toIndex<numVertices;toIndex++){
            if (edges[fromIndex][toIndex]!=NULL_EDGE){
                adjVertices.enqueue(vertices[toIndex]);
            }
        }
        return adjVertices;
    }

    @Override
    public void markVertex(T vertex) {
        int index = indexIs(vertex);
        marks[index]=true;
    }

    @Override
    public void clearMarks() {
        for (int index=0;index< marks.length;index++){
            marks[index]=false;
        }
    }

    @Override
    public boolean isMarked(T vertex) {
        int index = indexIs(vertex);
        return (marks[index]==true);
    }

    @Override
    public T getUnmarked() {
        T toReturn = null;
        for (int index=0;index<marks.length;index++){
            if (marks[index]==false){
                if (vertices[index]!=null){
                    toReturn = vertices[index];
                }
                break;
            }
        }
        return toReturn;
    }
}
