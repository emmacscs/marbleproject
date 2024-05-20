package entities;

import java.util.*;

public class Agent 
{
    boolean Active; // if the agent on or off
    public double currentBudget; //track of budget available
    public double initialBudget; 
    public int alpha;
    public ArrayList<Resource> neighborresources;
    public int objectsAllocated;
    public double current_utility;

    ArrayList<Resource> priorityList; // first element (spot 0) has lowest priority and last element has the highers

    public Agent(double budget, int alphaX)
    {
        this.initialBudget = budget;
        this.currentBudget = budget;
        this.Active = true;
        this.alpha = alphaX;
        this.objectsAllocated = 0;

    }

    /*Method to set an agents neighborhood */
    public void setneighbors(ArrayList<Resource> neighbors)
    {
        this.neighborresources = new ArrayList<Resource>(neighbors);
    }

    /*Method to create the priority list
     * Forn now its the order of the neighbors, in the future it will have to be random or another way of making it
     */
    public void computePriorityList(ArrayList<Resource> neighbors)
    {
        priorityList = new ArrayList<Resource>(neighbors);
    }

    public boolean isFull()
    {
        return objectsAllocated >= alpha;
    }

    /*Method to calculate an individual utility of an agent with the formula
     * U = (initial budget-final budget) + sum((priority of resource/neighbors)* amount of objects in that resource)
     * @param the allocation matrix and the index of this agent
     * @return double of utility for this agent at the given point in time
     */
    public double calculateAgentUtility(Matrix allocation, int agentIndex, ArrayList<Resource> allResources)
    {

        double money_utility = (initialBudget - currentBudget);

        double sumOfPriority_utility = 0;

        for (Resource r : this.neighborresources) 
        {
            double top = priorityList.indexOf(r) + 1; //avoid the 0 on numerator
            double ratio = top / (neighborresources.size()); 

            int indexR = allResources.indexOf(r);
            int objectsinthisresource = allocation.getElement(agentIndex, indexR);
            double resource_utility = ratio * objectsinthisresource;

            sumOfPriority_utility =sumOfPriority_utility + resource_utility;

        }

        double utility = sumOfPriority_utility + money_utility; //final summation for the utility
        this.current_utility = utility;

        return utility;
    }

    public int getPriority (Resource y, ArrayList<Edge> edges )
    {
        int priority_index = priorityList.indexOf(y) +1;
        int competing_number = y.getNeighbors(edges).size() -1;

        int ratio = priority_index - competing_number;

        return ratio;

        // i will apply the formula, priority - amount to compete against
    }

    

   

}


