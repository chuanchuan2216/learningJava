package oods4e.ch10.graphs;

import oods4e.ch04.queues.QueueInterface;

public interface WeightedGraphInterface<T> {
    boolean isEmpty();

    boolean isFull();

    void addVertex(T vertex);

    boolean hasVertex(T vertex);

    void addEdge(T fromVertex,T toVertex,int weight);

    int weightIs(T fromVertex,T toVertex);

    QueueInterface<T> getToVertices(T vertex);

    void markVertex(T vertex);

    void clearMarks();

    boolean isMarked(T vertex);

    T getUnmarked();
}
