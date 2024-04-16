package entities;


public class Edge 
{

    //An edge is composed by an agent x and a resource y
    Agent x;
    Resource y;
    
    public Edge(Agent agent,Resource resource)
    {
        this.x = agent;
        this.y = resource;
    }

    /*Method to retrieve the agent of this edge */
    public Agent getX()
    {
        return this.x;
    }

    /*Method to retrieve the resource of this edge */
    public Resource getY()
    {
        return this.y;
    }

    /*Method to check if an agent i is part of this edge */
    public boolean isAgentX(Agent i)
    {
        return (this.x == i);
    }

    /*Method to check if a resouce j is part of this edge */
    public boolean isResourceY(Resource j)
    {
        return (this.y == j);
    }

    

   
}
