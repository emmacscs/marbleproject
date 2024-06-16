import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import entities.*;

class GraphicaPanelB extends JPanel {

    private Network network;
    private double overallUtility;
    private static final int NUM_NODES = 100; // Total number of nodes (agents + resources)
    private static final int AGENT_START_X = 100; // X-coordinate for agents
    private static final int AGENT_Y_START = 100; // Starting Y-coordinate for agents
    private static final int RESOURCE_X_START = 600; // X-coordinate for resources
    private static final int RESOURCE_Y_START = 100; // Starting Y-coordinate for resources
    private static final int NODE_SPACING_X = 100; // Horizontal spacing between nodes
    private static final int NODE_SPACING_Y = 80; // Vertical spacing between rows of nodes
    private static final int TEXT_OFFSET_X = 20; // Offset for text labels

    public GraphicaPanelB(Network network, double overallUtility) {
        this.network = network;
        this.overallUtility = overallUtility;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        int numAgents = network.getAgents().size();
        int numResources = network.getResources().size();
        int agentsPerRow = (int) Math.ceil((double) numAgents / 5); // Agents split into 5 rows
        int resourcesPerRow = (int) Math.ceil((double) numResources / 5); // Resources split into 5 rows

        // Draw agents in 5 rows
        for (int i = 0; i < numAgents; i++) {
            Agent agent = network.getAgents().get(i);
            int rowIndex = i / agentsPerRow; // Determine row index

            int agentY = AGENT_Y_START + rowIndex * NODE_SPACING_Y;
            int agentX = AGENT_START_X + (i % agentsPerRow) * NODE_SPACING_X;

            g2d.setColor(Color.BLUE);
            g2d.fillOval(agentX - 15, agentY - 15, 30, 30);
            g2d.setColor(Color.BLACK);
            g2d.drawString("Agent " + (i + 1), agentX - 15 + TEXT_OFFSET_X, agentY - 20);

            // Draw agent's alpha value
            g2d.drawString("Alpha: " + agent.alpha, agentX + 40 + TEXT_OFFSET_X, agentY);
        }

        // Draw resources in 5 rows
        for (int i = 0; i < numResources; i++) {
            Resource resource = network.getResources().get(i);
            int rowIndex = i / resourcesPerRow; // Determine row index

            int resourceY = RESOURCE_Y_START + rowIndex * NODE_SPACING_Y;
            int resourceX = RESOURCE_X_START + (i % resourcesPerRow) * NODE_SPACING_X;

            g2d.setColor(Color.GREEN);
            g2d.fillRect(resourceX - 15, resourceY - 15, 30, 30);
            g2d.setColor(Color.BLACK);
            g2d.drawString("Resource " + (i + 1), resourceX - 15 + TEXT_OFFSET_X, resourceY - 20);

            // Draw allocated objects
            g2d.drawString("Allocated: " + resource.current_objects, resourceX + 20 + TEXT_OFFSET_X, resourceY);

            // Draw minimum price
            g2d.drawString("Min Price: " + resource.initialPrice, resourceX + 20 + TEXT_OFFSET_X, resourceY + 20);

            // Draw beta value
            g2d.drawString("Beta: " + resource.betaFirst, resourceX + 20 + TEXT_OFFSET_X, resourceY + 40);
        }

        // Draw edges and allocation details
        for (Edge edge : network.getEdges()) {
            int agentIndex = network.getAgents().indexOf(edge.getX());
            int resourceIndex = network.getResources().indexOf(edge.getY());

            int rowIndexAgent = agentIndex / agentsPerRow;
            int rowIndexResource = resourceIndex / resourcesPerRow;

            int agentY = AGENT_Y_START + rowIndexAgent * NODE_SPACING_Y;
            int resourceY = RESOURCE_Y_START + rowIndexResource * NODE_SPACING_Y;

            int agentX = AGENT_START_X + (agentIndex % agentsPerRow) * NODE_SPACING_X;
            int resourceX = RESOURCE_X_START + (resourceIndex % resourcesPerRow) * NODE_SPACING_X;

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

            if (priority == -1) {
                // Draw priority
                g2d.drawString("new edge: ", AGENT_START_X - 70, 10 + agentY + (resourceY - agentY) / 4);
            } else {
                // Draw priority
                g2d.drawString("Priority: " + priority, AGENT_START_X - 70, agentY + (resourceY - agentY) / 4);
            }
        }

        // Draw overall utility
        g2d.setColor(Color.BLACK);
        g2d.drawString("Overall Utility: " + overallUtility, 350, 50);
    }
}
