import java.util.ArrayList;
import entities.*;

public class Network
{

    public ArrayList<Edge> edges;
    public ArrayList<Agent> agents;
    public ArrayList<Resource> resources;

    public Matrix adjacencyMatrix;
    public Matrix allocationMatrix;

    public int DistributionStrategy; // if 0: add extra space, if 1: add extra edge

    public Network(ArrayList<Edge> edges, ArrayList<Agent> agents, ArrayList<Resource> resources, int DistributionStrategy)
    {
        this.edges = edges;
        this.agents = agents;
        this.resources = resources;
        this.DistributionStrategy= DistributionStrategy;

        //Compute the neighborhood and priorityList of all agents
        for (Agent a : agents) 
        {
            ArrayList<Resource> neighbors = new ArrayList<Resource>();
            for (Edge e : edges)
            {
                if(e.isAgentX(a))
                {neighbors.add(e.getY());}    
            }
            a.setneighbors(neighbors);
            a.computePriorityList(neighbors);
           
        }

        computeInitialMatrices();
    }

    /*Method to start an allocation by using the allocationcontroller */
    public void startAllocation()
    {
        AllocationController algorithm = new AllocationController(this);
        for (Resource r : resources) 
            {
                System.out.println("This resource took :  " + r.extraSpace + "  extra slots.");
            }
            

        for (Agent a : agents) 
        {
                System.err.println("This agent spent:  " + (a.initialBudget - a.currentBudget) + " euros. "); 
        }

    }


    /*Method that using the edges creates the initial adjacency matrix as well as the empty allocation matrix */
    public void computeInitialMatrices() {

        allocationMatrix = new Matrix(agents.size(), resources.size()); // Empty matrix of size agents x resources
        adjacencyMatrix = new Matrix(agents.size(), resources.size()); // Assuming you have a Matrix class for the adjacency matrix
    
        for (int i = 0; i < agents.size(); i++) 
        {
            Agent agt = agents.get(i);
    
            for (int j = 0; j < resources.size(); j++)
             {
                Resource res = resources.get(j);

                boolean isConnected = false;
    
                for (Edge e : edges) 
                {
                    if (e.isResourceY(res) && e.isAgentX(agt)) 
                    {
                        isConnected = true;
                        break; // No need to keep searching once a connection is found
                    }
                }
    
                if (isConnected) 
                {
                    adjacencyMatrix.insertElement(i, j, 1);
                } 
                else 
                {
                    adjacencyMatrix.insertElement(i, j, 0);
                }
            }
        }
       
    }

     /*Method that, using the allocation matrix of this current network checks if it's a completeallocation */
     public boolean isAllocation()
     {
   
        for (Agent a : agents) 
        {
           if(!a.isFull()) 
           return false;
        }

         return true;
     }

     /*Method to retrieve the adjacency matrix of the network */
     public Matrix getAdjacencyMatrix()
     {
        return adjacencyMatrix;
     }

     /*Method to retrieve agents of the network */
     public ArrayList<Agent> getAgents()
     {
        return agents;
     }

     /*Method to retrieve resources of the network */
     public ArrayList<Resource> getResources()
     {
        return resources;
     }
     /*Method to retrieve the edges of the network */
     public ArrayList<Edge> getEdges()
     {
        return edges;
     }

     /*Method to retrieve the current allocation matrix of the network */
     public Matrix getAllocationMatrix()
     {
        return allocationMatrix;
     }

     /*Method to check in the adjacency matrix if an agent and a resource are connected
      * @param agent x and resource y
      @return true if there's an edge between them, false otherwise
      */
     public boolean isItConnected(Agent x,Resource y)
     {
        int indexA = agents.indexOf(x);
        int indexR = resources.indexOf(y);

        if(adjacencyMatrix.getElement(indexA,indexR)==1)
        {
            return true;
        }
        return false;
     }

     /*Method to add a new edge to the matriX
     cAN BE USED WHEN THE DISTRIBUTION MOVE CAN'T BE PERFORMED
     @Param agent x reosurce y
      */
     public void addEdge(Agent x, Resource y)
     {
        int indexA = agents.indexOf(x);
        int indexR = resources.indexOf(y);

        adjacencyMatrix.insertElement(indexA,indexR,1);
        x.neighborresources.add(y);
        edges.add(new Edge(x,y));
     }

    
     public Resource getNonFullUnlinkedResource(Agent x)
     {
        for (Resource r : resources) 
        {
            if ((!isItConnected(x,r)) && (!r.isFull()))
            {
                return r;
            }
            
        }
        return null;
     }

     public boolean hasNoSpaceAvailable(Agent x)
     {
        for (Resource r: x.neighborresources)
        {
            if(!r.isFull())
            return false;
        }
        return true;
     }
    

    
} 