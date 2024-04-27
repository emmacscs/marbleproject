import javax.swing.*;
import java.awt.*;

public class plot extends JFrame {

    private double[] data;

    public plot(double[] data) {
        this.data = data;

        setTitle("Simple Plot");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);

        PlotPanel plotPanel = new PlotPanel();
        add(plotPanel, BorderLayout.CENTER);
    }

    class PlotPanel extends JPanel {

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);

            Graphics2D g2d = (Graphics2D) g;

            // Determine plot size
            int plotWidth = getWidth() - 100;
            int plotHeight = getHeight() - 100;

            // Calculate scale factors
            double maxX = data.length;
            double maxY = getMax(data);
            double scaleX = plotWidth / maxX;
            double scaleY = plotHeight / maxY;

            // Draw axis
            g2d.drawLine(50, plotHeight + 50, plotWidth + 50, plotHeight + 50); // X-axis
            g2d.drawLine(50, 50, 50, plotHeight + 50); // Y-axis

            // Draw axis labels
            g2d.drawString("Iteration", plotWidth + 30, plotHeight + 70);
            g2d.drawString("Utility Function", 20, 30);

            // Draw scale numbers on x-axis
            int numXScale = 10; // Number of scale numbers on x-axis
            double xScaleInterval = maxX / numXScale;
            for (int i = 0; i <= numXScale; i++) {
                int x = (int) (i * xScaleInterval * scaleX) + 50;
                g2d.drawString(Integer.toString((int) (i * xScaleInterval)), x, plotHeight + 70);
            }

            // Draw scale numbers on y-axis
            int numYScale = 10; // Number of scale numbers on y-axis
            double yScaleInterval = maxY / numYScale;
            for (int i = 0; i <= numYScale; i++) {
                int y = (int) (plotHeight - i * yScaleInterval * scaleY) + 50;
                g2d.drawString(Integer.toString((int) (i * yScaleInterval)), 20, y);
            }

            // Draw data points
            g2d.setColor(Color.BLUE);
            for (int i = 0; i < data.length; i++) {
                int x = (int) (i * scaleX) + 50;
                int y = (int) (plotHeight - data[i] * scaleY) + 50;
                g2d.fillOval(x - 3, y - 3, 6, 6);
            }

            // Find minimum value
            double min = getMin(data);
            int minX = (int) (indexOf(data, min) * scaleX) + 50;
            int minY = (int) (plotHeight - min * scaleY) + 50;

            // Draw red point at the minimum value
            g2d.setColor(Color.RED);
            g2d.fillOval(minX - 3, minY - 3, 6, 6);
        }

        // Helper method to find the maximum value in an array
        private double getMax(double[] array) {
            double max = Double.MIN_VALUE;
            for (double value : array) {
                if (value > max) {
                    max = value;
                }
            }
            return max;
        }

        // Helper method to find the minimum value in an array
        private double getMin(double[] array) {
            double min = Double.MAX_VALUE;
            for (double value : array) {
                if (value < min) {
                    min = value;
                }
            }
            return min;
        }

        // Helper method to find the index of a value in an array
        private int indexOf(double[] array, double value) {
            for (int i = 0; i < array.length; i++) {
                if (array[i] == value) {
                    return i;
                }
            }
            return -1; // Value not found
        }
    }
}