import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class KMeans {

    //Method to perform the k-means algorithm on a list of vectors
    public static List<List<Vector>> runKMeans(int k, List<Vector> data) {
        Vector[] means = initMeans(k, data);
        List<List<Vector>> partitions = createPartitions(k, data, means);
        means = recalcMeans(partitions);
        boolean converged = false;
        while (!converged) {
            List<List<Vector>> newPartitions = createPartitions(k, data, means);
            if (newPartitions.equals(partitions)) {
                converged = true;
            } else {
                partitions = newPartitions;
                recalcMeans(partitions);
            }
        }
        return partitions;
    }

    //Select initial means using the Forgy method
    private static Vector[] initMeans(int k, List<Vector> data) {
        Vector[] means = new Vector[k];
        for (int i = 0; i < k; i++) {
            means[i] = data.get(new Random().nextInt(data.size()));
        }
        return means;
    }

    //creates partitions by grouping the vectors depending on which centroid they are closest to
    private static List<List<Vector>> createPartitions(int k, List<Vector> data, Vector[] means) {
        //Creates a new partitions list
        List<List<Vector>> partitions = new ArrayList<>();
        for (int i = 0; i < k; i++) {
            partitions.add(new ArrayList<Vector>());
        }
        //Adds each vector to the partition it is closest to the centroid of
        for (Vector v : data) {
            partitions.get(findNearestMean(means, v)).add(v);
        }
        return partitions;
    }

    //finds the index of the nearest centroid to a given point
    private static int findNearestMean(Vector[] means, Vector point) {
        double minDistance = Double.MAX_VALUE;
        int index = 0;
        for (int i = 0; i < means.length; i++) {
            double distance = Vector.distance(means[i], point);
            if (distance < minDistance) {
                minDistance = distance;
                index = i;
            }
        }
        return index;
    }

    //recalculates the means based on the new partitions
    private static Vector[] recalcMeans(List<List<Vector>> partitions) {
        Vector[] means = new Vector[partitions.size()];
        for (int i = 0; i < partitions.size(); i++) {
            means[i] = calculateMeans(partitions.get(i));
        }
        return means;
    }

    //Calculates the mean of a list of vectors
    private static Vector calculateMeans(List<Vector> vectors) {
        double x = 0;
        double y = 0;
        for (Vector v : vectors) {
            x += v.data[0];
            y += v.data[1];
        }
        x /= vectors.size();
        y /= vectors.size();
        return new Vector(new double[]{x, y});
    }
}
