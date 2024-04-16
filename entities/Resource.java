package entities;
import java.util.*;

public class Resource 
{
    double initialPrice; 
    public int beta;
    public int current_objects; //store the amount of objects placed at this resource
    public int extraSpace;

   
    boolean Active; // TRUE if the resource is usable ; false otherwise

    public Resource(double price, int betaY)
    {
        this.initialPrice = price;
        this.Active = true;
        this.beta = betaY;
        extraSpace=0;

        
    }

    /*Method to retrieve the initial selling price of this resource */
    public double getinitialPrice()
    {
        return initialPrice;
    }

    /*method to add an object in this resource */
    public void addObject()
    {
        current_objects = current_objects+1;
    }


    /*method to check if there's space for one more object in this resource
     * @return true is itheres no space, false otherwise*/
    public boolean isFull()
    {
        return current_objects>=beta;
    }

    /*Method that given all the edges in the graph will compute the neighbors of this resource
     * @param array list of all network edges
     * @return array list of neighbor agents
     */
    public ArrayList<Agent> getAvailableNeighbors(ArrayList<Edge> edges)
    {
        ArrayList<Agent> nei = new ArrayList<Agent>();

        for (Edge e : edges)
        {
            if (e.getY() == this && !e.getX().isFull())
            {
                nei.add(e.getX());
            }   
        }
        return nei;

    }

     /*Method that given all the edges in the graph will compute the neighbors of this resource
     * @param array list of all network edges
     * @return array list of neighbor agents
     */
    public ArrayList<Agent> getNeighbors( ArrayList<Edge> edges)
    {
        ArrayList<Agent> nei = new ArrayList<Agent>();

        for (Edge e : edges)
        {
            if (e.getY() == this)
            {
                nei.add(e.getX());
            }   
        }
        return nei;
    }
    

    
    
}