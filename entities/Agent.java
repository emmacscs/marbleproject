package entities;

import java.util.*;

public class Agent 
{
    boolean Active; // if the agent on or off
    double currentBudget; //track of budget available
    double initialBudget; 
    ArrayList<Resource> neighborResources; //list of all resources that this agent can use
    ArrayList<Edge> AgentEdges; //list of all edges where this agent appears
    ArrayList<Resource> priorityList; // first element (spot 0) has lowest priority and last element has the highers

    public Agent(double budget, ArrayList<Edge> allEdges)
    {
        this.initialBudget = budget;
        this.currentBudget = budget;
        this.Active = true;

        computeAgentEdges(allEdges);
        computePriorityList(); //at random choses the priority of resources

    }

    /*Method to iterate through the network edges and select the arraylist the ones where this agent appears also separates the resources in the neighbors
     * @param arraylist of all edges*/
    public void computeAgentEdges(ArrayList<Edge> edges)
    {
        for (Edge e : edges) 
        {
            if(e.isAgentX(this)) //when the agent appears in the edge
            {
                AgentEdges.add(e);
                neighborResources.add(e.getY()); //Add the correpsondent resource to the neighborhood
            }
        }
    }

   
    /*Method that looks at the connections of the agent and computes a random priority list, only effective when creating the agent */
    public void computePriorityList() 
    {
        ArrayList<Resource> available = new ArrayList<Resource>(neighborResources);
        Random rnd = new Random();

        while (!available.isEmpty()) 
        {
            int i = rnd.nextInt(available.size()); // Generate a random index within the available resources
            priorityList.add(available.get(i)); // Add the resource at the random index to the priority list
            available.remove(i); // Remove the selected resource from the available list
        }
    }
}


