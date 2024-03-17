import java.util.ArrayList;
import entities.*;
import java.util.Random; 

public class AllocationController
{
    Network system;

    AllocationController(Network system)
    {

        this.system = system;
        while(!system.isAllocation())
        {
            allocationStep();
            system.allocationMatrix.matrixPrint();
        }
    }

    public void allocationStep()
    {
         //Select a random resource that still has one slot free minimum
         ArrayList<Resource> nonFullResources = nonFullResoures();
         if(nonFullResources.size()>0)
         {
            Random random_method = new Random(); 
            int index = random_method.nextInt(nonFullResources.size()); 
            Resource temp_resource = nonFullResources.get(index);
           // System.out.println(temp_resource);
    
            AuctionSystem Auction = new AuctionSystem(temp_resource, system.edges);
    
            Agent temp_agent = Auction.getWinner();
            //allocate an object 
            int indexAgent= system.agents.indexOf(temp_agent);
            int indexResource = system.resources.indexOf(temp_resource);
            int amount = system.allocationMatrix.getElement(indexAgent,indexResource);
            system.allocationMatrix.insertElement(indexAgent, indexResource, amount+1);
   
            //the agent lost money, the resource has one more object in it
   
            temp_resource.current_objects ++; //one more space taken in the resource
            temp_agent.currentBudget = temp_agent.currentBudget - Auction.highestBid.value;//the agent lost the bid money
         }
        
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