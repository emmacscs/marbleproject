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

    

   

}


