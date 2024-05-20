import entities.*;
import java.util.*;

public class Bid
{
    public double value;
    public Agent owner;

    public Bid(Agent x, double minimumPrice, int p, boolean Demanded)
    {
        Random r = new Random();
        double temp = r.nextDouble(3);
        // the priority will be added to the bid, so highes tpriority ensures highes bid
        owner = x;
        if(owner.currentBudget>=minimumPrice+temp)
        {
            value = minimumPrice + temp + p;
            if(Demanded)
            {
                value = value - temp;
            }
        }
        else
        {
            value =0;
        }
    }

}