/*
Class to represent a generalised vector
 */
public class Vector {
    double data[];

    public Vector(double data[]) {
        this.data = data;
    }

    public static double distance(Vector a, Vector b) {
        return Math.sqrt(Math.pow((a.data[0] - b.data[0]), 2.0) + Math.pow((a.data[1] - b.data[1]), 2.0));
    }
}
