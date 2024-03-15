import java.util.ArrayList;
import entities.*;
import java.util.Random; 

public class AllocationController
{
    Network system;

    AllocationController(Network system)
    {

        this.system = system;
    }

    public void allocationStep()
    {
         //Select a random resource that still has one slot free minimum
         ArrayList<Resource> nonFullResources = nonFullResoures();
         Random random_method = new Random(); 
         int index = random_method.nextInt(nonFullResources.size()); 
         Resource temp_resource = nonFullResources.get(index);
         System.out.println(temp_resource);
 
         AuctionSystem Auction = new AuctionSystem(temp_resource);
 
         Agent temp_agent = Auction.getWinner();
         //allocate an object 
 
         //the agent lost money, the resource has one more object in it
    }


    /*For this first step of the algorithm method to get all resources with space
     * @return array list of all non full resources */
    ArrayList<Resource> nonFullResoures()
    {
        ArrayList<Resource> nonFull = new ArrayList<Resource>();

        for (Resource r : this.system.resources) 
        {
            if(!r.isFull())
            {
                nonFull.add(r);
            }  
        }

        return nonFull;
    }
}