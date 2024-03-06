package entities;

import java.util.ArrayList;

public class Resource 
{
    double initialPrice; 
    int beta;

    ArrayList<Agent> neighborAgents; //list of all agents that can use this resource
    ArrayList<Edge> resourceEdges; //list of all edges where this resource appears
    boolean Active; // TRUE if the resource is usable ; false otherwise

    public Resource(double price, ArrayList<Edge> allEdges, int betaY)
    {
        this.initialPrice = price;
        this.Active = true;
        this.beta = betaY;

        computeResourceEdges(allEdges);
    }

    /*Method to retrieve the initial selling price of this resource */
    public double getinitialPrice()
    {
        return initialPrice;
    }

    /*Method to create the negihbors and edges from the all edges passed as parameter form the network */
    public void computeResourceEdges(ArrayList<Edge> edges)
    {
        for (Edge e : edges) 
        {
            if(e.isResourceY(this)) //when the resource appears in the edge
            {
                resourceEdges.add(e);
                neighborAgents.add(e.getX()); //Add the correpsondent agent to the neighborhood
            }
        }
    }

    
    
}