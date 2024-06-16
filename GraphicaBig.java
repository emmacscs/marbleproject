import javax.swing.*;
import java.awt.*;


public class GraphicaBig extends JFrame {
    
    private Network network;
    private double overallUtility;

    public GraphicaBig(Network network, double overallUtility) {
        this.network = network;
        this.overallUtility = overallUtility;

        setTitle("Final Network Visualization");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);

        GraphicaPanelB graphicaPanelBig = new GraphicaPanelB(network, overallUtility);
        add(graphicaPanelBig, BorderLayout.CENTER);
    }
}
