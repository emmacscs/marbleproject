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
            
            //If a resource is full but a neighbor of it hasnt been completely allocated add one space ot the resource
            for (Resource r : system.resources) 
            {
                if (r.isFull() && system.DistributionStrategy==0)
                {
                   for (Agent a : r.getNeighbors(system.edges)) 
                   {
                    if (!a.isFull())
                    {
                        r.beta++;
                        r.extraSpace++;
                    }
                   } 
                }

                else if (r.isFull() && system.DistributionStrategy==1)
                {
                    for (Agent a : r.getNeighbors(system.edges)) 
                    {
                        if ((!a.isFull()) && (system.hasNoSpaceAvailable(a)))
                        {
                            Resource r2= system.getNonFullUnlinkedResource(a);
                            system.addEdge(a, r2);
                            System.out.println("New edge between agent: " +system.agents.indexOf(a) + "and resource  " + system.resources.indexOf(r2));
                        } 
                    }
                }

            }
            
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
    
            if (Auction.allBids.size() >0)
            {
                Agent temp_agent = Auction.getWinner();
                //allocate an object 
                int indexAgent= system.agents.indexOf(temp_agent);
                int indexResource = system.resources.indexOf(temp_resource);
                int amount = system.allocationMatrix.getElement(indexAgent,indexResource);
                system.allocationMatrix.insertElement(indexAgent, indexResource, amount+1);

                if(Auction.highestBid.value ==0)
                {
                    System.out.println("All the agents run out of budget");
                }
    
                //the agent lost money, the resource has one more object in it

                System.out.println("Highest bid:  " + Auction.highestBid.value);
    
                temp_resource.current_objects ++; //one more space taken in the resource
                temp_agent.currentBudget = temp_agent.currentBudget - Auction.highestBid.value;//the agent lost the bid money

                temp_agent.objectsAllocated ++; //add an object already allocated from the agent

                System.out.println("Agent at index " + system.agents.indexOf(temp_agent) +"  Bought resource at index  " + system.resources.indexOf(temp_resource) + "   For " + Auction.highestBid.value + "   euros" );
                
                System.out.println("Agent at index " + system.agents.indexOf(temp_agent)+ "  now has " + (temp_agent.alpha - temp_agent.objectsAllocated) + "   objects left");
                System.out.println("Agent at index " + system.agents.indexOf(temp_agent)+ "  now has " + (temp_agent.initialBudget - temp_agent.currentBudget) + "   money");

                System.out.println("Resource at index  " + system.resources.indexOf(temp_resource) + "   now has  " + (temp_resource.beta + temp_resource.extraSpace - temp_resource.current_objects) + "  space left  ");
            }
            
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