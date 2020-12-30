package ch02.figures;

public class Rectangle implements FigureInterface {

    protected double length, width;

    public Rectangle(double length, double width) {
        this.length = length;
        this.width = width;
    }

    @Override
    public double perimeter() {
        return (2 * (length + width));
    }

    @Override
    public double area() {
        return (length * width);
    }
}
