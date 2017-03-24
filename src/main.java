import java.util.List;

public class main {
    public static void main(String[] args) {
        Relation relation = ARFFImporter.importARFF(args[0]);
        List<List<Vector>> partitions = KMeans.runKMeans(Integer.parseInt(args[1]), relation.data);
        ScatterPlot sp = new ScatterPlot(partitions, relation.relation);
        sp.plot();
    }
}
