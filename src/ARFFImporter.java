import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.List;

public class ARFFImporter {

    public static Relation importARFF(String file) {
        Relation relation = new Relation();
        int attribute_count = 0;
        //flag to indicate if we're reading data
        boolean data_input = false;
        int dimension = 0;
        //bitset to flag which attributes we are going to read in
        //since non-numeric attributes are discarded
        BitSet bitSet = new BitSet();
        List<Vector> vectorList = new ArrayList<>();
        //reads input file one line at a time
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            for (String line; (line = br.readLine()) != null; ) {
                if (!data_input) {
                    //grab the relationship name, may be useful
                    if (line.contains("@RELATION")) {
                        relation.relation = line.split(" ")[1];
                    //flag which attributes we want
                    } else if (line.contains("@ATTRIBUTE")) {
                        if (line.contains("REAL") || line.contains("INT")) {
                            bitSet.set(attribute_count);
                        }
                        attribute_count++;
                    //move on to reading data
                    } else if (line.contains("@DATA")) {
                        data_input = true;
                        dimension = bitSet.cardinality();
                    }
                } else {
                    double[] data = new double[dimension];
                    String values[] = line.split(",");
                    int count = 0;
                    //for each data line, iterate though values
                    //parsing those for the attributes we want
                    for (int i = 0; i < attribute_count; i++) {
                        if (bitSet.get(i)) {
                            data[count] = Double.parseDouble(values[i]);
                            count++;
                        }
                    }
                    Vector vector = new Vector(data);
                    vectorList.add(vector);
                }

            }
        } catch (Exception e) {e.printStackTrace();}

        relation.data = vectorList;


        return relation;
    }


}
