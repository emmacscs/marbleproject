import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import javax.swing.*;
import java.awt.*;

public class SimplePlot {

    public static void main(String[] args) {
        // Sample data - replace this with your double array
        double[] data = {1.0, 2.0, 3.0, 4.0, 5.0};

        // Create a series for the data
        XYSeries series = new XYSeries("Data");
        for (int i = 0; i < data.length; i++) {
            series.add(i + 1, data[i]); // Assuming x values are 1, 2, 3, ...
        }

        // Add the series to a dataset
        XYSeriesCollection dataset = new XYSeriesCollection();
        dataset.addSeries(series);

        // Create the chart
        JFreeChart chart = ChartFactory.createXYLineChart(
                "Simple Plot",
                "X",
                "Y",
                dataset
        );

        // Create and set up the frame
        JFrame frame = new JFrame("Simple Plot");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);

        // Add the chart to a panel
        ChartPanel chartPanel = new ChartPanel(chart);
        frame.getContentPane().add(chartPanel, BorderLayout.CENTER);

        // Display the frame
        frame.setVisible(true);
    }
}