import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import java.util.List;

public class ScatterPlot {

    XYSeriesCollection data;
    String title;

    public ScatterPlot(List<List<Vector>> seriesList, String title) {
        this.data = createDataset(seriesList);
        this.title = title;
    }

    //Plots the scatter graph
    public void plot() {
        JFreeChart chart = ChartFactory.createScatterPlot(
                title,
                "X",
                "Y",
                data,
                PlotOrientation.VERTICAL,
                true,
                true,
                false
        );
        ChartFrame frame = new ChartFrame("k-means", chart);
        frame.pack();
        frame.setVisible(true);
    }

    //Generates the correct style of data set for the chart library from the partitions list
    private XYSeriesCollection createDataset(List<List<Vector>> seriesList) {
        XYSeriesCollection data = new XYSeriesCollection();
        for (int i = 0; i < seriesList.size(); i++) {
            XYSeries series = new XYSeries(i == seriesList.size() - 1 ? "Means" : "Cluster " + (i+1));
            for (Vector v : seriesList.get(i)) {
                series.add(v.data[0], v.data[1]);
            }
            data.addSeries(series);
        }
        return data;

    }
}
