import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import entities.*;

class GraphicaPanel extends JPanel {

    private Network network;
    private double overallUtility;

    public GraphicaPanel(Network network, double overallUtility)
    {
        this.network = network;
        this.overallUtility = overallUtility;
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Draw agents
        int agentX = 100;
        int agentYStart = 100;
        int agentYSpacing = 100;

        for (int i = 0; i < network.getAgents().size(); i++) {
            Agent agent = network.getAgents().get(i);
            int agentY = agentYStart + i * agentYSpacing;
            g2d.setColor(Color.BLUE);
            g2d.fillOval(agentX - 15, agentY - 15, 30, 30);
            g2d.setColor(Color.BLACK);
            g2d.drawString("Agent " + (i + 1), agentX - 15, agentY - 20);

             // Draw agent's alpha value
             g2d.drawString("Alpha: " + agent.alpha, agentX + 40, agentY);
        }

        // Draw resources
        int resourceX = 600;
        int resourceYStart = 100;
        int resourceYSpacing = 100;

        for (int i = 0; i < network.getResources().size(); i++) {
            Resource resource = network.getResources().get(i);
            int resourceY = resourceYStart + i * resourceYSpacing;
            g2d.setColor(Color.GREEN);
            g2d.fillRect(resourceX - 15, resourceY - 15, 30, 30);
            g2d.setColor(Color.BLACK);
            g2d.drawString("Resource " + (i + 1), resourceX - 15, resourceY - 20);

            // Draw allocated objects
            g2d.drawString("Allocated: " + resource.current_objects, resourceX + 20, resourceY);

            // Draw minimum price
            g2d.drawString("Min Price: " + resource.initialPrice, resourceX + 20, resourceY + 20);

            // Draw beta value
            g2d.drawString("Beta: " + resource.beta, resourceX + 20, resourceY + 40);
        }
        

      // Draw edges and allocation details
      for (Edge edge : network.getEdges()) {
        int agentIndex = network.getAgents().indexOf(edge.getX());
        int resourceIndex = network.getResources().indexOf(edge.getY());

        int agentY = agentYStart + agentIndex * agentYSpacing;
        int resourceY = resourceYStart + resourceIndex * resourceYSpacing;

        g2d.setColor(Color.RED);
        g2d.drawLine(agentX, agentY, resourceX, resourceY);

        // Draw the number of objects allocated from the agent to the resource
        String allocationDetails = String.valueOf(network.allocationMatrix.getElement(agentIndex, resourceIndex));
            
        // Calculate position slightly offset from the center
        double offset = 0.6;  // Offset factor (0.5 is center, 0.6 is slightly towards resource)
        int textX = (int) (agentX * (1 - offset) + resourceX * offset);
        int textY = (int) (agentY * (1 - offset) + resourceY * offset);
        g2d.drawString(allocationDetails, textX, textY);

          // Get priority of the resource for the agent
          int priority = edge.getX().priorityList.indexOf(edge.getY());

          if(priority == -1)
          {
            // Draw priority
          g2d.drawString("new edge: ", agentX - 70, 10 + agentY + (resourceY - agentY) / 4);
          }
          else{
                 // Draw priority
          g2d.drawString("Priority: " + priority, agentX - 70, agentY + (resourceY - agentY) / 4);
          }
         
        
    }

        // Draw overall utility
        g2d.setColor(Color.BLACK);
        g2d.drawString("Overall Utility: " + overallUtility, 350, 50);
    }
}

