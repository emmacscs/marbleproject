import java.util.ArrayList;

import entities.*;

public class AuctionSystem
{
    Agent HighestBidder;
    Resource r;
    ArrayList<Agent> allBidders;
    ArrayList<Bid> allBids;
    public Bid highestBid;

    AuctionSystem(Resource r, ArrayList<Edge> Edges)
    {
        this. r = r;
        //get all neighbor agents
        allBidders = r.getAvailableNeighbors(Edges);

      
            //each agent place a bid
            allBids = new ArrayList<Bid>();
            for (Agent a : allBidders) 
            {
                Bid temp = new Bid(a, r.getinitialPrice());       
                allBids.add(temp);
            }
        
            
        


    }

    /*Method to select the highest bid owner
     * In case of tie, right now the first agent to appear is selected, this should change in the future*/
    public Agent getWinner()
    {
        double max= 0;
        highestBid = allBids.get(0);

        for (Bid  b : allBids) 
        {
            if(b.value>max)
            {
                max = b.value;
                highestBid = b;
            }
            
        }

        HighestBidder = highestBid.owner;

        return HighestBidder;
    }

 
}