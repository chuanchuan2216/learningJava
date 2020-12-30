package support;

public class Flight implements Comparable<Flight> {

    protected String fromVertex;
    protected String toVertex;
    protected int distance;

    public Flight(String fromVertex, String toVertex, int distance) {
        this.fromVertex = fromVertex;
        this.toVertex = toVertex;
        this.distance = distance;
    }

    public String getFromVertex() {
        return fromVertex;
    }

    public void setFromVertex(String fromVertex) {
        this.fromVertex = fromVertex;
    }

    public String getToVertex() {
        return toVertex;
    }

    public void setToVertex(String toVertex) {
        this.toVertex = toVertex;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    @Override
    public int compareTo(Flight other) {
        return (other.distance - this.distance);
    }

    @Override
    public String toString() {
        return fromVertex + "  " + toVertex + "  " + distance;
    }
}
