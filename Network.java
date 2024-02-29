import java.util.ArrayList;
import entities.*;

public class Network
{

    public ArrayList<Edge> edges;
    public ArrayList<Agent> agents;
    public ArrayList<Resource> resources;

    public Matrix adjacencyMatrix;
    public Matrix allocationMatrix;

    public Network(ArrayList<Edge> edges, ArrayList<Agent> agents, ArrayList<Resource> resources)
    {
        this.edges = edges;
        this.agents = agents;
        this.resources = resources;

        computeInitialMatrices();
    }

    /*Method that using the edges creates the initial adjacency matrix as well as the empty allocation matrix */
    public void computeInitialMatrices() {

        allocationMatrix = new Matrix(agents.size(), resources.size()); // Empty matrix of size agents x resources
        adjacencyMatrix = new Matrix(agents.size(), resources.size()); // Assuming you have a Matrix class for the adjacency matrix
    
        for (int i = 0; i < agents.size(); i++) {
            Agent agt = agents.get(i);
    
            for (int j = 0; j < resources.size(); j++) {
                Resource res = resources.get(j);
                boolean isConnected = false;
    
                for (Edge e : edges) {
                    if (e.isResourceY(res) && e.isAgentX(agt)) {
                        isConnected = true;
                        break; // No need to keep searching once a connection is found
                    }
                }
    
                if (isConnected) {
                    adjacencyMatrix.insertElement(i, j, 1);
                } else {
                    adjacencyMatrix.insertElement(i, j, 0);
                }
            }
        }
    }

    
    

    
} 