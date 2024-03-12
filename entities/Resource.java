package entities;

public class Resource 
{
    double initialPrice; 
    public int beta;

  
   
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

    

    
    
}