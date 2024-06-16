import javax.swing.*;
import java.awt.*;


public class Graphica extends JFrame {
    
    private Network network;
    private double overallUtility;

    public Graphica(Network network, double overallUtility) {
        this.network = network;
        this.overallUtility = overallUtility;

        setTitle("Final Network Visualization");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);

        GraphicaPanel graphicaPanel = new GraphicaPanel(network, overallUtility);
        add(graphicaPanel, BorderLayout.CENTER);
    }
}
