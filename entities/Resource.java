package entities;

public class Resource 
{
    double initialPrice; 
    public int beta;
    public int current_objects; //store the amount of objects placed at this resource

  
   
    boolean Active; // TRUE if the resource is usable ; false otherwise

    public Resource(double price, int betaY)
    {
        this.initialPrice = price;
        this.Active = true;
        this.beta = betaY;

        
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
        return current_objects==beta;
    }
    

    
    
}