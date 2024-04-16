import entities.*;
import java.util.*;

public class Bid
{
    public double value;
    public Agent owner;

    public Bid(Agent x, double minimumPrice)
    {
        Random r = new Random();
        double temp = r.nextDouble(3);
        owner = x;
        if(owner.currentBudget>=minimumPrice+temp)
        {
            value = minimumPrice + temp;
        }
        else
        {
            value =0;
        }
    }

}